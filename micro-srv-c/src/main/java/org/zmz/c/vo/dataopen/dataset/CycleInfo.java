package org.zmz.c.vo.dataopen.dataset;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CycleInfo {

    private String cycleCode;

    private String cycleExp;

    private String cycleVal;

    private String cycleName;

    private String dateFormat;

    public CycleInfo(String cycleCode, String cycleName, String cycleExp, String dateFormat) {
        this.cycleCode = cycleCode;
        this.cycleName = cycleName;
        this.cycleExp = cycleExp;
        this.cycleVal = "${" + cycleExp + "}";
        this.dateFormat = dateFormat;
    }
}