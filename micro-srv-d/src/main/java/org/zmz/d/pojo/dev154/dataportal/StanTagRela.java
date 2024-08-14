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
@TableName(value = "stan_tag_rela")
public class StanTagRela {
    @TableId
    private Long relaId;

    private Long tagId;

    private String dataType;

    private Long dataId;

    private Date createDate = new Date();

    private Date updateDate = new Date();

    private String statusCd;

    private Long createStaff;

    private String createStaffCode;

}