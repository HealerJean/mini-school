package com.hlj.proj.enums;

/**
 * @Description
 * @Author HealerJean
 * @Date 2019-06-16  01:58.
 */
public enum  StatusEnum {

    生效("10", "生效"),
    废弃("99", "废弃");

    StatusEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String code;
    public String desc;


}
