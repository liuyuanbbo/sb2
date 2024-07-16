package org.zmz.c.test.obj;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
public class SimpleVo {
    /**
     * 上级表所属对象（度量所属对象）
     */
    private Long srcObjectId;

    /**
     * 上级表标识（度量所属表）
     */
    private Long srcTableId;

    /**
     * 上级表编码（度量所属表）
     */
    private String srcTableCode;

    /**
     * 下级关联表所属对象
     */
    private Long tgtObjectId;

    /**
     * 下级关联表标识
     */
    private Long tgtTableId;

    /**
     * 下级关联表编码
     */
    private String tgtTableCode;

    public SimpleVo(Long srcObjectId, Long srcTableId, String srcTableCode, Long tgtObjectId, Long tgtTableId, String tgtTableCode) {
        this.srcObjectId = srcObjectId;
        this.srcTableId = srcTableId;
        this.srcTableCode = srcTableCode;
        this.tgtObjectId = tgtObjectId;
        this.tgtTableId = tgtTableId;
        this.tgtTableCode = tgtTableCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SimpleVo simpleVo = (SimpleVo) o;
        return Objects.equals(srcTableId, simpleVo.srcTableId) &&
                Objects.equals(srcTableCode, simpleVo.srcTableCode) &&
                Objects.equals(tgtTableId, simpleVo.tgtTableId) &&
                Objects.equals(tgtTableCode, simpleVo.tgtTableCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(srcObjectId, srcTableId, srcTableCode, tgtObjectId, tgtTableId, tgtTableCode);
    }
}
