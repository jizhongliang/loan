package com.hwc.framework.modules.model;

/**
 * Created by   on 2017/11/2.
 */
/**
 * 风险评估参数
 */
public class RiskItems {

    /**
     * 商品类目
     */
    private String frms_ware_category;
    /**
     * 用户唯一标识
     */
    private String user_info_mercht_userno;

    /**
     * 绑定手机号
     */
    private String user_info_bind_phone;

    /**
     * 注册时间
     */
    private String user_info_dt_register;

    /**
     * 注册姓名
     */
    private String user_info_full_name;

    /**
     * 证件类型 默认为0身份证号
     */
    private String user_info_id_type;

    /**
     * 证件号码
     */
    private String user_info_id_no;

    /**
     * 是否实名认证  1是  0 无认证
     */
    private String user_info_identify_state;

    /**
     * 实名认证方式  1 银行卡 2 现场认证 3 身份证远程认证 4 其它
     */
    private String user_info_identify_type;

    /**
     * 获取商品类目
     * @return frms_ware_category
     */
    public String getFrms_ware_category() {
        return frms_ware_category;
    }

    /**
     * 设置商品类目
     * @param frms_ware_category
     */
    public void setFrms_ware_category(String frms_ware_category) {
        this.frms_ware_category = frms_ware_category;
    }

    /**
     * 获取用户唯一标识
     * @return user_info_mercht_userno
     */
    public String getUser_info_mercht_userno() {
        return user_info_mercht_userno;
    }

    /**
     * 设置用户唯一标识
     * @param user_info_mercht_userno
     */
    public void setUser_info_mercht_userno(String user_info_mercht_userno) {
        this.user_info_mercht_userno = user_info_mercht_userno;
    }

    /**
     * 获取绑定手机号
     * @return user_info_bind_phone
     */
    public String getUser_info_bind_phone() {
        return user_info_bind_phone;
    }

    /**
     * 设置绑定手机号
     * @param user_info_bind_phone
     */
    public void setUser_info_bind_phone(String user_info_bind_phone) {
        this.user_info_bind_phone = user_info_bind_phone;
    }

    /**
     * 获取注册时间
     * @return user_info_dt_register
     */
    public String getUser_info_dt_register() {
        return user_info_dt_register;
    }

    /**
     * 设置注册时间
     * @param user_info_dt_register
     */
    public void setUser_info_dt_register(String user_info_dt_register) {
        this.user_info_dt_register = user_info_dt_register;
    }

    /**
     * 获取注册姓名
     * @return user_info_full_name
     */
    public String getUser_info_full_name() {
        return user_info_full_name;
    }

    /**
     * 设置注册姓名
     * @param user_info_full_name
     */
    public void setUser_info_full_name(String user_info_full_name) {
        this.user_info_full_name = user_info_full_name;
    }

    /**
     * 获取证件类型默认为0身份证号
     * @return user_info_id_type
     */
    public String getUser_info_id_type() {
        return user_info_id_type;
    }

    /**
     * 设置证件类型默认为0身份证号
     * @param user_info_id_type
     */
    public void setUser_info_id_type(String user_info_id_type) {
        this.user_info_id_type = user_info_id_type;
    }

    /**
     * 获取证件号码
     * @return user_info_id_no
     */
    public String getUser_info_id_no() {
        return user_info_id_no;
    }

    /**
     * 设置证件号码
     * @param user_info_id_no
     */
    public void setUser_info_id_no(String user_info_id_no) {
        this.user_info_id_no = user_info_id_no;
    }

    /**
     * 获取是否实名认证1是0无认证
     * @return user_info_identify_state
     */
    public String getUser_info_identify_state() {
        return user_info_identify_state;
    }

    /**
     * 设置是否实名认证1是0无认证
     * @param user_info_identify_state
     */
    public void setUser_info_identify_state(String user_info_identify_state) {
        this.user_info_identify_state = user_info_identify_state;
    }

    /**
     * 获取实名认证方式1银行卡2现场认证3身份证远程认证4其它
     * @return user_info_identify_type
     */
    public String getUser_info_identify_type() {
        return user_info_identify_type;
    }

    /**
     * 设置实名认证方式1银行卡2现场认证3身份证远程认证4其它
     * @param user_info_identify_type
     */
    public void setUser_info_identify_type(String user_info_identify_type) {
        this.user_info_identify_type = user_info_identify_type;
    }

}
