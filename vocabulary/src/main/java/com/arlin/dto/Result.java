package com.arlin.dto;

import com.arlin.constants.StatusConst;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName: ResultVO
 * @Description: 接口返回类
 * @Author: arlin
 * @Date: 2021/6/24
 */
@Data
public class Result<T> implements Serializable {
    private boolean flag;
    private Integer code;
    private String message;
    private T data;

    public Result(boolean flag, Integer code, String message, Object data) {
        this.flag = flag;
        this.code = code;
        this.message = message;
        this.data = (T) data;
    }

    public Result(boolean flag, Integer code, String message) {
        this.flag = flag;
        this.code = code;
        this.message = message;
    }

    public Result() {
        this.flag = true;
        this.code = StatusConst.OK;
        this.message = "操作成功!";
    }

    public static <T> Result<T> success(T data) {
        return new Result<>(true, StatusConst.OK, "操作成功", data);
    }

}