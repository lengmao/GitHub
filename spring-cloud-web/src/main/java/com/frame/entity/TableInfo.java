package com.frame.entity;

import java.util.List;
import java.util.Map;

/**
 * 
* @ClassName: TableInfo 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author liyz liyz@bzhcloud.com 
* @date 2017年8月4日 上午11:33:47 
*
 */
public class TableInfo {
	private String schema;
	private String tableName;
	private String tableComment;
	private String tableType;
//
//	private List<ColumnInfo> columns;
//	private Map<String,String> status;
	
	public String getSchema() {
		return schema;
	}
	public void setSchema(String schema) {
		this.schema = schema;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getTableComment() {
		return tableComment;
	}
	public void setTableComment(String tableComment) {
		this.tableComment = tableComment;
	}
	
	public String getTableType() {
		return tableType;
	}
	public void setTableType(String tableType) {
		this.tableType = tableType;
	}
//	public List<ColumnInfo> getColumns() {
//		return columns;
//	}
//	public void setColumns(List<ColumnInfo> columns) {
//		this.columns = columns;
//	}
//	public Map<String, String> getStatus() {
//		return status;
//	}
//	public void setStatus(Map<String, String> status) {
//		this.status = status;
//	}
	
}
