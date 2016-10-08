package com.baseui.data;

import com.google.gson.Gson;

/**
 * Created by 王少岩 on 2016/9/19.
 * 数据模型的基类(实现Model Gson化，便于调试与数据转化)
 */
public abstract class BaseBean {

    public String toJson() {
        return toJson(this);
    }

    public static String toJson(BaseBean bean) {
        if (bean != null) {
            return new Gson().toJson(bean);
        }
        return null;
    }

    public static <T extends BaseBean> T fromJson(String jsonStr, Class<? extends BaseBean> subClass) {
        BaseBean newObj = new Gson().fromJson(jsonStr, subClass);
        return (T) newObj;
    }

    public String toString() {
        return toJson();
    }
}
