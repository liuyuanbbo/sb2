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
@Table(name = "dim_index_key_rela")
@NameStyle(value = Style.camelhumpAndLowercase)
public class DimIndexKeyRela {
    private Long relaId;

    private Long dimIndexId;

    private String tableCode;

    private Long relaKeyObjectId;

    private Long srcRelaId;

    private String statusCd;

    private String dataType;
}