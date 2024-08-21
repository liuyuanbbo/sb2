package org.zmz.d.pojo.dev154.dataopen;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
@TableName(value = "obj_info")
public class ObjInfo {
    @TableId
    private Long objectId;

    private Long parentId;

    private Long grpId;

    private String objectCode;

    private String objectName;

    private String primaryKey;

    private Long metaTableId;

    private String tableCode;

    private String statusCd;

    private String layer;

    private String domain;

    private String comments;

    private String createType;

    private Date createDate;

    private Long operId;

    private Long comAcctId;

    private Long dirId;

    private Long datasourceId;

    private String tableType;

    private String primaryKeys;

    private String dataCntType;

    private String objectAlias;

    private Long projectId;

    private String keyCode;

    private Long orderNo;

    private String version;
}