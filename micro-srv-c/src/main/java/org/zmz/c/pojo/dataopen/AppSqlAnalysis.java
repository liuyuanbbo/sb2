package org.zmz.c.pojo.dataopen;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import tk.mybatis.mapper.annotation.NameStyle;
import tk.mybatis.mapper.code.Style;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author Zmz
 */
@Getter
@Setter
@ToString
@Table(name = "app_sql_analysis")
@NameStyle(value = Style.camelhumpAndLowercase)
public class AppSqlAnalysis {
    @TableId
    @Id
    private Long analysisId;

    private String analysisName;

    private String analysisDesc;

    private Long grpId;

    private String statusCd;

    private Date createDate;

    private String creator;

    private Date updateDate;

    private String updator;

    private String createStaffCode;

    private Integer comAcctId;

    private String version;

}