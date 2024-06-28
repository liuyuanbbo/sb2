package org.zmz.c.qo.dataopen;

import lombok.Getter;
import lombok.Setter;
import org.zmz.c.pojo.dataopen.ObjKeyColumnRela;

import java.util.Objects;

@Getter
@Setter
public class ObjKeyColumnRelaVo extends ObjKeyColumnRela {

    private String columnName;

    /**
     * 一端关联字段
     */
    private String relaColumnName;

    /**
     * 一端关联字段
     */
    private String relaColumnType;

    private Integer relaColumnLength;

    private Integer relaColumnAccuracy;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ObjKeyColumnRelaVo that = (ObjKeyColumnRelaVo) o;
        return Objects.equals(this.getColumnCode(), that.getColumnCode()) &&
                Objects.equals(this.getColumnId(), that.getColumnId()) &&
                Objects.equals(this.getRelaColumnId(), that.getRelaColumnId()) &&
                Objects.equals(this.getRelaColumnCode(), that.getRelaColumnCode());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getColumnCode(), this.getColumnId(), this.getRelaColumnId(), this.getRelaColumnCode());
    }
}
