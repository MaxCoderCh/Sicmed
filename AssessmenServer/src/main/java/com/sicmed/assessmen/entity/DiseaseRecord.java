package com.sicmed.assessmen.entity;

import java.util.Date;

public class DiseaseRecord {
    private String id;

    private String recordPath;

    private String patientId;

    private String createDoctor;

    private Date createTime;

    private String updateDoctor;

    private Date updateTime;

    private String deleteDoctor;

    private Date deleteTime;

    private String delFlag;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getRecordPath() {
        return recordPath;
    }

    public void setRecordPath(String recordPath) {
        this.recordPath = recordPath == null ? null : recordPath.trim();
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId == null ? null : patientId.trim();
    }

    public String getCreateDoctor() {
        return createDoctor;
    }

    public void setCreateDoctor(String createDoctor) {
        this.createDoctor = createDoctor == null ? null : createDoctor.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateDoctor() {
        return updateDoctor;
    }

    public void setUpdateDoctor(String updateDoctor) {
        this.updateDoctor = updateDoctor == null ? null : updateDoctor.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getDeleteDoctor() {
        return deleteDoctor;
    }

    public void setDeleteDoctor(String deleteDoctor) {
        this.deleteDoctor = deleteDoctor == null ? null : deleteDoctor.trim();
    }

    public Date getDeleteTime() {
        return deleteTime;
    }

    public void setDeleteTime(Date deleteTime) {
        this.deleteTime = deleteTime;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag == null ? null : delFlag.trim();
    }
}