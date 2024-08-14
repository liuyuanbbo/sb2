package org.zmz.d.pojo.dev154.dataportal;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * @author Zmz
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode
@TableName(value = "stan_tag")
public class StanTag {
    @TableId
    private Long tagId;

    private String tagName;

    private String tagDesc;

    private Long grpId;

    private Long heatValue;

    private Date createDate = new Date();

    private Date updateDate = new Date();

    private String statusCd;

    private Long createStaff;

    private String createStaffCode;

    private Long updateStaff;

    private String createType;

    private String icon;
}
