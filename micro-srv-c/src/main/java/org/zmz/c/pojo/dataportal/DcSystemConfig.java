package org.zmz.c.pojo.dataportal;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Table(name = "DC_SYSTEM_CONFIG")
public class DcSystemConfig implements Serializable {

    private Long paramId;

    @Id
    private String paramCode;

    private String paramValue;

    private String paramDesc;

    private String paramGroupCode;

    private String paramGroupName;

    private String paramGroupEnName;

    private Integer paramSeq;

    private String paramName;

    private String paramEnName;

    private String paramText;

    private String paramType;

}