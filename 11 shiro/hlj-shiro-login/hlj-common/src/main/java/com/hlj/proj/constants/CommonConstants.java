package com.hlj.proj.constants;

/**
 * @ClassName CommonConstants
 * @Author TD
 * @Date 2019/4/15 16:19
 * @Description 基础常量
 */
public class CommonConstants {

    /**
     * 接口返回信息
     */
    public static final String returnMsg = "msg";

    /** 成功标识常量（系统内部使用） */
    public static final String COMMON_SUCCESS = "success";

    /**
     * 后台文件下载地址
     */
    public static final String COMMON_FILE_URI ="/api/sys/file/downLoad?accessKeyId=";

    /**
     * 用户
     */
    public static final String SESSION_USER = "sessionUser";

    /** Redis系统标识 */
    public static final String REDIS_SCF = "SCF";

    /** Redis验证码标识 */
    public static final String REDIS_VERIFY_CODE = "VERIFY_CODE";



    /** 模板数据变量 */
    public static final String TEMPLE_VERIFY_CODE = "VerifyCode";

    /** 管理员用户名 */
    public static final String ADMIN = "admin";

    /** 默认密码 */
    public static final String DEFAULT_PASSWORD = "xiaomiJRKJ2019";

    /** 手机 */
    public static final String PHONE = "phone";

    /** 邮箱 */
    public static final String EMAIL = "email";

    /** 找回密码流程redis key */
    public static final String REDIS_RESET_PASSWORD_FLOW = "SCF:RESET_PASSWORD_FLOW:";
}
