package org.zmz.c.vo.dataopen.dataset;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.zmz.c.pojo.dataopen.ObjInfo;
import org.zmz.c.qo.dataopen.ObjKeyColumnRelaVo;
import org.zmz.c.qo.dataopen.ObjRelaTreeColumnVo;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
public class ObjRelaTreeVo {

    /**
     * 当前对象id
     */
    private Long objectId;

    /**
     * 关联id
     */
    private Long relaId;

    /**
     * 当前对象的N端对象id
     */
    private Long oneToNObjectId;

    /**
     * N端对象关联表id
     */
    private Long oneToNTableId;

    /**
     * N端对象关联表编码
     */
    private String oneToNTableCode;

    /**
     * 当前对象id
     */
    private Long relaKeyObjectId;

    /**
     * 父级目录
     */
    private String parentDir;

    /**
     * 关联路径
     */
    private String path;

    /**
     * 当前对象关联表id
     */
    private Long relaTableId;

    /**
     * 当前对象关联表编码
     */
    private String relaTableCode;

    List<ObjRelaTreeColumnVo> relaColumns;

    private List<ObjKeyColumnRelaVo> keyColumnRelas = new ArrayList<>();

    /**
     * 当前对象信息
     */
    private ObjInfo objInfo;

    private List<ObjRelaTreeVo> children = new ArrayList<>();
}