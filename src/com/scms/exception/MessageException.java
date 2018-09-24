package com.scms.exception;

public class MessageException extends  Exception{
    private String msg;

    public MessageException(String s) {
    }

    public MessageException(String message, String msg) {
        super(message);
        this.msg = msg;
    }


    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
