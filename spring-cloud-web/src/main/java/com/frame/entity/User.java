package com.frame.entity;/**
 * Created by scaf_xs on 2017/12/18.
 */

/**
 * @author scaf_xs
 * @ClassName: User
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2017/12/18 11:52
 */

import java.io.Serializable;


public class User implements Serializable {

   // private static final long serialVersionUID = 1L;
    private Long id;
    private String userName;
    private String passWord;
    private String userSex;
    private String nickName;

    public User() {
        super();
    }

    public User(String userName, String passWord, String userSex) {
        super();
        this.passWord = passWord;
        this.userName = userName;
        this.userSex = userSex;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return "userName " + this.userName + ", pasword " + this.passWord + "sex " + this.userSex;
    }

}
