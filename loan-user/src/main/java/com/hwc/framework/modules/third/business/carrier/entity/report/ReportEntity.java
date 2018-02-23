package com.hwc.framework.modules.third.business.carrier.entity.report;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.hwc.framework.modules.service.base.Saveable;

import java.util.Date;

/**
 * Created by zengdongping on 17/1/4.
 */
@JsonIgnoreProperties(value = {"primaryVal", "keyColumns", "tableName"}, ignoreUnknown = true)
public class ReportEntity implements Saveable {
    private long id;
    private String userId;
    private String mobile;
    private String taskId;
    private String reportData;
    private Date createTime;
    private Date lastModifyTime;

    private static final String[] keyColumns = {"id"};
    private static final String tableName = "t_reportdata";

    @Override
    public String getTableName() {
        return tableName;
    }

    @Override
    public String[] getKeyColumns() {
        return keyColumns;
    }


    @Override
    public String getPrimaryVal() {
        return String.valueOf(id);
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastModifyTime() {
        return lastModifyTime;
    }

    public void setLastModifyTime(Date lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getReportData() {
        return reportData;
    }

    public void setReportData(String reportData) {
        this.reportData = reportData;
    }
}
