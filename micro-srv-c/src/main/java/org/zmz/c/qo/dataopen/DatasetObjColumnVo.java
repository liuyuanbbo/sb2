package org.zmz.c.qo.dataopen;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Setter
@Getter
public class DatasetObjColumnVo {

    /**
     * 数据类型
     */
    public static final String COLUMN = "column";

    public static final String LABEL = "label";

    public static final String INDEX = "index";

    public static final String PRO_INDEX = "proIndex";

    public static final List<String> STRING_TYPE_LIST = List.of("CHAR", "VARCHAR", "BINARY", "VARBINARY",
            "BLOB", "MEDIUMBLOB", "LONGBLOB", "TEXT",
            "MEDIUMTEXT", "LONGTEXT", "ENUM", "SET",
            "NCHAR", "VARCHAR2", "NVARCHAR2", "CLOB",
            "NCLOB", "STRING");

    /**
     * 当前目录标识，前端持载树
     */
    private String dir;

    /**
     * 当前父目录标识，前端持载树用
     */
    private String parentDir;

    /**
     * 当前数据主键标识
     */
    private Long dataId;

    private Long parentId;

    private String dataType;

    private String dataName;

    /**
     * 字段ID
     */
    private Long columnId;

    /**
     * 字段编码
     */
    private String columnCode;

    /**
     * 字段类型
     */
    private String columnType;

    /**
     * 字段来源类型
     */
    private String srcColumnType;

    /**
     * 归属指标标识
     */
    private Long dimIndexId;

    /**
     * 原子指标标识
     */
    private Long proIndexId;

    /**
     * 归属标签标识
     */
    private Long injectionLabelId;

    /**
     * 表编码
     */
    private String tableCode;

    /**
     * 归属表标识
     */
    private Long tableId;

    /**
     * 数据源标识
     */
    private Long datasourceId;

    /**
     * 数据源标识
     */
    private String datasourceCode;

    /**
     * 对象标识
     */
    private Long objectId;

    /**
     * 来源对象标识，虚拟维度对象对应的物理字段所属的对象
     */
    private Long srcObjectId;

    /**
     * 对象名称
     */
    private String objectName;

    /**
     * 技术口径
     */
    private String techDesc;

    /**
     * 业务口径
     */
    private String busiDesc;

    /**
     * 是否主键字段
     */
    private String isPrimary;

    /**
     * 是否是帐字段
     */
    private String isAcct;

    /**
     * 是否是分区字段
     */
    private String isPartition;

    /**
     * 1端对象的关联字段id
     */
    private Long relaColumnId;

    /**
     * 1端对象的对象id
     */
    private Long relaKeyObjectId;

    /**
     * 帐期类型
     */
    private String cycleType;

    /**
     * 枚举值
     */
    private List<Map<?, ?>> enumList;

    /**
     * 字段长度
     */
    private Integer columnLength;

    /**
     * 字段精度
     */
    private Integer columnAccuracy;

    /**
     * 字段精度
     */
    private String dateFormat;

    /**
     * 目录下数据的数量
     */
    private Long dirNum;

    /**
     * 原子指标计算方式
     */
    private String func;

    /**
     * 派生指标的原子指标目录
     */
    private String proIndexDir;

    /**
     * 派生指标状态
     */
    private String indexState;

    /**
     * 对象主表id
     */
    private Long mainTableId;

    /**
     * 是否为复制字段
     */
    private String isCopy;

    /**
     * 与对象进行关联的关联id，选择分析对象的relaId为-1
     */
    private Long relaId;

    /**
     * 是否隐藏字段
     */
    private String isHidden;

    /**
     * 隐藏类型，方便判断是否隐藏该类型字段
     */
    private String hiddenDataType;

    /**
     * 关联路径 N端字段路径例子：1，2，3； 选择对象的字段路径：-1； 1端字段路径：-1，4，5，6； 一条完整路径就是1，2，3，-1，4，5，6
     */
    private String path;

    /**
     * 计算字段
     */
    private List<DatasetColumnQo> columnGroup;

    /**
     * 指标单位
     */
    private String dimIndexUnit;

    /**
     * 标签单位
     */
    private String injectionLabelUnit;

    /**
     * 单位名称
     */
    private String unitName;

    /**
     * 字段描述
     */
    private String columnDesc;

    /**
     * 指标状态 1100 下架 1000 上架
     */
    private String statusOpen;

    /**
     * 根目录名称
     */
    private String rootDirName;

    /**
     * 平铺第二级目录字段时，字段使用这个来排序
     */
    private int expandDirectorySort;

    /**
     * 是否层级维度
     */
    private Integer isOrgDimension;

    /**
     * 层级维度最细粒度
     */
    private Integer orgLevel;

    /**
     * 字段关联类型，1-一端；n-N端；0-主分析对象；2-拥有共同一端维度的对象（没有直接关系）；v-虚拟对象
     */
    private String relaType;

    /**
     * 统计类型
     */
    private String dataCntType;

    /**
     * 关联术语
     */
    private String termCode;

    /**
     * 术语对应的字段列表 度量/维度/条件对应的术语所对应的物理字段，范围随着拖拽会变化
     */
    private List<DatasetObjColumnVo> termRelaColumns = new ArrayList<>();

    /**
     * 术语关联物理字段，是否被排除，在拖拽过程中逻辑校验，不符合的排除
     */
    private boolean pass = false;

    /**
     * 指标的输出模式，默认为表模式，湖南移动要支持sq模式
     */
    private String outPutMode = "table";

}