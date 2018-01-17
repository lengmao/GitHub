package com.frame.entity;/**
 * Created by scaf_xs on 2017/12/19.
 */

import java.io.Serializable;

/**
 * @author scaf_xs
 * @ClassName: Entity
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2017/12/19 15:31
 */

public class Entity implements Serializable {
    private int entity_id;
    private int parent_id;
    private String entity_code;
    private String entity_name;

    public Entity(int entity_id, int parent_id, String entity_code, String entity_name) {
        this.entity_id = entity_id;
        this.parent_id = parent_id;
        this.entity_code = entity_code;
        this.entity_name = entity_name;
    }

    public Entity() {
    }

    public int getEntity_id() {
        return entity_id;
    }

    public void setEntity_id(int entity_id) {
        this.entity_id = entity_id;
    }

    public int getParent_id() {
        return parent_id;
    }

    public void setParent_id(int parent_id) {
        this.parent_id = parent_id;
    }

    public String getEntity_code() {
        return entity_code;
    }

    public void setEntity_code(String entity_code) {
        this.entity_code = entity_code;
    }

    public String getEntity_name() {
        return entity_name;
    }

    public void setEntity_name(String entity_name) {
        this.entity_name = entity_name;
    }

    @Override
    public String toString() {
        return "Entity{" +
                "entity_id=" + entity_id +
                ", parent_id=" + parent_id +
                ", entity_code='" + entity_code + '\'' +
                ", entity_name='" + entity_name + '\'' +
                '}';
    }
}
