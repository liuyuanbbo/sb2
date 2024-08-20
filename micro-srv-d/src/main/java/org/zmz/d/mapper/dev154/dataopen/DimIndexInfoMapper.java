package org.zmz.d.mapper.dev154.dataopen;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.zmz.d.pojo.dev154.dataopen.DimIndexInfo;

import java.util.Set;

/**
 * @author Zmz
 */
public interface DimIndexInfoMapper extends BaseMapper<DimIndexInfo> {
    // 查询所有派生指标 Id
    Set<Long> selectDimIndexIds();
}
