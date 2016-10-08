package com.baseui.data;

import java.io.Serializable;

/**
 * Created by 王少岩 on 2016/9/19.
 * 数据协议的基类
 */
public class BaseProtocolBean extends BaseBean implements Serializable {
    public BaseStatus status;

    public static class BaseStatus extends BaseBean {
        public String code;
        public String msg;

        public Boolean isSuccess() {
            if ("0000".equals(this.code)) {
                return true;
            }
            return false;
        }
    }
}
