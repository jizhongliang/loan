package com.hwc.framework.modules.model;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "arc_basecode")
public class ArcBasecode {
    /**
     * 代码组
     */
    @Id
    private String cat;

    /**
     * 代码
     */
    @Id
    private String code;

    /**
     * 中文描述
     */
    private String descript;

    /**
     * 是否系统代码 :  T/F
     */
    private String sys;

    /**
     * 是否停用 :  T/F
     */
    private String halt;

    /**
     * 内部分组
     */
    private String grp;

    /**
     * 修改人
     */
    private Long cby;

    /**
     * 修改时间
     */
    private Date changed;

    /**
     * 排序
     */
    private Integer seq;

    /**
     * 预留字段
     */
    private String exts1;

    /**
     * 预留字段
     */
    private String exts2;

    /**
     * 预留字段
     */
    private String exts3;

    /**
     * exts4
     */
    private String exts4;

    /**
     * 获取代码组
     *
     * @return cat - 代码组
     */
    public String getCat() {
        return cat;
    }

    /**
     * 设置代码组
     *
     * @param cat 代码组
     */
    public void setCat(String cat) {
        this.cat = cat;
    }

    /**
     * 获取代码
     *
     * @return code - 代码
     */
    public String getCode() {
        return code;
    }

    /**
     * 设置代码
     *
     * @param code 代码
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * 获取中文描述
     *
     * @return descript - 中文描述
     */
    public String getDescript() {
        return descript;
    }

    /**
     * 设置中文描述
     *
     * @param descript 中文描述
     */
    public void setDescript(String descript) {
        this.descript = descript;
    }

    /**
     * 获取是否系统代码 :  T/F
     *
     * @return sys - 是否系统代码 :  T/F
     */
    public String getSys() {
        return sys;
    }

    /**
     * 设置是否系统代码 :  T/F
     *
     * @param sys 是否系统代码 :  T/F
     */
    public void setSys(String sys) {
        this.sys = sys;
    }

    /**
     * 获取是否停用 :  T/F
     *
     * @return halt - 是否停用 :  T/F
     */
    public String getHalt() {
        return halt;
    }

    /**
     * 设置是否停用 :  T/F
     *
     * @param halt 是否停用 :  T/F
     */
    public void setHalt(String halt) {
        this.halt = halt;
    }

    /**
     * 获取内部分组
     *
     * @return grp - 内部分组
     */
    public String getGrp() {
        return grp;
    }

    /**
     * 设置内部分组
     *
     * @param grp 内部分组
     */
    public void setGrp(String grp) {
        this.grp = grp;
    }

    /**
     * 获取修改人
     *
     * @return cby - 修改人
     */
    public Long getCby() {
        return cby;
    }

    /**
     * 设置修改人
     *
     * @param cby 修改人
     */
    public void setCby(Long cby) {
        this.cby = cby;
    }

    /**
     * 获取修改时间
     *
     * @return changed - 修改时间
     */
    public Date getChanged() {
        return changed;
    }

    /**
     * 设置修改时间
     *
     * @param changed 修改时间
     */
    public void setChanged(Date changed) {
        this.changed = changed;
    }

    /**
     * 获取排序
     *
     * @return seq - 排序
     */
    public Integer getSeq() {
        return seq;
    }

    /**
     * 设置排序
     *
     * @param seq 排序
     */
    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    /**
     * 获取预留字段
     *
     * @return exts1 - 预留字段
     */
    public String getExts1() {
        return exts1;
    }

    /**
     * 设置预留字段
     *
     * @param exts1 预留字段
     */
    public void setExts1(String exts1) {
        this.exts1 = exts1;
    }

    /**
     * 获取预留字段
     *
     * @return exts2 - 预留字段
     */
    public String getExts2() {
        return exts2;
    }

    /**
     * 设置预留字段
     *
     * @param exts2 预留字段
     */
    public void setExts2(String exts2) {
        this.exts2 = exts2;
    }

    /**
     * 获取预留字段
     *
     * @return exts3 - 预留字段
     */
    public String getExts3() {
        return exts3;
    }

    /**
     * 设置预留字段
     *
     * @param exts3 预留字段
     */
    public void setExts3(String exts3) {
        this.exts3 = exts3;
    }

    /**
     * 获取exts4
     *
     * @return exts4 - exts4
     */
    public String getExts4() {
        return exts4;
    }

    /**
     * 设置exts4
     *
     * @param exts4 exts4
     */
    public void setExts4(String exts4) {
        this.exts4 = exts4;
    }
}