package org.zmz.c.pojo.dataopen;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import tk.mybatis.mapper.annotation.NameStyle;
import tk.mybatis.mapper.code.Style;

import javax.persistence.Table;

@Getter
@Setter
@ToString
@Table(name = "obj_column_rela")
@NameStyle(value = Style.camelhumpAndLowercase)
public class ObjColumnRela {
    private Long relaId;

    private Long objectId;

    private Long srcObjectId;

    private Long columnId;

    private String columnCode;

    private String columnName;

    private String columnType;

    private String tableCode;

    private Long srcTableId;

    private Long datasourceId;

    private String datasourceCode;

    private String colAttr;

    private Long dataId;

    private String dataName;

    private String statusCd;

    private Long comAcctId;

    private Long srcColumnId;

    private Long seq;
}