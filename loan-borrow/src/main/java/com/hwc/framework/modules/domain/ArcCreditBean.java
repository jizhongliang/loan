package com.hwc.framework.modules.domain;

import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by   on 2017/10/30.
 */
@Data
public class ArcCreditBean {
    private Long id;

    /**
     * 用户标识
     */

    private Long userId;

    /**
     * 总额度
     */
    private BigDecimal total;

    /**
     * 额度类型
     */

    private Long creditType;

    /**
     * 评分
     */
    private String grade;

    /**
     * 已使用额度
     */
    private BigDecimal used;

    /**
     * 可使用额度
     */
    private BigDecimal unuse;

    /**
     * 状态 10 -正常 20-冻结
     */
    private String state;
}
