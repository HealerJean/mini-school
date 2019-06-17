package com.hlj.proj.exception;


import com.hlj.proj.enums.ResponseEnum;

/**
 * 系统业务异常
 */
public class BusinessException extends RuntimeException {


    private int code;

    public BusinessException(int code) {
        this.code = code;
    }

    public BusinessException(String message) {
        super(message);
        this.code = ResponseEnum.逻辑错误.code;
    }

    public BusinessException(int code, String message) {
        super(message);
        this.code = code;
    }

    public BusinessException(ResponseEnum responseEnum) {
        super(responseEnum.msg);
        this.code = responseEnum.code ;
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
        this.code = ResponseEnum.逻辑错误.code;
    }

    public BusinessException(int code ,Throwable e) {
        super(e);
        this.code = code;
    }


    public void setCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

}
