package com.hlj.proj.enums;

import java.util.Arrays;
import java.util.List;

/**
 * @author HealerJean
 * @version 1.0v
 * @ClassName ResponseEnum
 * @date 2019/6/13  20:45.
 * @msgcription
 */
public enum ResponseEnum   {


    正常(200,"访问正常"),
    条件验证失败(201,"条件验证失败"),
    参数解析失败(202,"参数解析失败"),
    操作确认(203,"操作确认"),
    未登录(204,"未登录"),

    逻辑错误(301,"逻辑错误"),

    登录标识过期(400,"登录标识过期"),
    未授权(401, "未授权"),
    访问禁止(403,"访问禁止"),
    页面丢失(404,"页面丢失"),
    系统错误(500,"系统错误"),

    部门名称已经存在(501,"部门名称已经存在"),
    父级部门不存在(502,"父级部门不存在"),
    部门不存在(503,"部门不存在"),
    部门存在子部门_不可删除(504,"部门存在子部门_不可删除"),
    部门存在用户_不可删除(505,"部门存在用户_不可删除"),
    菜单不存在(506,"菜单不存在"),
    菜单已经存在(507,"菜单已经存在"),
    角色不存在(508,"角色不存在"),
    角色已经存在(509,"角色已经存在"),
    用户不存在(510,"用户不存在"),


    未知错误(999,"未知错误"),





     ;


    public int code;
    public String msg;

    ResponseEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }


    public ResponseEnum value(String enumName){
        return valueOf( enumName ) ;
    }

    public static boolean checkExist( Integer code){
        for (ResponseEnum value : ResponseEnum.values()){
            if (value.code == code){
                return true;
            }
        }
        return false;
    }


    public static ResponseEnum toEnum(int code){
        for (ResponseEnum value : ResponseEnum.values()){
            if (value.code == code){
                return value;
            }
        }
        return ResponseEnum.未知错误;
    }

    public static String getmsg(int code){
        for (ResponseEnum value : ResponseEnum.values()){
            if (value.code == code){
                return value.msg;
            }
        }
        return ResponseEnum.未知错误.msg;
    }

    public static List<ResponseEnum> getTypeList(){
        return Arrays.asList(values());
    }

}
