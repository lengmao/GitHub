package com.springboot.demo.sys.entity;

/**
 * @author scaf_xs
 * @ClassName: SysRole
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2019/3/21 15:53
 */

public class SysRole {
    private String id;
    private String name;
    private String description;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
