package org.zmz.c.service.dataopen.dataset;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.zmz.c.mapper.dataopen.ObjInfoMapper;
import org.zmz.c.pojo.dataopen.AppSqlColumn;
import org.zmz.c.pojo.dataopen.ObjInfo;
import org.zmz.c.pojo.dataportal.DcSystemConfigList;
import org.zmz.c.pojo.dataportal.Organization;
import org.zmz.c.qo.dataopen.BusinessAttr;
import org.zmz.c.qo.dataopen.Column;
import org.zmz.c.qo.dataopen.Constants;
import org.zmz.c.qo.dataopen.DataPrivCtrlVo;
import org.zmz.c.qo.dataopen.DatasetColumnAndConditionQo;
import org.zmz.c.qo.dataopen.DatasetColumnQo;
import org.zmz.c.qo.dataopen.DatasetDetail;
import org.zmz.c.qo.dataopen.MetaDataInfo;
import org.zmz.c.qo.dataopen.ModelInfo;
import org.zmz.c.qo.dataopen.ModelInfoQo;
import org.zmz.c.qo.dataopen.OrgDimension;
import org.zmz.c.qo.dataopen.PhysicsModelQo;
import org.zmz.c.service.dataopen.common.StaticDataService;
import org.zmz.c.service.dataopen.remote.DataCommonService;
import org.zmz.c.utils.AccountUtil;
import org.zmz.c.utils.JsonUtil;
import org.zmz.c.vo.dataopen.dataset.PhysicsTableResponseVo;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
public class DatasetDataPrivService {

    @Resource
    private DataCommonService dataCommonService;

    @Resource
    private DatasetModelService datasetModelService;

    @Resource
    StaticDataService staticDataService;

    @Resource
    ObjInfoMapper objInfoMapper;

    public int getDataPrivGroupCount(DatasetDetail params) {
        if (CollectionUtils.isEmpty(params.getGroups())) {
            return 0;
        }
        return (int) params.getGroups().stream().filter(v -> v.getDataPrivCtrlInfo().isDataPrivCtrl()).count();
    }

    public DataPrivCtrlVo isDataPrivCtrl(DatasetColumnAndConditionQo params) {
        DataPrivCtrlVo dataPrivCtrlVo = new DataPrivCtrlVo();
        // 判断是否需要数据权限控制
        Map<Long, ModelInfo> modelMap = datasetModelService.getModelInfoMap(params);
        List<ModelInfo> privModeInfos = modelMap.values().stream()
            .filter(model -> StringUtils.isNotEmpty(model.getBussinessAttr().getOrgField()))
            .collect(Collectors.toList());
        if (CollectionUtils.isEmpty(privModeInfos)) {
            dataPrivCtrlVo.setDataPrivCtrl(false);
            return dataPrivCtrlVo;
        }
        else {
            dataPrivCtrlVo.setDataPrivCtrl(true);
            dataPrivCtrlVo.setDataPrivModelList(privModeInfos);
        }
        // 获取数据库类型
        Long objectId = params.getColumnList().stream().map(AppSqlColumn::getObjectId).filter(Objects::nonNull)
            .findFirst().orElse(null);
        ObjInfo oneObjInfo = objInfoMapper.selectById(objectId);
        String tableType = oneObjInfo.getTableType();

        ModelInfoQo modelInfoQo = new ModelInfoQo();
        modelInfoQo.setIsOrgDimension(Constants.YES_VALUE_1);
        modelInfoQo.setTableType(tableType);
        List<ModelInfo> modelInfoList = dataCommonService.queryAllModelInfoBatch(modelInfoQo);
        // 过滤层级粒度为-1的组织维度表
        if (CollUtil.isNotEmpty(modelInfoList)) {
            log.info("查找公共模块的组织维度表信息：{}", JsonUtil.toJson(modelInfoList));
            modelInfoList = modelInfoList.stream().filter(ModelInfo::isOrgDimension).collect(Collectors.toList());
        }
        if (CollUtil.isEmpty(modelInfoList)) {
            dataPrivCtrlVo.setDataPrivCtrl(false);
            return dataPrivCtrlVo;
        }
        // 判断 modelInfoList 中的 orgLevel 是否有重复
        boolean isExistOrgLevelRepeat = existOrgLevelRepeat(modelInfoList);
        // 存在重复的 检查是否配置了 COMSUME_ORG_DIMENSION
        if (isExistOrgLevelRepeat) {
            List<DcSystemConfigList> configList = staticDataService.getDcSystemConfigList("CONSUME_ORG_DIMENSION");
            // 没有配置 直接日志提示
            if (CollectionUtils.isEmpty(configList)) {
                log.info("层级(组织)维度表层级重复，无需数据权限控制！如需数据权限控制请检查系统参数配置CONSUME_ORG_DIMENSION。");
                dataPrivCtrlVo.setDataPrivCtrl(false);
                return dataPrivCtrlVo;
            }
            else {
                // 根据配置库表类型+模型表编码进行 modelInfoList 过滤
                modelInfoList = filterModelInfoList(modelInfoList, configList);
                boolean filterIsExistOrgLevelRepeat = existOrgLevelRepeat(modelInfoList);
                // 过滤之后仍然存在 重复的则进行非数据权限控制并记录日志
                if (filterIsExistOrgLevelRepeat) {
                    log.warn("层级(组织)维度表orgLevel存在重复,经过CONSUME_ORG_DIMENSION配置参数进行过滤后仍然存在重复,无需数据权限控制！");
                    dataPrivCtrlVo.setDataPrivCtrl(false);
                    return dataPrivCtrlVo;
                }
            }
        }
        if (CollectionUtils.isEmpty(modelInfoList)) {
            log.warn("层级(组织)维度表为空,请检查CONSUME_ORG_DIMENSION配置参数,无需数据权限控制！");
            dataPrivCtrlVo.setDataPrivCtrl(false);
            return dataPrivCtrlVo;
        }
        log.info("层级维度不为-1的组织维度表信息：{}", JsonUtil.toJson(modelInfoList));

        List<Long> metaDataIds = modelInfoList.stream().map(m -> m.getMetaDataInfo().getMetaDataId())
            .collect(Collectors.toList());

        QueryWrapper<ObjInfo> wrapper = new QueryWrapper<>();
        wrapper.in("meta_table_id", metaDataIds);
        wrapper.ne("status_cd", Constants.STATUS_CD_1200);
        List<ObjInfo> objInfoList = objInfoMapper.selectList(wrapper);
        if (CollectionUtils.isEmpty(objInfoList)) {
            log.warn("层级(组织)维度表未配置对象！");
            dataPrivCtrlVo.setDataPrivCtrl(false);
            return dataPrivCtrlVo;
        }

        // 获取组织明细表
        ModelInfo orgModel = this.getOrgModel(tableType);
        if (orgModel == null) {
            log.warn("层级(组织)明细表未配置，无需数据权限控制！");
            dataPrivCtrlVo.setDataPrivCtrl(false);
            return dataPrivCtrlVo;
        }
        if (StringUtils.isEmpty(orgModel.getBussinessAttr().getBusinessKey())) {
            log.warn("层级(组织)明细表未配置主键，无需数据权限控制！");
            dataPrivCtrlVo.setDataPrivCtrl(false);
            return dataPrivCtrlVo;
        }
        dataPrivCtrlVo.setDataPrivCtrl(true);
        dataPrivCtrlVo.setOrgDimensionModelInfoList(modelInfoList);
        dataPrivCtrlVo.setOrgDimensionObjInfoList(objInfoList);
        dataPrivCtrlVo.setOrgModelInfo(orgModel);
        // 获取组织维度字段
        this.setOrgDimensionColumn(params, dataPrivCtrlVo);
        // 设置表与组织明细表的关联路径map
        return dataPrivCtrlVo;
    }

    /**
     * 根据组织维度表获取组织明细表
     */
    public ModelInfo getOrgModel(String tableType) {
        // 获取组织明细表
        DcSystemConfigList orgConfig = staticDataService.getDcSystemConfigList("CONSUME_ORG_MODEL", tableType);
        if (orgConfig == null) {
            log.warn("组织明细表未配置[CONSUME_ORG_MODEL]！");
            return null;
        }
        PhysicsModelQo physicsModelQo = new PhysicsModelQo();
        physicsModelQo.setTableType(tableType);
        physicsModelQo.setTableCode(orgConfig.getStandDisplayValue());
        physicsModelQo.setPageIndex(Constants.PAGE_INDEX + "");
        physicsModelQo.setPageSize(Constants.PAGE_INDEX + "");

        List<PhysicsTableResponseVo> modelInfoList = dataCommonService.getPhysicsTableListPro(physicsModelQo);
        if (CollUtil.isEmpty(modelInfoList)) {
            log.warn("获取不到组织明细表{}！", orgConfig.getStandDisplayValue());
            return null;
        }
        ModelInfo orgModel = dataCommonService.queryAllModelInfoPro(modelInfoList.get(0).getMetaDataId());
        // standDesc存储pathCode对应的字段
        orgModel.getBussinessAttr().setPathCode(orgConfig.getStandDesc());
        return orgModel;
    }

    /**
     * 获取组织维度字段
     */
    public void setOrgDimensionColumn(DatasetColumnAndConditionQo params, DataPrivCtrlVo dataPrivCtrl) {
        // 获取权限控制相关信息
        if (!dataPrivCtrl.isDataPrivCtrl()) {
            return;
        }
        // 与第一组权限字段保持一样
        DataPrivCtrlVo firstDataPrivCtrl = params.getFirstDataPrivCtrlInfo();
        if (firstDataPrivCtrl != null) {
            if (firstDataPrivCtrl.getExistOrgDimensionColumn() != null) {
                for (DatasetColumnQo datasetColumnQo : params.getColumnList()) {
                    if (datasetColumnQo.getColumnId()
                        .equals(firstDataPrivCtrl.getExistOrgDimensionColumn().getColumnId())
                        && datasetColumnQo.getPath().equals(firstDataPrivCtrl.getExistOrgDimensionColumn().getPath())) {
                        dataPrivCtrl.setExistOrgDimensionColumn(firstDataPrivCtrl.getExistOrgDimensionColumn());
                        if (firstDataPrivCtrl.getAddOrgDimensionColumn() != null) {
                            dataPrivCtrl.setAddOrgDimensionColumn(firstDataPrivCtrl.getAddOrgDimensionColumn());
                        }
                        dataPrivCtrl.setOrgDimensionModelInfo(firstDataPrivCtrl.getOrgDimensionModelInfo());
                        dataPrivCtrl.setOrgDimensionObjInfo(firstDataPrivCtrl.getOrgDimensionObjInfo());
                        return;
                    }
                }
            }
        }

        List<ModelInfo> orgDimensionModelInfoList = dataPrivCtrl.getOrgDimensionModelInfoList();
        List<ObjInfo> orgDimensionObjInfoList = dataPrivCtrl.getOrgDimensionObjInfoList();
        Map<Long, ModelInfo> orgDimensionModelInfoMap = orgDimensionModelInfoList.stream()
            .collect(Collectors.toMap(m -> m.getMetaDataInfo().getMetaDataId(), m -> m, (v1, v2) -> v2));
        Map<Long, ObjInfo> orgDimensionObjInfoMap = orgDimensionObjInfoList.stream()
            .collect(Collectors.toMap(ObjInfo::getMetaTableId, o -> o, (v1, v2) -> v2));

        // 获取分析字段
        List<DatasetColumnQo> allColumnList = new ArrayList<>();
        List<DatasetColumnQo> columnList = params.getColumnList();
        for (DatasetColumnQo datasetColumnQo : columnList) {
            List<DatasetColumnQo> columnGroupList = datasetColumnQo.getColumnGroup();
            // 计算字段需要把他拆分成多个度量
            if (CollUtil.isNotEmpty(columnGroupList)) {
                for (DatasetColumnQo columnGroup : columnGroupList) {
                    if ("arithmeticCondItem".equals(columnGroup.getCondType())) {
                        allColumnList.add(columnGroup);
                    }
                }
            }
            else {
                allColumnList.add(datasetColumnQo);
            }
        }

        List<OrgDimension> selectOrgDimensionList = new ArrayList<>(10);
        List<DatasetColumnQo> selectColumnList = new ArrayList<>(10);
        List<OrgDimension> orgDimensionTotalList = new ArrayList<>(10);
        List<Column> orgDimensionColumnTotalList = new ArrayList<>(10);
        // 把每张组织维度表的层级字段和所有字段选出来
        for (ModelInfo orgDimensionModelInfo : orgDimensionModelInfoList) {
            Long metaDataId = orgDimensionModelInfo.getMetaDataInfo().getMetaDataId();
            List<OrgDimension> orgDimensionList = orgDimensionModelInfo.getBussinessAttr().getOrgDimensionList();
            for (OrgDimension orgDimension : orgDimensionList) {
                orgDimension.setMetaDataId(metaDataId);
            }
            orgDimensionTotalList.addAll(orgDimensionList);

            List<Column> orgDimensionColumnList = orgDimensionModelInfo.getColumnList();
            orgDimensionColumnTotalList.addAll(orgDimensionColumnList);
        }

        // 判断字段有没有选择层级维度
        for (DatasetColumnQo column : allColumnList) {
            String columnCode = column.getColumnCode();
            for (OrgDimension orgDimension : orgDimensionTotalList) {
                if (column.getTableId().equals(orgDimension.getMetaDataId())
                    && (orgDimension.getOrgIdColumnCode().equals(columnCode)
                        || orgDimension.getOrgNameColumnCode().equals(columnCode))) {
                    selectColumnList.add(column);
                }
            }
        }

        // 对维度的层级字段进行排序
        OrgDimension minOrgDimension = null;
        List<DatasetColumnQo> sortList = selectColumnList.stream().filter(c -> "dimension".equals(c.getAppType()))
            .sorted(Comparator.comparingLong(DatasetColumnQo::getSeq)).collect(Collectors.toList());
        if (CollUtil.isNotEmpty(sortList)) {
            // 以第一个选择维度的层级字段为权限判断路径
            DatasetColumnQo firstOrgDimensionColumn = sortList.get(0);
            // 筛选出与第一个选择组织维度字段在同一路径上的其他层级字段
            selectColumnList = selectColumnList.stream().filter(c -> "dimension".equals(c.getAppType()))
                .filter(c -> c.getPath().contains(firstOrgDimensionColumn.getPath())
                    || firstOrgDimensionColumn.getPath().contains(c.getPath()))
                .collect(Collectors.toList());

            // 找出层级字段对应的层级
            for (DatasetColumnQo column : selectColumnList) {
                String columnCode = column.getColumnCode();
                for (OrgDimension orgDimension : orgDimensionTotalList) {
                    if (column.getTableId().equals(orgDimension.getMetaDataId())
                        && (orgDimension.getOrgIdColumnCode().equals(columnCode)
                            || orgDimension.getOrgNameColumnCode().equals(columnCode))
                        && Integer.parseInt(orgDimension.getOrgLevel()) > 0) {
                        selectOrgDimensionList.add(orgDimension);
                    }
                }
            }

            // 找到组织维度层级最细的那个
            minOrgDimension = selectOrgDimensionList.stream().max((x, y) -> {
                int xOrgLevel = Integer.parseInt(x.getOrgLevel());
                int yOrgLevel = Integer.parseInt(y.getOrgLevel());
                return xOrgLevel - yOrgLevel;
            }).orElse(null);

        }
        // 有选择组织维度上的字段
        if (minOrgDimension != null) {
            DatasetColumnQo id = null;
            DatasetColumnQo name = null;
            for (DatasetColumnQo datasetColumnQo : selectColumnList) {
                if (datasetColumnQo.getColumnCode().equals(minOrgDimension.getOrgIdColumnCode())
                    && datasetColumnQo.getTableId().equals(minOrgDimension.getMetaDataId())) {
                    id = datasetColumnQo;
                }
                if (datasetColumnQo.getColumnCode().equals(minOrgDimension.getOrgNameColumnCode())
                    && datasetColumnQo.getTableId().equals(minOrgDimension.getMetaDataId())) {
                    name = datasetColumnQo;
                }
            }
            // 有选id的优先，并且不需要新增id字段了；选了name但是没选id，就需要新增一个id字段
            if (id != null) {
                dataPrivCtrl.setExistOrgDimensionColumn(id);
            }
            else if (name != null) {
                dataPrivCtrl.setExistOrgDimensionColumn(name);
                for (Column column : orgDimensionColumnTotalList) {
                    if (column.getColumnCode().equals(minOrgDimension.getOrgIdColumnCode())
                        && column.getMetaDataId().equals(minOrgDimension.getMetaDataId())) {
                        DatasetColumnQo orgDimensionColumnQo = new DatasetColumnQo();
                        orgDimensionColumnQo.setColumnId(column.getColumnId());
                        orgDimensionColumnQo.setColumnCode(column.getColumnCode());
                        orgDimensionColumnQo.setTableId(column.getMetaDataId());
                        orgDimensionColumnQo.setTableCode(column.getTableCode());
                        orgDimensionColumnQo.setPath(name.getPath());
                        dataPrivCtrl.setAddOrgDimensionColumn(orgDimensionColumnQo);
                    }
                }
            }
            // 最后将最细粒度层级所在的组织维度表和组织维度对象放到dataPrivCtrl，最后sql会根据这个选择用哪张表的path_code
            ModelInfo orgDimensionModelInfo = orgDimensionModelInfoMap.get(minOrgDimension.getMetaDataId());
            ObjInfo orgDimensionObjInfo = orgDimensionObjInfoMap.get(minOrgDimension.getMetaDataId());

            Organization organization = AccountUtil.getOrganization();
            Map<String, OrgDimension> orgDimensionMap = new HashMap<>(10);
            for (OrgDimension orgDimension : orgDimensionTotalList) {
                orgDimensionMap.put(orgDimension.getOrgLevel(), orgDimension);
            }
            // 如果用户所在组织的层级比所选最细组织维度还要细，就要从用户那个层级获取pathCode
            if (organization != null) {
                String orgLevel = organization.getOrgLevel();
                if (Integer.parseInt(orgLevel) > Integer.parseInt(minOrgDimension.getOrgLevel())) {
                    OrgDimension userOrgDimension = orgDimensionMap.get(orgLevel);
                    orgDimensionModelInfo = orgDimensionModelInfoMap.get(userOrgDimension.getMetaDataId());
                    orgDimensionObjInfo = orgDimensionObjInfoMap.get(userOrgDimension.getMetaDataId());
                }
            }
            dataPrivCtrl.setOrgDimensionModelInfo(orgDimensionModelInfo);
            dataPrivCtrl.setOrgDimensionObjInfo(orgDimensionObjInfo);
        }
    }

    // 根据查询出来的配置值 过滤 modelInfoList
    private List<ModelInfo> filterModelInfoList(List<ModelInfo> modelInfoList, List<DcSystemConfigList> configList) {
        List<ModelInfo> newModelInfoList = new ArrayList<>();
        for (DcSystemConfigList dcSystemConfig : configList) {
            String standDisplayValue = dcSystemConfig.getStandDisplayValue();
            String standCode = dcSystemConfig.getStandCode();
            for (ModelInfo modelInfo : modelInfoList) {
                MetaDataInfo metaDataInfo = modelInfo.getMetaDataInfo();
                if (StringUtils.equals(standDisplayValue, metaDataInfo.getTableType())
                    && StringUtils.equals(standCode, metaDataInfo.getMetaDataCode())) {
                    newModelInfoList.add(modelInfo);
                }
            }
        }
        return newModelInfoList;
    }

    // 判断modelInfoList 中的业务属性 的 orgLevel 是否有重复(自身 以及 其他表的 orgLevel 都不允许有重复)
    private boolean existOrgLevelRepeat(List<ModelInfo> modelInfoList) {
        Set<String> orgLevelSets = new HashSet<>();
        for (ModelInfo modelInfo : modelInfoList) {
            BusinessAttr bussinessAttr = modelInfo.getBussinessAttr();
            if (bussinessAttr != null && bussinessAttr.getOrgDimensionList() != null) {
                for (OrgDimension orgDimension : bussinessAttr.getOrgDimensionList()) {
                    String orgLevel = orgDimension.getOrgLevel();
                    if (orgLevelSets.contains(orgLevel)) {
                        return true;
                    }
                    orgLevelSets.add(orgLevel);
                }
            }
        }
        return false;
    }
}
