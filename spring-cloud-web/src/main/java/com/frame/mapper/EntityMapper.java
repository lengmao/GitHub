package com.frame.mapper;

import com.frame.entity.Entity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by scaf_xs on 2017/12/19.
 */
public interface EntityMapper {

    List<Entity> getAllEntity(@Param("limit") Integer limit, @Param("offset") Integer offset, @Param("entityName") String entityName);

    int getCountEntity();

    boolean insertEntity(@Param("parent_id") Integer parent_id,
                         @Param("entity_code") String entity_code, @Param("entity_name") String entity_name);

    Entity getEntityById(@Param("id") int id);

    boolean updateEntity(@Param("entity_id") int entity_id,@Param("parent_id") Integer parent_id,
                         @Param("entity_code") String entity_code, @Param("entity_name") String entity_name);
}
