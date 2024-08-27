package org.zmz.c.pojo.dataopen;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import tk.mybatis.mapper.annotation.NameStyle;
import tk.mybatis.mapper.code.Style;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Zmz
 */
@Getter
@Setter
@ToString
@Table(name = "obj_key_column_rela")
@NameStyle(value = Style.camelhumpAndLowercase)
public class ObjKeyColumnRela {
    @TableId
    @Id
    private Long relaId;

    private Long parentRelaId;

    private Long objectId;

    private String columnCode;

    private Long columnId;

    private Long relaColumnId;

    private String relaColumnCode;

    /**
     * N端字段的加工函数
     */
    public String func;

    /**
     * 1端字段的加工函数
     */
    public String relaFunc;
}