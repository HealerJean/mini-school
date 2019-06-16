package com.hlj.proj.enums;

/**
 * @Description
 * @Author HealerJean
 * @Date 2019-06-16  15:42.
 */
public enum  MenuTypeEnum {

    后端菜单("0", "后端菜单"),
    前端菜单("1", "前端菜单");

    MenuTypeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public  String code;
    public String desc;


    public static MenuTypeEnum toEnum(String code){
        for (MenuTypeEnum value : MenuTypeEnum.values()){
            if (value.code == code){
                return value;
            }
        }
        return null;
    }
}
