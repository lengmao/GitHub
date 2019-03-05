package com.springboot.demo.sys.entity;

import java.util.List;

/**
 * @author scaf_xs
 * @ClassName: SysMenu
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2019/2/27 16:03
 */

public class SysMenu {
    private int id;
    private  String name;
    private String parentCode;
    private String code;
    private String url;
    private String icon;
    private int order;
    private String description;
    private List<SysMenu> childs;

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

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<SysMenu> getChilds() {
        return childs;
    }

    public void setChilds(List<SysMenu> childs) {
        this.childs = childs;
    }
}
