package com.app.gdzc.data.source.local;

import android.content.Context;
import android.util.Log;

import com.app.gdzc.data.bean.LydwBean;
import com.app.gdzc.net.ResponseListener;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

/**
 * Created by 王少岩 on 2016/8/26.
 */
public class LydwDao extends BaseDao<LydwBean, Integer> {

    public static final String LYDWDAO_TAG = "lydwDao";

    public LydwDao(Context context) {
        super(context);
    }

    @Override
    public Dao<LydwBean, Integer> getDao() throws SQLException {
        return getHelper().getDao(LydwBean.class);
    }

    public void getData(String tag, ResponseListener<List<LydwBean>> listener) {
        getDataLike(tag, 0, new HashMap<String, String>(), listener);
    }

    public List<LydwBean> getData(){
        List<LydwBean> list = null;
        try {
            list = queryForAll();
        } catch (SQLException e) {
            Log.e("LydwDao", "查询失败");
            e.printStackTrace();
        }
        return list;
    }
}