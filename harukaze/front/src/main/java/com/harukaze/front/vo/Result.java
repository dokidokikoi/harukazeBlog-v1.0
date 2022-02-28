package com.harukaze.front.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class Result implements Serializable {

    private int code; // 200是正常，非200表示异常
    private String msg;
    private Object data;
    private int total;

    public static Result succ(Object data, int total) {
        return succ(200, "操作成功", data, total);
    }

    public static Result succ(int code, String msg, Object data, int total) {
        Result r = new Result();
        r.setCode(code);
        r.setMsg(msg);
        r.setData(data);
        r.setTotal(total);
        return r;
    }

    public static Result fail(String msg) {
        return fail(400, msg, null);
    }

    public static Result fail(String msg, Object data) {
        return fail(400, msg, data);
    }

    public static Result fail(int code, String msg, Object data) {
        Result r = new Result();
        r.setCode(code);
        r.setMsg(msg);
        r.setData(data);
        return r;
    }

}
