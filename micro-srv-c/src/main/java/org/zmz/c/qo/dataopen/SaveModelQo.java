package org.zmz.c.qo.dataopen;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SaveModelQo extends ModelInfo {

    private String projectId = "-1";

    /**
     * 是否立即提交，默认1
     */
    private String isImmediatelyCommit = "1";

    private String versionDesc;

}