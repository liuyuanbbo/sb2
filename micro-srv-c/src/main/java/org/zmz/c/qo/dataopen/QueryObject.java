package org.zmz.c.qo.dataopen;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class QueryObject implements Serializable {

    private Integer page = 1;

    private Integer rowNum = 5;

    private Integer pageIndex = 1;

    private Integer pageSize = 5;

    /**
     * 添加创建人
     */
    private Long createStaff;

    /**
     * 添加企业comAcctId
     */

    private Long comAcctId;

    /**
     * 角色
     */
    private List<Long> roleIds;

    /**
     * 当前登录人所属区域路径
     */
    private String regionPathCode;

    /**
     * 当前登录人所属组织路径
     */
    private String orgPathCode;

    /**
     * 国际化
     */
    private String language;

    /**
     * 可见范围授权
     */
    private List<Long> authObjIds;

    /**
     * 可见范围授权
     */
    private Long authObjId;

    /**
     * 授权对象类型
     */
    private String authObjType;

    /**
     * 授权类型 R 可读，M 过滤
     */
    private String privType;

    /**
     * 项目ID
     */
    private Long projectId;

    /**
     * 兼容旧的参数
     *
     * @param page 当前页码
     */
    public void setPage(Integer page) {
        this.page = page;
        this.pageIndex = page;
    }

    /**
     * 兼容旧的入参
     *
     * @param rowNum 页面大小
     */
    public void setRowNum(Integer rowNum) {
        this.rowNum = rowNum;
        this.pageSize = rowNum;
    }

    /**
     * 分隔符，通常用来传逗号
     */
    private String separator;

    /**
     * 适配pageIndex pageSize
     */
    public Integer getPage() {
        if (pageIndex != null && pageIndex != 1) {
            return pageIndex;
        }
        return page;
    }

    public Integer getRowNum() {
        if (pageSize != null && pageSize != 5) {
            return pageSize;
        }
        return rowNum;
    }

    /**
     * 单极目录查询
     */
    private Long parentGrpId;

}