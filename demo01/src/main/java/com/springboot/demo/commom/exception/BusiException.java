package com.springboot.demo.commom.exception;

/**
 * @author scaf_xs
 * @ClassName: BusiException
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2019/2/28 10:42
 */

public class BusiException extends RuntimeException{
    public BusiException(String error){
        super(error);
    }
}
