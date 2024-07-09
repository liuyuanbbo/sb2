package org.zmz.c.vo.dataopen.common;

import lombok.Data;

import java.io.Serializable;

@Data
public class PrivInfo implements Serializable {

    private Long privId;

    private String privCode;

    private String privName;

    private String privType;

}