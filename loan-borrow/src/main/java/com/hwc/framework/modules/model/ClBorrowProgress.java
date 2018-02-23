package com.hwc.framework.modules.model;

import javax.persistence.*;
import java.util.Date;

@Table(name = "cl_borrow_progress")
public class ClBorrowProgress {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 关联用户id
     */
    @Column(name = "user_id")
    private Long userId;

    /**
     * 借款信息id
     */
    @Column(name = "borrow_id")
    private Long borrowId;

    /**
     * 借款进度状态 10申请成功待审核  20自动审核通过 21自动审核不通过  22自动审核未决待人工复审 26人工复审通过 27人工复审不通过    30放款成功  31放款失败   40还款成功    50逾期  90坏账
     */
    private String state;

    /**
     * 进度描述
     */
    private String remark;

    /**
     * 进度生成时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 放款日期
     */
    @Column(name = "loan_time")
    private Date loanTime;

    /**
     * 还款日期
     */
    @Column(name = "repay_time")
    private Date repayTime;

    /**
     * 获取主键
     *
     * @return id - 主键
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置主键
     *
     * @param id 主键
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取关联用户id
     *
     * @return user_id - 关联用户id
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * 设置关联用户id
     *
     * @param userId 关联用户id
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * 获取借款信息id
     *
     * @return borrow_id - 借款信息id
     */
    public Long getBorrowId() {
        return borrowId;
    }

    /**
     * 设置借款信息id
     *
     * @param borrowId 借款信息id
     */
    public void setBorrowId(Long borrowId) {
        this.borrowId = borrowId;
    }

    /**
     * 获取借款进度状态 10申请成功待审核  20自动审核通过 21自动审核不通过  22自动审核未决待人工复审 26人工复审通过 27人工复审不通过    30放款成功  31放款失败   40还款成功    50逾期  90坏账
     *
     * @return state - 借款进度状态 10申请成功待审核  20自动审核通过 21自动审核不通过  22自动审核未决待人工复审 26人工复审通过 27人工复审不通过    30放款成功  31放款失败   40还款成功    50逾期  90坏账
     */
    public String getState() {
        return state;
    }

    /**
     * 设置借款进度状态 10申请成功待审核  20自动审核通过 21自动审核不通过  22自动审核未决待人工复审 26人工复审通过 27人工复审不通过    30放款成功  31放款失败   40还款成功    50逾期  90坏账
     *
     * @param state 借款进度状态 10申请成功待审核  20自动审核通过 21自动审核不通过  22自动审核未决待人工复审 26人工复审通过 27人工复审不通过    30放款成功  31放款失败   40还款成功    50逾期  90坏账
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * 获取进度描述
     *
     * @return remark - 进度描述
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置进度描述
     *
     * @param remark 进度描述
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 获取进度生成时间
     *
     * @return create_time - 进度生成时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置进度生成时间
     *
     * @param createTime 进度生成时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取放款日期
     *
     * @return loan_time - 放款日期
     */
    public Date getLoanTime() {
        return loanTime;
    }

    /**
     * 设置放款日期
     *
     * @param loanTime 放款日期
     */
    public void setLoanTime(Date loanTime) {
        this.loanTime = loanTime;
    }

    /**
     * 获取还款日期
     *
     * @return repay_time - 还款日期
     */
    public Date getRepayTime() {
        return repayTime;
    }

    /**
     * 设置还款日期
     *
     * @param repayTime 还款日期
     */
    public void setRepayTime(Date repayTime) {
        this.repayTime = repayTime;
    }
}