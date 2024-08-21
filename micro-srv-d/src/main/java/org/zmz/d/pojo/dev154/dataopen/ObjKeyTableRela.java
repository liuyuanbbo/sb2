package org.zmz.d.pojo.dev154.dataopen;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@TableName(value = "obj_key_table_rela")
public class ObjKeyTableRela {
    @TableId
    private Long relaId;

    private Long objectId;

    private Long metaDataId;

    private String columnCode;

    private String tableCode;

    private String datasourceCode;

    private String relaType;

    private Long columnId;

    private Long relaKeyObjectId;

    private Long relaColumnId;

    private String relaColumnCode;

    private Long relaTableId;

    private String referType;

    private Long srcRelaId;
}