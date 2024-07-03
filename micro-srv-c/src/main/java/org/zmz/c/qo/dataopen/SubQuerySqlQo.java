package org.zmz.c.qo.dataopen;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SubQuerySqlQo {

    private List<DatasetColumnQo> metricList;

    private List<DatasetColumnQo> dimensionList;

    private String sql;

    private String dimensionType;

    private String tbAlisa;
}