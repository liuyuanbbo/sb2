package org.zmz.c.vo.dataopen.dataset;

import lombok.Data;
import org.springframework.beans.BeanUtils;
import org.zmz.c.qo.dataopen.ModelInfo;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class PhysicsTableResponseVo {

    private String sysCode;

    private Long datasourceId;

    private Date createTime;

    private Long tableId;

    private String tableName;

    private Long catalogId;

    private String createStaffName;

    private String domainId;

    private Long createStaffId;

    private Date lastUpdateTime;

    private String tableCode;

    private String domainName;

    private Long lastModifyStaffId;

    private String schemaCode;

    private Long comAcctId;

    private String dataCycle;

    private String datasourceCode;

    private Long metaDataId;

    /**
     * 落地状态，00A是以落地；
     */
    private String metaDataStatus;

    private String versionId;

    private String uuid;

    private String acctColumn;

    private List<PhysicsColumnResponseVo> columns;

    public PhysicsTableResponseVo() {
    }

    public PhysicsTableResponseVo(ModelInfo modelInfo) {
        this.sysCode = modelInfo.getMetaDataInfo().getTableType();
        this.datasourceId = modelInfo.getMetaDataInfo().getSchemaId();
        this.tableId = modelInfo.getMetaDataInfo().getMetaDataId();
        this.tableName = modelInfo.getMetaDataInfo().getMetaDataName();
        this.catalogId = modelInfo.getMetaDataInfo().getCatalogId();
        this.domainId = modelInfo.getMetaDataInfo().getDomainId();
        this.tableCode = modelInfo.getMetaDataInfo().getMetaDataCode();
        this.schemaCode = modelInfo.getMetaDataInfo().getSchemaCode();
        this.dataCycle = modelInfo.getMetaDataInfo().getDataCycleCode();
        this.datasourceCode = modelInfo.getMetaDataInfo().getSchemaCode();
        this.metaDataId = modelInfo.getMetaDataInfo().getMetaDataId();
        this.versionId = modelInfo.getMetaDataInfo().getVersionId();
        this.uuid = modelInfo.getMetaDataInfo().getUuid();
        this.acctColumn = modelInfo.getBussinessAttr().getPeriod();
        this.columns = modelInfo.getColumnList().stream().map(column -> {
            PhysicsColumnResponseVo phyColumn = new PhysicsColumnResponseVo();
            BeanUtils.copyProperties(column, phyColumn);
            return phyColumn;
        }).collect(Collectors.toList());
    }

}