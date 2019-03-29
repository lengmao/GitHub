package com.springboot.demo.sys.entity;

import java.util.Date;

/**
 * @author scaf_xs
 * @ClassName: SysUser
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2019/2/26 14:19
 */

public class SysUser {
    private String id;
    private String name;
    private String password;
    private String email;
    private Date lastLoginTime;

    /** 状态 1-可用 0-禁用 */
    private String status;

    private Date createTime;

    public SysUser(){}

    public SysUser(String id, String name, String password, String email, Date lastLoginTime, String status, Date createTime) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.email = email;
        this.lastLoginTime = lastLoginTime;
        this.status = status;
        this.createTime = createTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
