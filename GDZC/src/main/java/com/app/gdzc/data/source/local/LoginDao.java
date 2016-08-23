package com.app.gdzc.data.source.local;

import android.content.Context;

import com.app.gdzc.data.bean.LoginBean;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by 王少岩 on 2016/8/23.
 */
public class LoginDao {
    private Dao<LoginBean, Integer> mLoginDao;
    private DataBaseHelper mHelper;

    public LoginDao(Context context) {
        try {
            mHelper = DataBaseHelper.getHelper(context);
            mLoginDao = mHelper.getDao(LoginBean.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean queryUser(LoginBean loginBean){
        boolean isExist = false;
        try {
            List<LoginBean> list =  mLoginDao.queryBuilder().where().eq("用户名", loginBean.getUserName()).and().eq("密码", loginBean.getPassWord()).query();
            isExist = list.size()>0?true:false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isExist;
    }
}
