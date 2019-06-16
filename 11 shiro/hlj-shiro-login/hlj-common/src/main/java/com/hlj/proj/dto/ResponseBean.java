package com.hlj.proj.dto;


import com.hlj.proj.enums.ResponseEnum;

/**
 * 返回对象
 */
public class ResponseBean {

    private ResponseBean() {
    }
    public static ResponseBean buildSuccess() {
        ResponseBean responseBean = new ResponseBean();
        responseBean.setSuccess(true);
        responseBean.setCode(ResponseEnum.正常.code);
        responseBean.setDate(System.currentTimeMillis()+"");
        return responseBean;
    }


    public static ResponseBean buildSuccess(Object result) {
        ResponseBean responseBean = new ResponseBean();
        responseBean.setSuccess(true);
        responseBean.setCode(ResponseEnum.正常.code);
        responseBean.setResult(result);
        responseBean.setDate(System.currentTimeMillis()+"");
        return responseBean;
    }


    public static ResponseBean buildSuccess(String msg ,Object result) {
        ResponseBean responseBean = new ResponseBean();
        responseBean.setSuccess(true);
        responseBean.setCode(ResponseEnum.正常.code);
        responseBean.setMsg(msg);
        responseBean.setResult(result);
        responseBean.setDate(System.currentTimeMillis()+"");
        return responseBean;
    }



    public static ResponseBean buildFailure() {
        ResponseBean responseBean = new ResponseBean();
        responseBean.setSuccess(false);
        responseBean.setCode(ResponseEnum.系统错误.code);
        responseBean.setDate(System.currentTimeMillis()+"");
        return responseBean;
    }

    public static ResponseBean buildFailure(String msg) {
        ResponseBean responseBean = new ResponseBean();
        responseBean.setSuccess(false);
        responseBean.setCode(ResponseEnum.系统错误.code);
        responseBean.setMsg(msg);
        responseBean.setDate(System.currentTimeMillis()+"");
        return responseBean;
    }

    public static ResponseBean buildFailure(ResponseEnum responseEnum) {
        ResponseBean responseBean = new ResponseBean();
        responseBean.setSuccess(false);
        responseBean.setCode(responseEnum.code);
        responseBean.setMsg(responseEnum.msg);
        responseBean.setDate(System.currentTimeMillis()+"");
        return responseBean;
    }

    public static ResponseBean buildFailure(int code, String msg) {
        ResponseBean responseBean = new ResponseBean();
        responseBean.setSuccess(false);
        responseBean.setCode(code);
        responseBean.setMsg(msg);
        responseBean.setDate(System.currentTimeMillis()+"");
        return responseBean;
    }

    private boolean success;
    private Object result = "{}";
    private String msg = "";
    private int code ;
    private String date ;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
