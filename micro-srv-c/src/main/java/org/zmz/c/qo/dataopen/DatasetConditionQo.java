package org.zmz.c.qo.dataopen;

import lombok.Getter;
import lombok.Setter;
import org.zmz.c.pojo.dataopen.AppSqlCondition;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Setter
@Getter
public class DatasetConditionQo extends AppSqlCondition {

    private Long dataId;

    private String dataType;

    private String dataName;

    private String dateFormat;

    private String columnType;

    private Long mainTableId;

    /**
     * 枚举值
     */
    private List<Map<?, ?>> enumList;

    /**
     * 单位名称(指标/标签)
     */
    private String unitName;

    /**
     * 是否组织维度表，如果多个对象配置了组织维表，需要前端限制只能拖一条路径的，防止多个组织维表无法区分最细组织粒度
     */
    private Integer isOrgDimension;

    /**
     * 数据源编码
     */
    private String datasourceCode;

    /**
     * 是否有效1，失效0，对象撤销、指标下架、字段删除时对应状态失效
     */
    private String isValid = "1";

    /**
     * 度量/维度/条件对应的术语所对应的物理字段，范围随着拖拽会变化
     */
    private List<DatasetObjColumnVo> termRelaColumns = new ArrayList<>();

    /**
     * 时间粒度类型  timeFreq/timeFreqValue
     */
    private String businessType;

    /**
     * 表别名
     */
    private String tableAlias;
}
