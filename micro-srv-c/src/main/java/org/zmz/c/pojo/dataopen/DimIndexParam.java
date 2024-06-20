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
@Table(name = "dim_index_param")
@NameStyle(value = Style.camelhumpAndLowercase)
public class DimIndexParam {
    private Long paramId;

    private Long dimIndexId;

    private String paramCode;

    private String paramValue;

    private Long comAcctId;

    private Long createStaffId;

    private Long updateStaffId;
}