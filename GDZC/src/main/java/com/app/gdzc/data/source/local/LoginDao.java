package com.app.gdzc.data.source.local;

import android.app.Activity;

import com.app.gdzc.login.bean.LoginBean;
import com.app.gdzc.net.ResponseListener;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.HashMap;

/**
 * Created by 王少岩 on 2016/8/23.
 */
public class LoginDao extends BaseDao<LoginBean, Integer> {

    public static final String LOGIN_TAG = "login";
    public LoginDao(Activity activity) {
        super(activity);
    }

    @Override
    public Dao<LoginBean, Integer> getDao() throws SQLException {
        return getHelper().getDao(LoginBean.class);
    }

    public void getData(String tag, LoginBean loginBean, ResponseListener listener) {
        HashMap<String, String> map = new HashMap<>();
        map.put("用户名", loginBean.getUserName());
        map.put("密码", loginBean.getPassWord());
        getData(tag, 0, map, listener);
    }
}
