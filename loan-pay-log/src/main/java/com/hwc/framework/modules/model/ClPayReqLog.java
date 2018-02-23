package com.hwc.framework.modules.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "cl_pay_req_log")
public class ClPayReqLog {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 第三方接口名称
     */
    private String service;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "borrow_id")
    private Long borrowId;

    /**
     * 商户订单编号
     */
    @Column(name = "order_no")
    private String orderNo;

    /**
     * 页面返回/同步回调时间
     */
    @Column(name = "return_time")
    private Date returnTime;

    /**
     * 后台通知/异步回调时间
     */
    @Column(name = "notify_time")
    private Date notifyTime;

    /**
     * 响应结果：1成功，-1失败
     */
    private Integer result;

    /**
     * 添加时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 请求IP
     */
    private String ip;

    /**
     * 请求参数
     */
    private String params;

    /**
     * 请求tpp参数拼接
     */
    @Column(name = "req_detail_params")
    private String reqDetailParams;

    /**
     * 页面返回/同步回调参数
     */
    @Column(name = "return_params")
    private String returnParams;

    /**
     * 后台通知/异步回调参数
     */
    @Column(name = "notify_params")
    private String notifyParams;

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
     * 获取第三方接口名称
     *
     * @return service - 第三方接口名称
     */
    public String getService() {
        return service;
    }

    /**
     * 设置第三方接口名称
     *
     * @param service 第三方接口名称
     */
    public void setService(String service) {
        this.service = service;
    }

    /**
     * @return user_id
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * @param userId
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * @return borrow_id
     */
    public Long getBorrowId() {
        return borrowId;
    }

    /**
     * @param borrowId
     */
    public void setBorrowId(Long borrowId) {
        this.borrowId = borrowId;
    }

    /**
     * 获取商户订单编号
     *
     * @return order_no - 商户订单编号
     */
    public String getOrderNo() {
        return orderNo;
    }

    /**
     * 设置商户订单编号
     *
     * @param orderNo 商户订单编号
     */
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    /**
     * 获取页面返回/同步回调时间
     *
     * @return return_time - 页面返回/同步回调时间
     */
    public Date getReturnTime() {
        return returnTime;
    }

    /**
     * 设置页面返回/同步回调时间
     *
     * @param returnTime 页面返回/同步回调时间
     */
    public void setReturnTime(Date returnTime) {
        this.returnTime = returnTime;
    }

    /**
     * 获取后台通知/异步回调时间
     *
     * @return notify_time - 后台通知/异步回调时间
     */
    public Date getNotifyTime() {
        return notifyTime;
    }

    /**
     * 设置后台通知/异步回调时间
     *
     * @param notifyTime 后台通知/异步回调时间
     */
    public void setNotifyTime(Date notifyTime) {
        this.notifyTime = notifyTime;
    }

    /**
     * 获取响应结果：1成功，-1失败
     *
     * @return result - 响应结果：1成功，-1失败
     */
    public Integer getResult() {
        return result;
    }

    /**
     * 设置响应结果：1成功，-1失败
     *
     * @param result 响应结果：1成功，-1失败
     */
    public void setResult(Integer result) {
        this.result = result;
    }

    /**
     * 获取添加时间
     *
     * @return create_time - 添加时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置添加时间
     *
     * @param createTime 添加时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取请求IP
     *
     * @return ip - 请求IP
     */
    public String getIp() {
        return ip;
    }

    /**
     * 设置请求IP
     *
     * @param ip 请求IP
     */
    public void setIp(String ip) {
        this.ip = ip;
    }

    /**
     * 获取请求参数
     *
     * @return params - 请求参数
     */
    public String getParams() {
        return params;
    }

    /**
     * 设置请求参数
     *
     * @param params 请求参数
     */
    public void setParams(String params) {
        this.params = params;
    }

    /**
     * 获取请求tpp参数拼接
     *
     * @return req_detail_params - 请求tpp参数拼接
     */
    public String getReqDetailParams() {
        return reqDetailParams;
    }

    /**
     * 设置请求tpp参数拼接
     *
     * @param reqDetailParams 请求tpp参数拼接
     */
    public void setReqDetailParams(String reqDetailParams) {
        this.reqDetailParams = reqDetailParams;
    }

    /**
     * 获取页面返回/同步回调参数
     *
     * @return return_params - 页面返回/同步回调参数
     */
    public String getReturnParams() {
        return returnParams;
    }

    /**
     * 设置页面返回/同步回调参数
     *
     * @param returnParams 页面返回/同步回调参数
     */
    public void setReturnParams(String returnParams) {
        this.returnParams = returnParams;
    }

    /**
     * 获取后台通知/异步回调参数
     *
     * @return notify_params - 后台通知/异步回调参数
     */
    public String getNotifyParams() {
        return notifyParams;
    }

    /**
     * 设置后台通知/异步回调参数
     *
     * @param notifyParams 后台通知/异步回调参数
     */
    public void setNotifyParams(String notifyParams) {
        this.notifyParams = notifyParams;
    }
}