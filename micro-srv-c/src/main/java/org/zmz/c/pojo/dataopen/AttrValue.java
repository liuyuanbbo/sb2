package org.zmz.c.pojo.dataopen;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import tk.mybatis.mapper.annotation.NameStyle;
import tk.mybatis.mapper.code.Style;

import javax.persistence.Table;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Table(name = "attr_value")
@NameStyle(value = Style.camelhumpAndLowercase)
public class AttrValue {
    private Long attrValueId;

    private Long attrId;

    private String attrValueName;

    private String attrValue;

    private String statusCd;

    private String attrValueDesc;

    private LocalDateTime createDate;

    private LocalDateTime statusDate;

    private LocalDateTime updateDate;

    private Long createStaff;

    private Long updateStaff;

    private Integer attrValueSort;

    private String parentValue;

    private String attrValueEnglishName;

    private String attrValueTwName;

    private String remark;
}