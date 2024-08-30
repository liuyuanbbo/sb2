package org.zmz.c.vo.dataopen.dataindex;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Zmz
 */
@Getter
@Setter
public class DataGrpVo implements Serializable {

    protected Long grpId;

    protected Long parentGrpId;

    protected String grpName;

    protected String grpCode;

    protected Long totalNum;
}