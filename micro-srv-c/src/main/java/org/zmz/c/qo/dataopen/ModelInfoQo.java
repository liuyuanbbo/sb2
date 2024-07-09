package org.zmz.c.qo.dataopen;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class ModelInfoQo implements Serializable {

    private Long metaDataId;

    private List<Long> metaDataIdList;

    private List<ModelTableQo> datasourceTableCodes;

    /**
     * 是否时间维度表
     */
    private String isTimeDimension;

    /**
     * 是否层级组织维度表（N端），1端为组织信息表
     */
    private String isOrgDimension;

    /**
     * 模型表类型，与isTimeDimension或isOrgDimension组合查询，返回结果应该只有一条
     */
    private String tableType;

    @Setter
    @Getter
    public static class ModelTableQo implements Serializable {
        private Long datasourceId;

        private String tableCode;

        private String tableDesc;

        public ModelTableQo(Long datasourceId, String tableCode) {
            this.datasourceId = datasourceId;
            this.tableCode = tableCode;
        }
    }
}