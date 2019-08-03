package com.frame.entity;

import javax.persistence.Table;
import java.util.Date;

/**
 * 账户实体类
 */
@Table
public class Account {
    private String id;
    // 用户名
    private String name;
    // 登录名
    private String userName;
    // 密码
    private String password;
    // 手机号
    private String phoneNumber;
    // 邮箱
    private String mailbox;
    // 状态
    private Boolean state;
    // 是否启用
    private Boolean isEnabled;
    // 报表安全密码
    private String reportPassword;
    // 默认密码
    public static final String DEFAULT_PASSWORD = "888888";

    private String tid;

    private String token;

    private String tenantId;
    // 最后一次登录系统的时间
    private Date lastLoginTime;

    private Boolean IMAdmin;

    private String openId;
    //常驻城市(登录超过3次以上)
    private String permanentCity;
    private Boolean isManager;
    private Boolean isChildrenManager;
    private String headImgId;
    private String tenantName;
    private String idCard;
    private String emergencyContactName;
    private String emergencyContactPhone;
    private String status;
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getMailbox() {
        return mailbox;
    }

    public void setMailbox(String mailbox) {
        this.mailbox = mailbox;
    }

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }

    public Boolean getIsEnabled() {
        return isEnabled;
    }

    public void setIsEnabled(Boolean isEnabled) {
        this.isEnabled = isEnabled;
    }

    public static String getDefaultPassword() {
        return DEFAULT_PASSWORD;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public Boolean getEnabled() {
        return isEnabled;
    }

    public void setEnabled(Boolean enabled) {
        isEnabled = enabled;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public Boolean isIMAdmin() {
        return IMAdmin;
    }

    public void setIMAdmin(Boolean IMAdmin) {
        this.IMAdmin = IMAdmin;
    }

    public String getReportPassword() {
        return reportPassword;
    }

    public void setReportPassword(String reportPassword) {
        this.reportPassword = reportPassword;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getPermanentCity() {
        return permanentCity;
    }

    public void setPermanentCity(String permanentCity) {
        this.permanentCity = permanentCity;
    }

    public Boolean getManager() {
        return isManager;
    }

    public void setManager(Boolean manager) {
        isManager = manager;
    }

    public Boolean getChildrenManager() {
        return isChildrenManager;
    }

    public void setChildrenManager(Boolean childrenManager) {
        isChildrenManager = childrenManager;
    }

    public String getHeadImgId() {
        return headImgId;
    }

    public void setHeadImgId(String headImgId) {
        this.headImgId = headImgId;
    }

    public String getTenantName() {
        return tenantName;
    }

    public void setTenantName(String tenantName) {
        this.tenantName = tenantName;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getEmergencyContactName() {
        return emergencyContactName;
    }

    public void setEmergencyContactName(String emergencyContactName) {
        this.emergencyContactName = emergencyContactName;
    }

    public String getEmergencyContactPhone() {
        return emergencyContactPhone;
    }

    public void setEmergencyContactPhone(String emergencyContactPhone) {
        this.emergencyContactPhone = emergencyContactPhone;
    }
}
