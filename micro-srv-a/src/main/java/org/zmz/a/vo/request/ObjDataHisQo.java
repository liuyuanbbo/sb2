package org.zmz.a.vo.request;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class ObjDataHisQo implements Serializable {
    private static final long serialVersionUID = 1234567890L;
    private String databaseProvider = "mysql";
    private Long dataId;
    private String dataType;
    private Long versionId;
}