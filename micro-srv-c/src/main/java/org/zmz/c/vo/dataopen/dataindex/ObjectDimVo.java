package org.zmz.c.vo.dataopen.dataindex;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Zmz
 */
@Getter
@Setter
public class ObjectDimVo implements Serializable {

    private Long objectId;

    private Long parentId;

    private Long grpId;

    private String objectCode;

    private String objectName;

    private String primaryKey;

    private Long metaTableId;

    private String tableCode;

    private String statusCd;

    private String createType;

    private Long datasourceId;

    private String tableType;

    private String objectAlias;

    private String keyCode;
}