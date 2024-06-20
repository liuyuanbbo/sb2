package org.zmz.c.pojo.dataopen;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import tk.mybatis.mapper.annotation.NameStyle;
import tk.mybatis.mapper.code.Style;

import javax.persistence.Table;
import java.util.Date;

@Getter
@Setter
@ToString
@Table(name = "s_service_apply")
@NameStyle(value = Style.camelhumpAndLowercase)
public class SServiceApply {
    private Long applyId;

    private Long relaId;

    private Long relaApplyId;

    private String applyCode;

    private String applyName;

    private Long serviceId;

    private String serviceType;

    private String serviceSubType;

    private Long smartId;

    private Date effDate;

    private Date expDate;

    private Date applyDate;

    private Long applyStaffId;

    private String applyReason;

    private Long custId;

    private String state;

    private Date stateDate;

    private String srcType;

    private Long taskId;

    private Long dnId;

    private Long createStaffId;

    private String electronicId;

    private Long systemId;

    private Long metaTableId;

    private String srcSystem;

    private String pageUrl;

    private String receiveUrl;

    private String isElecontron;

    private Long newApplyId;

    private Long fileId;

    private Long projectId;

    private String projectName;

    private String tenantCode;

    private String serviceName;

    private String clusterType;

    private String clusterConnectString;

    private String superTennantCode;

    private String datasourceCode;

    private Long datasourceId;

    private String channel;

    private String applyState;

    private String renewType;

    private String renewTimeData;

    private Long templateId;

    private String formObj;
}