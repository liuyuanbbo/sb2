package org.zmz.d.pojo.dev154.dataopen;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@TableName(value = "obj_key_column_rela")
public class ObjKeyColumnRela {
    @TableId
    private Long relaId;

    private Long parentRelaId;

    private Long objectId;

    private String columnCode;

    private Long columnId;

    private Long relaColumnId;

    private String relaColumnCode;

    private String func;

    private String relaFunc;
}