package org.zmz.c.dto.dataopen;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.util.CollectionUtils;
import org.zmz.c.qo.dataopen.DatasetObjColumnVo;
import org.zmz.c.qo.dataopen.ObjKeyTableRelaVo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@ToString
public class ObjRelaDTO {

    /**
     * 直接一端
     */
    private List<Long> oneObjList;

    /**
     * 当前对象信息
     */
    private DatasetObjColumnVo objColumn;

    /**
     * 直接一端个数
     */
    private Integer oneObjSize;

    /**
     * 直接一端和维度交集
     */
    private List<Long> retainObjIdList;

    /**
     * 直接一端和维度交集个数
     */
    private Integer retainObjSize;

    public ObjRelaDTO() {

    }

    /**
     * @param tm           度量对象
     * @param allObjIdList 拖选对象
     * @param relaVoList   直接一端集合
     */
    public ObjRelaDTO(DatasetObjColumnVo tm, List<Long> allObjIdList, List<ObjKeyTableRelaVo> relaVoList) {
        this.objColumn = tm;
        this.oneObjList = CollectionUtils.isEmpty(relaVoList) ? new ArrayList<>(1)
                : relaVoList.stream().map(ObjKeyTableRelaVo::getRelaKeyObjectId).collect(Collectors.toList());
        this.oneObjSize = this.oneObjList.size();
        this.retainObjIdList = new ArrayList<>(allObjIdList);
        retainObjIdList.retainAll(this.oneObjList);
        this.retainObjSize = this.retainObjIdList.size();
    }
}