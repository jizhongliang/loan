package com.hwc.framework.modules.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "cl_user_card_credit_log")
public class ClUserCardCreditLog {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 用户标识
     */
    @Column(name = "user_id")
    private Long userId;

    /**
     * 请求参数
     */
    @Column(name = "req_params")
    private String reqParams;

    /**
     * 响应参数
     */
    @Column(name = "return_params")
    private String returnParams;

    @Column(name = "img_params")
    private String imgParams;

    /**
     * 人脸匹配值
     */
    private String confidence;

    /**
     * 结果
     */
    private String result;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

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
     * 获取用户标识
     *
     * @return user_id - 用户标识
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * 设置用户标识
     *
     * @param userId 用户标识
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * 获取请求参数
     *
     * @return req_params - 请求参数
     */
    public String getReqParams() {
        return reqParams;
    }

    /**
     * 设置请求参数
     *
     * @param reqParams 请求参数
     */
    public void setReqParams(String reqParams) {
        this.reqParams = reqParams;
    }

    /**
     * 获取响应参数
     *
     * @return return_params - 响应参数
     */
    public String getReturnParams() {
        return returnParams;
    }

    /**
     * 设置响应参数
     *
     * @param returnParams 响应参数
     */
    public void setReturnParams(String returnParams) {
        this.returnParams = returnParams;
    }

    /**
     * @return img_params
     */
    public String getImgParams() {
        return imgParams;
    }

    /**
     * @param imgParams
     */
    public void setImgParams(String imgParams) {
        this.imgParams = imgParams;
    }

    /**
     * 获取人脸匹配值
     *
     * @return confidence - 人脸匹配值
     */
    public String getConfidence() {
        return confidence;
    }

    /**
     * 设置人脸匹配值
     *
     * @param confidence 人脸匹配值
     */
    public void setConfidence(String confidence) {
        this.confidence = confidence;
    }

    /**
     * 获取结果
     *
     * @return result - 结果
     */
    public String getResult() {
        return result;
    }

    /**
     * 设置结果
     *
     * @param result 结果
     */
    public void setResult(String result) {
        this.result = result;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}