package org.zmz.c.pojo.dataopen;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Table;

@Setter
@Getter
@Table(name = "ZMGR_OMP_FIELD_TYPE_VALUE_MAP")
public class ZmgrOmpFieldTypeValueMap {

    private Long id;

    private String typeValue;

    private String typeString;

    private String tableType;
}
