package org.zmz.c.vo.dataopen.dataindex;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ObjKeyTableColumnVo implements Serializable {

    private String tableCode;

    private String columnId;

    private String columnCode;

    private String relaTableCode;

    private String relaColumnId;

    private String relaColumnCode;

    private String relaKeyObjectId;

}