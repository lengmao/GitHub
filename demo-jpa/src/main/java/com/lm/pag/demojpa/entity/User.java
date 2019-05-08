package com.lm.pag.demojpa.entity;

import javax.persistence.*;

/**
 * @author scaf_xs
 * @ClassName: User
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2019/5/7 11:49
 */
@Entity
@Table(name = "T_USER")
public class User {

    @Id
    @Column(name = "USER_ID")
    private Long userId;

    @Column(name = "USERNAME",length = 50)
    private String username;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "PHONE_NUM")
    private String phoneNum;

    //跟数据库无关的字段使用@Transient标记或移至VO类。
    @Transient
    private String other;

    public User() {
    }

    public User(Long userId, String username, String password, String phoneNum) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.phoneNum = phoneNum;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
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
