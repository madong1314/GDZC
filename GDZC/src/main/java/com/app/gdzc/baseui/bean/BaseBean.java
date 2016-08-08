package com.app.gdzc.baseui.bean;

import java.io.Serializable;

/**
 * Created by 王少岩 on 2016/5/17.
 */
public class BaseBean implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 5228556729499531783L;
    protected BaseStatus status;

    public BaseStatus getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "{" +
                "status:{" +
                "code:" + status.getCode() + ", " +
                "msg:" + status.getMsg() +
                "}" +
                "}";
    }

    public static class BaseStatus{
        private String code;
        private String msg;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        /**
         *  返回数据是否成功
         * @return
         */
        public Boolean isSuccess() {
            if ("0000".equals(this.code)) {
                return true;
            }
            return false;
        }

    }
}