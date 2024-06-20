package org.zmz.model;

import java.util.Date;

public class SApplyOrder {
    private Integer orderId;

    private String orderCode;

    private String orderName;

    private String flowTypeId;

    private Integer applyId;

    private String curTacheName;

    private Integer curTacheId;

    private String dealObjType;

    private Long dealObjId;

    private Date createDate;

    private Integer createStaffId;

    private Integer dealStaffId;

    private String dealContent;

    private String dealResult;

    private Date dealDate;

    private String state;

    private Integer returnTacheId;

    private Integer dealType;

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode == null ? null : orderCode.trim();
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName == null ? null : orderName.trim();
    }

    public String getFlowTypeId() {
        return flowTypeId;
    }

    public void setFlowTypeId(String flowTypeId) {
        this.flowTypeId = flowTypeId == null ? null : flowTypeId.trim();
    }

    public Integer getApplyId() {
        return applyId;
    }

    public void setApplyId(Integer applyId) {
        this.applyId = applyId;
    }

    public String getCurTacheName() {
        return curTacheName;
    }

    public void setCurTacheName(String curTacheName) {
        this.curTacheName = curTacheName == null ? null : curTacheName.trim();
    }

    public Integer getCurTacheId() {
        return curTacheId;
    }

    public void setCurTacheId(Integer curTacheId) {
        this.curTacheId = curTacheId;
    }

    public String getDealObjType() {
        return dealObjType;
    }

    public void setDealObjType(String dealObjType) {
        this.dealObjType = dealObjType == null ? null : dealObjType.trim();
    }

    public Long getDealObjId() {
        return dealObjId;
    }

    public void setDealObjId(Long dealObjId) {
        this.dealObjId = dealObjId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Integer getCreateStaffId() {
        return createStaffId;
    }

    public void setCreateStaffId(Integer createStaffId) {
        this.createStaffId = createStaffId;
    }

    public Integer getDealStaffId() {
        return dealStaffId;
    }

    public void setDealStaffId(Integer dealStaffId) {
        this.dealStaffId = dealStaffId;
    }

    public String getDealContent() {
        return dealContent;
    }

    public void setDealContent(String dealContent) {
        this.dealContent = dealContent == null ? null : dealContent.trim();
    }

    public String getDealResult() {
        return dealResult;
    }

    public void setDealResult(String dealResult) {
        this.dealResult = dealResult == null ? null : dealResult.trim();
    }

    public Date getDealDate() {
        return dealDate;
    }

    public void setDealDate(Date dealDate) {
        this.dealDate = dealDate;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    public Integer getReturnTacheId() {
        return returnTacheId;
    }

    public void setReturnTacheId(Integer returnTacheId) {
        this.returnTacheId = returnTacheId;
    }

    public Integer getDealType() {
        return dealType;
    }

    public void setDealType(Integer dealType) {
        this.dealType = dealType;
    }
}