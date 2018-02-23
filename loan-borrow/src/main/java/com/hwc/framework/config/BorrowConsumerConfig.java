package com.hwc.framework.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by   on 2017/11/28.
 */
@ConfigurationProperties(prefix = "ons.borrow.consumer")
@Component
@Data
public class BorrowConsumerConfig {
    private String applyBorrowId;
    private String applyBorrowTag;

    private String refuseBorrowId;
    private String refuseBorrowTag;

    private String borrowPassId;
    private String borrowPassTag;

    private String borrowOverdueId;
    private String borrowOverdueTag;

    private String repayExpireId;
    private String repayExpireTag;

    private String mortgageApplyId;
    private String mortgageApplyTag;

    private String mortgageTrailId;
    private String mortgageTrailTag;

    private String mortgageRefuseId;
    private String mortgageRefuseTag;

    private String mortgageReviewId;
    private String mortgageReviewTag;

    private String mortgageAuthId;
    private String mortgageAuthTag;

    private String mortgageWithdrawalsId;
    private String mortgageWithdrawalsTag;
    
    private String paySuccessId;
    private String paySuccessTag;
    
    private String repaySuccessId;
    private String repaySuccessTag;

}
