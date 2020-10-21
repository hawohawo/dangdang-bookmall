package com.dangdang.common.exception;

/**
 * @author zengyuzhi
 * @date 2020/10/21 下午1:17
 */
public enum ErrorCodeEnum {
    UNKNOW_EXCEPTION(10000,"系统未知异常"),
    VALID_EXCEPTION(10001,"数据校验错误");

    private int code;
    private String msg;

    ErrorCodeEnum(int code,String msg){
        this.code = code;
        this.msg = msg  ;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

}
