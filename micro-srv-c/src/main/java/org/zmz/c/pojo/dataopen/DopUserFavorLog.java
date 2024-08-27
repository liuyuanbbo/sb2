package org.zmz.c.pojo.dataopen;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @author Zmz
 */
@Getter
@Setter
@TableName("dop_user_favor_log")
public class DopUserFavorLog {
    @TableId
    private Long logId;

    private Long dataId;

    private String dataType;

    private Date favoriteDate;

    private Long userId;

    private Date cancelDate;

    private String statusCd;
}