package com.frame.service;/**
 * Created by scaf_xs on 2017/12/16.
 */

import com.frame.entity.TableInfo;
import com.frame.mapper.TableMapper;
import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author scaf_xs
 * @ClassName: TableService
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2017/12/16 16:20
 */

@Service
public class TableService {

    public List<TableInfo> getTableList(Map<String,String> map){
        String schema="maintenance";

        DataSource dataSource=new PooledDataSource(map.get("dbDriver"),map.get("dbUrl"),map.get("username"),map.get("password"));

        TransactionFactory transactionFactory=new JdbcTransactionFactory();
        Environment environment=new Environment("development",transactionFactory,dataSource);
        Configuration configuration=new Configuration(environment);
        configuration.addMapper(TableMapper.class);

        SqlSessionFactory sqlSessionFactory=new SqlSessionFactoryBuilder().build(configuration);
        SqlSession sqlSession=sqlSessionFactory.openSession();
//        List<Map<String,Object>> list=sqlSession.selectList("com.frame.mapper.TableMapper.getTableList");

        TableMapper mapper=sqlSession.getMapper(TableMapper.class);
        List<TableInfo> tableInfos=mapper.getTableList(schema);
        sqlSession.commit();
        sqlSession.close();

       return tableInfos;
    }
}
