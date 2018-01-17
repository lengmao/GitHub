package com.frame.config;

import java.util.List;

/**
 * 
* @ClassName: TableResultResponse 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author zhuhai zhuhai@bzhcloud.com 
* @date 2017年8月4日 上午11:17:03 
* 
* @param <T>
 */
public class TableResultResponse<T> extends BaseResponse {

    long total;
    List<T> rows;
    
    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

    public TableResultResponse(long total, List<T> rows) {
        this.total = total;
        this.rows = rows;
    }

    public TableResultResponse() {
    }

    TableResultResponse<T> total(int total) {
        this.total = total;
        return this;
    }

    TableResultResponse<T> total(List<T> rows) {
        this.rows = rows;
        return this;
    }
}
