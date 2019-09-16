package com.ljh.databinding.data.model;

public class BaseModel<T> {
    private int retcode;
    private String message;
    private T result;

    public int getRetcode() {
        return retcode;
    }

    public void setRetcode(int retcode) {
        this.retcode = retcode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "BaseModel{" +
                "retcode=" + retcode +
                ", message='" + message + '\'' +
                ", result=" + result +
                '}';
    }
}