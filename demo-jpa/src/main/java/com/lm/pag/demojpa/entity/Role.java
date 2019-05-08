package com.lm.pag.demojpa.entity;

import com.sun.javafx.beans.IDProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author scaf_xs
 * @ClassName: Role
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2019/5/7 17:47
 */
@Entity
@Table(name="t_role")
public class Role {

    @Id
    @Column(name="role_id")
    private Long roleId;

    @Column(name="role_name")
    private String roleName;

    @Column(name="role_discription")
    private String roleDiscription;

    public Role() {
    }

    public Role(Long roleId, String roleName, String roleDiscription) {
        this.roleId = roleId;
        this.roleName = roleName;
        this.roleDiscription = roleDiscription;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleDiscription() {
        return roleDiscription;
    }

    public void setRoleDiscription(String roleDiscription) {
        this.roleDiscription = roleDiscription;
    }
}
