package org.zmz.c.qo.dataopen;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class OrgDimension implements Serializable {

    /**
     * orgId orgName orgLevel
     */
    private String orgIdColumnCode;

    private String orgNameColumnCode;

    private String orgLevel;

    private Long metaDataId;

    /**
     * 嵌套后，别名变化
     */
    private boolean isAlias = false;
}