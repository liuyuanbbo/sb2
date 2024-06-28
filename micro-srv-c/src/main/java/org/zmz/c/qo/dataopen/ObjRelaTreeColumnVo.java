package org.zmz.c.qo.dataopen;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class ObjRelaTreeColumnVo implements Serializable {

    /**
     * N端对象关联字段id
     */
    private Long oneToNColumnId;

    /**
     * N端对象关联字段编码
     */
    private String oneToNColumnCode;

    /**
     * 当前对象关联字段id
     */
    private Long relaColumnId;

    /**
     * 当前对象关联字段编码
     */
    private String relaColumnCode;

    public ObjRelaTreeColumnVo() {
    }

    public ObjRelaTreeColumnVo(Long oneToNColumnId, String oneToNColumnCode, Long relaColumnId, String relaColumnCode) {
        this.oneToNColumnId = oneToNColumnId;
        this.oneToNColumnCode = oneToNColumnCode;
        this.relaColumnId = relaColumnId;
        this.relaColumnCode = relaColumnCode;
    }
}
