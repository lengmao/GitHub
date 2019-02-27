package com.springboot.demo.busi.entity;

/**
 * @author scaf_xs
 * @ClassName: MyTestEntity
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2019/2/22 16:21
 */

public class MyTestEntity {
    private int id;
    private String name;
    private String address;
    private String phone;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
