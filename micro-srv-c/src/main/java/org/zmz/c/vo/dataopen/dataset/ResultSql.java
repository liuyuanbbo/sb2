package org.zmz.c.vo.dataopen.dataset;

import lombok.Getter;
import lombok.Setter;
import org.zmz.c.qo.dataopen.DatasetColumnQo;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class ResultSql {

    /**
     * 是否单个sql,只有层级字段等于1个和度量分组等于1个的时候 isSingle=true
     * true则在子查询就添加字段注释、字段隐藏、字段枚举、权限字段、周期性调度sql添加账期占位符、计算字段
     */
    public boolean isSingle = true;

    public boolean isGroupBy = true;

    /**
     * sql预览语句
     */
    public List<String> sqlLists = new ArrayList<>();

    public Map<Long, List<DatasetColumnQo>> metricsGroup;

    /**
     * 调度度量关联表sql语句
     */
    public List<String> mergeSqls = new ArrayList<>();

    /**
     * 临时表sql
     */
    public Map<String, String> tmpTableSql = new LinkedHashMap<>();

    /**
     * 临时表别名
     */
    public Map<String, String> tmpTableNames = new LinkedHashMap<>();

}