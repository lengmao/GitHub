package com.frame.mapper;

import com.frame.entity.TableInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by scaf_xs on 2017/12/16.
 */
@Repository
public interface TableMapper {

    List<TableInfo> getTableList(@Param("schema") String schema);

    int getTableTotal(String schema);
}
