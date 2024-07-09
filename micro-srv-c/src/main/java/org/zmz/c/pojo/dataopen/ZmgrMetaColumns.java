package org.zmz.c.pojo.dataopen;

import cn.hutool.core.util.StrUtil;
import lombok.Getter;
import lombok.Setter;
import org.zmz.c.qo.dataopen.DeriveDimIndexInfo;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Setter
@Getter
@Table(name = "ZMGR_META_COLUMNS")
public class ZmgrMetaColumns {
    @Id
    private Long columnId;

    private Long metaTableId;

    private String columnCode;

    private String columnName;

    private String columnType;

    private String columnLength;

    private String dateType;

    private String columnAccuracy;

    private String columnConstraint;

    private String columnDesc;

    private Integer seq;

    private String mergeColumnCode;

    private String statusCd;

    private String constraintOptionParams;

    private String defaultValue;

    private String columnEmpty;

    private String uuid;

    private String prefix;

    private Long comAcctId;

    private Long userLevel;

    /**
     * 扩展表合并过来的字段
     */
    private String isLan;

    private String lanCodeRule;

    private String isAcct;

    private String isPrimary;

    private String isPartition;

    private String isDesensitized;

    private String desensitizedAlgorithm;

    private String colAttr;

    private Long dimId;

    private String columnSecurityLevel;

    private String techDesc;

    private String busiDesc;

    private Long grpId;

    private String isNull;

    /**
     * 自定义属性
     */
    private String customize;

    private String isIndex;

    /**
     * 同 dimId
     */
    private Long dimDimentionId;

    /**
     * 原始字段id
     */
    @Transient
    private Long srcColumnId;

    /**
     * 原始字段编码，用于字段编码重命名
     */
    @Transient
    private String srcColumnCode;

    /**
     * 输出类型 2 普通 4 指标 5 标签
     */
    @Transient
    private String outputType;

    public ZmgrMetaColumns() {
    }

    public ZmgrMetaColumns(DeriveDimIndexInfo dimIndexInfo) {
        if (dimIndexInfo != null) {
            this.setColumnCode(dimIndexInfo.getFieldCode());
            this.setColumnName(dimIndexInfo.getDimIndexName());
            this.setColumnType(dimIndexInfo.getDataType());
        }
    }

    /**
     * 编辑宽表时判断字段是否有修改
     */
    public boolean metaColumnIsChange(Object obj) {
        // 地址相等
        if (this == obj) {
            return false;
        }

        // 非空性：对于任意非空引用x，x.equals(null)应该返回false。
        if (obj == null) {
            return true;
        }

        if (obj instanceof ZmgrMetaColumns) {
            ZmgrMetaColumns otherCol = (ZmgrMetaColumns) obj;
            // 需要比较的字段相等，则这两个对象相等
            if (!StrUtil.equals(isDesensitized, otherCol.getIsDesensitized())) {
                return true;
            }
            if (!StrUtil.equals(desensitizedAlgorithm, otherCol.getDesensitizedAlgorithm())) {
                return true;
            }
            if (!StrUtil.equals(columnSecurityLevel, otherCol.getColumnSecurityLevel())) {
                return true;
            }
            if (!StrUtil.equals(colAttr, otherCol.getColAttr())) {
                return true;
            }
            if (!StrUtil.equals(columnLength, otherCol.getColumnLength())) {
                return true;
            }
            if (!StrUtil.equals(columnDesc, otherCol.getColumnDesc())) {
                return true;
            }
            if (!StrUtil.equals(customize, otherCol.getCustomize())) {
                return true;
            }
            if (!StrUtil.equals(isLan, otherCol.getIsLan())) {
                return true;
            }
            if (!StrUtil.equals(isAcct, otherCol.getIsAcct())) {
                return true;
            }
            if (!StrUtil.equals(lanCodeRule, otherCol.getLanCodeRule())) {
                return true;
            }
            if (!StrUtil.equals(busiDesc, otherCol.getBusiDesc())) {
                return true;
            }
            return !StrUtil.equals(techDesc, otherCol.getTechDesc());

        }
        return false;
    }
}