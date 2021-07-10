package com.arlin.constants;

/**
 * @ClassName: StatusConst
 * @Description: 系统返回码常量
 * @Author: arlin
 * @Date: 2021/6/23
 */
public class StatusConst {

    /**
     * 成功
     */
    public static final int OK = 200;

    /**
     * 失败
     */
    public static final int ERROR = 201;

    /**
     * 系统异常
     */
    public static final int SYSTEM_ERROR = 500;

    /**
     * 未登录
     */
    public static final int NOT_LOGIN = 401;

    /**
     * 没有操作权限
     */
    public static final int AUTHORIZED = 403;
}
