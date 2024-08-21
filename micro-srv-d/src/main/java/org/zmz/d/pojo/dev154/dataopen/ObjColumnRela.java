package org.zmz.d.pojo.dev154.dataopen;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@TableName(value = "obj_column_rela")
public class ObjColumnRela {
    @TableId
    private Long relaId;

    private Long objectId;

    private Long srcObjectId;

    private Long srcColumnId;

    private Long columnId;

    private String columnCode;

    private String columnName;

    private String columnType;

    private String tableCode;

    private Long srcTableId;

    private Long datasourceId;

    private String datasourceCode;

    private Long dataId;

    private String dataName;

    private String statusCd;

    private Long comAcctId;

    private Long seq;
}