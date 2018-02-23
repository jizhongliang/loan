package com.hwc.framework.modules.model;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Table(name = "cl_overdue_fine_log")
public class CLOverdueFineLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "repay_id")
    private Long repayId;

    @Column(name = "borrow_id")
    private Long borrowId;

    private BigDecimal total;

    private Integer day;

    private BigDecimal fine;

    private BigDecimal amount;

    private BigDecimal rate;

    @Column(name = "create_time")
    private Date createTime;

}