package org.zmz.c.qo.dataopen;

import lombok.Getter;
import lombok.Setter;
import org.zmz.c.pojo.dataopen.DataSetCondition;

import java.io.Serializable;

@Getter
@Setter
public class ProRestrictColumnVo extends DataSetCondition implements Serializable {

    /**
     * 字段类型，process业务过程，object业务对象，label标签
     */
    private String columnType;

    /**
     * 是否是名称字段
     */
    private Integer isNameColumn;

    private Long labelId;

    private String labelValueType;

    private String isAcct;

    private String cycleType;

    private String dateFormat;
}