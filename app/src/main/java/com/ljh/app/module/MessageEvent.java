package com.ljh.app.module;

/**
 * Created by hk_jacky on 2018/3/28.
 */

public class MessageEvent<T>{

    private String message;
    private T data;
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public MessageEvent(String message) {
        this.message = message;
    }

    public MessageEvent(String message, T t){
        this.data = t;
        this.message = message;
    }

    public T getData(){
        return data;
    }
}
