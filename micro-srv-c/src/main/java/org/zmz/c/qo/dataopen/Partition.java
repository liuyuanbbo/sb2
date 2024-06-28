package org.zmz.c.qo.dataopen;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class Partition implements Serializable {

    private Long partitionId;

    private Long metaDataId;

    private Long columnId;

    private String partitionCode;

    private String columnCode;

    private String partitionName;

    private String partitionSort;

    private String partitionValue;

    private String partitionType;

    public void setPartitionName(String partitionName) {
        this.partitionName = partitionName != null ? partitionName.replace("'", "") : null;
    }
}