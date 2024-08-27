package org.zmz.c.pojo.dataopen;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Zmz
 */
@Getter
@Setter
public class FlowTacheEvent {
    private Long flowTacheId;

    private String flowTypeId;

    private String finishHandler;

    private String failHandler;

    private String state;

    private String auditTacheType;
}