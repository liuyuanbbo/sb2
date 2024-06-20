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
@Table(name = "obj_key_table_rela")
@NameStyle(value = Style.camelhumpAndLowercase)
public class ObjKeyTableRela {
    private Long relaId;

    private Long objectId;

    private Long metaDataId;

    private String columnCode;

    private String tableCode;

    private String datasourceCode;

    private String relaType;

    private Long columnId;

    private Long relaKeyObjectId;

    private Long relaColumnId;

    private String relaColumnCode;

    private Long relaTableId;

    private String referType;

    private Long srcRelaId;
}