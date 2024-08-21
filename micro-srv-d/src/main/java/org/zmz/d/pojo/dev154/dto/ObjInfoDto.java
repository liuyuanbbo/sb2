package org.zmz.d.pojo.dev154.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

/**
 * @author Zmz
 */
@Getter
@Setter
public class ObjInfoDto {
    private String objectName;

    private String objectCode;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ObjInfoDto that = (ObjInfoDto) o;
        return Objects.equals(objectName, that.objectName) && Objects.equals(objectCode, that.objectCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(objectName, objectCode);
    }
}
