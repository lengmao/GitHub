package com.frame.config;
/**
 * 
* @ClassName: BaseResponse 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author zhuhai zhuhai@bzhcloud.com 
* @date 2017年11月15日 上午11:17:14 
*
 */
public class BaseResponse {
	private int status = 200;
    private String message;

    public BaseResponse(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public BaseResponse() {
    }
    public BaseResponse(String message) {
        this.message = message;
    }
    
    public BaseResponse(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
