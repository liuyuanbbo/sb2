package org.zmz.c.qo.dataopen;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Setter
@Getter
public class AcctOffsetConf implements Serializable {
    /**
     * 表与表账期偏移，左表账期=右表账期减1
     */
    private List<Long> leftTableIds;

    private List<Long> rightTableIds;
}