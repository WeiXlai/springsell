package com.zdxh.sell.VO;

import java.io.Serializable;

/**
 * HTTP请求的返回对象
 */
public class ResultVO<T> implements Serializable {


    private static final long serialVersionUID = -2141727548387453158L;
    /*
        错误码
         */
    private Integer code;
    /*
    返回信息
     */
    private String msg;
    /*
    数据
     */
    private T data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
