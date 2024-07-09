package org.zmz.c.vo.dataopen.common;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class CommonRegion implements Serializable {

    // 区域id
    private Long commonRegionId;

    // 父区域id
    private Long parRegionId;

    private String regionName;

    // 区域编码
    private String regionNbr;

    // 状态；1000: 有效;1100: 无效;1200: 未生效;1300: 删除;1400: 审批中;1500: 审批不通过;1600: 审批通过;1700: 指标配置生成编排;1800: 上架待审批;1900: 下架待待审批;
    private String statusCd;

    private String pathCode;
}