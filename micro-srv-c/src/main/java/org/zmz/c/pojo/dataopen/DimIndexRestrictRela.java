package org.zmz.c.pojo.dataopen;

import lombok.Data;
import tk.mybatis.mapper.annotation.NameStyle;
import tk.mybatis.mapper.code.Style;

import javax.persistence.Table;

@Data
@Table(name = "dim_index_restrict_rela")
@NameStyle(value = Style.camelhumpAndLowercase)
public class DimIndexRestrictRela {

    /**
     * 关联标识
     */
    public Long relaId;

    /**
     * 派生指标标识
     */
    public Long indexId;

    /**
     * 业务限定标识
     */
    public Long restrictId;

    /**
     * 业务限定版本号
     */
    public String restrictVersion;

    /**
     * 状态 00A有效 00X无效
     */
    public String state;
}