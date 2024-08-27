package org.zmz.c.pojo.dataopen;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import tk.mybatis.mapper.annotation.NameStyle;
import tk.mybatis.mapper.code.Style;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Zmz
 */
@Getter
@Setter
@ToString
@Table(name = "app_sql_group")
@NameStyle(value = Style.camelhumpAndLowercase)
public class AppSqlGroup {
    @TableId
    @Id
    private Long groupId;

    private String groupName;

    private String groupType;

    private String statusCd;

    private Integer seq;

    private Long appId;

}