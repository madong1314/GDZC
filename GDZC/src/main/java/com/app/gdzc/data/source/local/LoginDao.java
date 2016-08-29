package com.app.gdzc.data.source.local;

import android.content.Context;

import com.app.gdzc.data.bean.LoginBean;
import com.app.gdzc.data.source.DataSource.Callback;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.HashMap;

/**
 * Created by 王少岩 on 2016/8/23.
 */
public class LoginDao extends BaseDao<LoginBean, Integer> {

    public LoginDao(Context context) {
        super(context);
    }

    @Override
    public Dao<LoginBean, Integer> getDao() throws SQLException {
        return getHelper().getDao(LoginBean.class);
    }

    public void getData(int tag, LoginBean loginBean, Callback callback) {
        HashMap<String, String> map = new HashMap<>();
        map.put("用户名", loginBean.getUserName());
        map.put("密码", loginBean.getPassWord());
        getData(tag, 0, map, callback);
    }
}
