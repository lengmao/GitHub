package com.lm.pag.demojpa.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author scaf_xs
 * @ClassName: UserRole
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2019/5/7 17:50
 */
@Entity
@Table(name="t_user_role")
public class UserRole {
    @Id
    @Column(name = "ur_id")
    private Long urId;

    @Column(name="u_id")
    private Long uId;

    @Column(name="r_id")
    private Long rId;

    public UserRole() {
    }

    public UserRole(Long urId, Long uId, Long rId) {
        this.urId = urId;
        this.uId = uId;
        this.rId = rId;
    }

    public Long getUrId() {
        return urId;
    }

    public void setUrId(Long urId) {
        this.urId = urId;
    }

    public Long getuId() {
        return uId;
    }

    public void setuId(Long uId) {
        this.uId = uId;
    }

    public Long getrId() {
        return rId;
    }

    public void setrId(Long rId) {
        this.rId = rId;
    }
}
