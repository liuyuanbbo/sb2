package org.zmz.c.pojo.dataopen;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import tk.mybatis.mapper.annotation.NameStyle;
import tk.mybatis.mapper.code.Style;

import javax.persistence.Table;

/**
 * @author Zmz
 */
@Getter
@Setter
@ToString
@Table(name = "obj_table_rela")
@NameStyle(value = Style.camelhumpAndLowercase)
public class ObjTableRela {
    private Long relaId;

    private Long objectId;

    private Long metaDataId;

    private String metaDataCode;

    private Integer mainFlag;

    private String metaDataType;

    private String statusCd;

    private Long comAcctId;

    private String datasourceCode;

    private String dataCycle;
}