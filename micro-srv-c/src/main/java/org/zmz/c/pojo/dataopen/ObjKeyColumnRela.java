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
@Table(name = "obj_key_column_rela")
@NameStyle(value = Style.camelhumpAndLowercase)
public class ObjKeyColumnRela {
    private Long relaId;

    private Long parentRelaId;

    private Long objectId;

    private String columnCode;

    private Long columnId;

    private Long relaColumnId;

    private String relaColumnCode;
}