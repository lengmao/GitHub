package com.springboot.demo.commom.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PageBean<T> {
    private int pageNumber;
    private int pageSize;
    private int pageTotal;
    private int total;
    private List<T> rows;

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        if (pageNumber < 1) {
            this.pageNumber = 1;
        } else if (pageNumber > this.pageTotal) {
            this.pageNumber = pageTotal;
        } else {
            this.pageNumber = pageNumber;
        }
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
        this.pageTotal = (this.total % this.pageSize == 0) ? this.total / this.pageSize : this.total / this.pageSize + 1;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

    public PageBean(int pageNumber, int pageSize, int total, List<T> rows) {
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        this.total = total;
        this.rows = rows;
    }

    public static Map<String,Object> pageMap(int pageNumber,int pageSize){
        Map<String, Object> pageMap = new HashMap<>(10);
        pageMap.put("pageNumber", (pageNumber - 1)*pageSize);
        pageMap.put("pageSize", pageSize);
        return pageMap;
    }
}
