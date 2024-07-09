package org.zmz.c.qo.dataopen;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import java.util.List;

/**
 * 数据编排详细信息
 */
@Getter
@Setter
public class DatasetDetail extends DatasetConfig {
    /**
     * 编排编码，唯一
     */
    private String appCode;

    /**
     * 父级编排标识
     */
    private Long parentId;

    /**
     * 父级编排编码（appCode)，决定父子关系
     */
    private String parentCode;

    /*********************** 分割线，以上为保存需要用到的字段，以下为查询详情 ************************/

    private String appName;

    private String appDesc;

    /**
     * 编排目录
     */
    private Long grpId;

    /**
     * 输出数据目录
     */
    private Long dataCatalogId;

    private Long metaTableId;

    private String appTableCode;

    /**
     * 前端下钻用到
     */
    private String fieldKey;

    /**
     * 任务id
     */
    private Long taskId;

    /**
     * 节点id
     */
    private Long dnId;

    /**
     * 申请单号
     */
    private Long orderNo;

    /**
     * 版本库对象标识uuid
     */
    private String versionObjectCode;

    /**
     * 引用表
     */
    private String sqlRelTables;

    /**
     * 任务sql
     */
    private String appInstanceSql;

    /*********************** 分割线，以上为查询查询详情，以下为配置信息 ************************/

    /**
     * 调度信息
     */
    private List<DatasetColumnAndConditionQo> groups;

    /**
     * 下发数据源
     */
    private Long datasourceId;

    private String datasourceType;

    /**
     * 调度信息
     */
    private ScheduleInfoQo scheduleInfo;

    /**
     * 项目ID
     */
    public Long projectId;

    /**
     * 假如有引用的对象、表、字段、指标等失效，则数据集根据失效标识提示用户该数据集引用下架指标或维度，请删除或重新生成
     */
    private String isValid = "1";

    /**
     * 菜单类型
     */
    public String operType;

    /**
     * 明细表ID
     */
    private Long detailTableId;

    /**
     * 公共属性拷贝
     */
    public void setDatasetDetailExt(DatasetDetail datasetDetail) {
        for (DatasetColumnAndConditionQo groupQo : datasetDetail.getGroups()) {
            BeanUtils.copyProperties(datasetDetail, groupQo);
        }
    }
}