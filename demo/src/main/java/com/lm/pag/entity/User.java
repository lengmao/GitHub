package com.lm.pag.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * @author scaf_xs
 * @ClassName: User
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2019/4/15 16:24
 */
@Table(name = "T_USER")
public class User {

    /**
     *@Table(name="") 此处name的值代表数据库中的表名称
     * @Column(name="") 此处name的值代表数据库中的表列名称
     */
    @Id
    @Column(name = "USER_ID")
    private String userId;

    @Column(name = "USERNAME")
    private String username;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "PHONE_NUM")
    private String phoneNum;

    //跟数据库无关的字段使用@Transient标记或移至VO类。
    @Transient
    private String other;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

}
