package com.cqupt.pojo;

public class RegisterMessage {
    /**
     * 返回信息success/fail/error
     */
    private String msg;
    /**
     * 若msg为error，则返回错误的字段
     */
    private String field;
    /**
     * 错误字段的改正方式
     */
    private String errorMsg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    @Override
    public String toString() {
        return "RegisterMessage{" +
                "msg='" + msg + '\'' +
                ", field='" + field + '\'' +
                ", errorMsg='" + errorMsg + '\'' +
                '}';
    }
}
