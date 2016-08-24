package com.app.gdzc.data.source.local;

import android.content.Context;

import com.app.gdzc.data.bean.LoginBean;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;

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
}
