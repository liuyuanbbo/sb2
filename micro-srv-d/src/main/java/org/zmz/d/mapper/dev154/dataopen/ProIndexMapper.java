package org.zmz.d.mapper.dev154.dataopen;

import org.zmz.d.pojo.dev154.dataopen.ProIndex;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.Set;

/**
 * @author Zmz
 */
public interface ProIndexMapper extends BaseMapper<ProIndex> {
    // 查询所有原子指标 Id
    Set<Long> selectProIndexIds();
}
