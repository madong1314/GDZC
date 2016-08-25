package com.app.gdzc.data.source.local;

import android.content.Context;

import com.app.gdzc.data.bean.LoginBean;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

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

    public boolean queryUser(LoginBean loginBean){
        boolean isExsit = false;
        try {
            List<LoginBean> list =  query(getDao().queryBuilder().where().eq("用户名", loginBean.getUserName()).and().eq("密码", loginBean.getPassWord()).prepare());
            isExsit = list.size()>0?true:false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isExsit;
    }
}
