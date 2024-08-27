package org.zmz.c.pojo.dataopen;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import tk.mybatis.mapper.annotation.NameStyle;
import tk.mybatis.mapper.code.Style;

import javax.persistence.Table;
import java.time.LocalDateTime;

/**
 * @author Zmz
 */
@Getter
@Setter
@ToString
@Table(name = "data_grp")
@NameStyle(value = Style.camelhumpAndLowercase)
public class DataGrp {
    private Long grpId;

    private String grpName;

    private String grpDesc;

    private String grpType;

    private String statusCd;

    private Long createStaff;

    private Long updateStaff;

    private LocalDateTime createDate;

    private LocalDateTime statusDate;

    private LocalDateTime updateDate;

    private String remark;

    private Long parentGrpId;

    private String pathCode;

    private String grpCode;

    private String grpNameEn;

    private Long comAcctId;

    private String isLeaf;

    private Long relGrpId;

    private Long coderuleId;
}