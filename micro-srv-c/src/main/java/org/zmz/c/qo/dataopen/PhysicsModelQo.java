package org.zmz.c.qo.dataopen;

import lombok.Getter;
import lombok.Setter;
import org.zmz.c.utils.KeyValues;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
public class PhysicsModelQo implements Serializable {

    private String pageIndex;

    private String pageSize;

    private String dataSourceName;

    private Long datasourceId;

    /**
     * 表编码 支持模糊查询
     */
    private String schemaCode;

    /**
     * 表编码 支持模糊查询
     */
    private String sysCode;

    /**
     * 表编码 支持模糊查询
     */
    private String tableCode;

    /**
     * 表名/编码 支持模糊查询 字段名/编码，模糊查询
     */
    private String tableName;

    /**
     * 表编码 精确查询
     */
    private String tableFullCode;

    /**
     * 表名 精确查询
     */
    private String tableFullName;

    /**
     * 表id
     */
    private Long tableId;

    /**
     * 表编码 支持模糊查询
     */
    private Long catalogId;

    private String language;

    /**
     * 客户标识
     */
    private Long comAcctId;

    /**
     * 表类型
     */
    private String tableType;

    /**
     * 业务主键
     */
    private String primaryKey;

    /**
     * 业务主键，模糊查询
     */
    private String primaryLikeKey;

    /**
     * 表id
     */
    private Collection<Long> tableIdList;

    /**
     * 表id
     */
    private Collection<String> tableCodeList;

    /**
     * 多业务主键
     */
    private List<String> multiPrimary;

    /**
     * 表id
     */
    private List<Long> excTableIdList;

    private String status;

    /**
     * 默认不需要权限过滤，个人用户也可以查询企业用户的数据
     */
    private String notDataPrivAop = KeyValues.YES_VALUE_Y;
}