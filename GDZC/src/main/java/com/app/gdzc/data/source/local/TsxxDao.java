package com.app.gdzc.data.source.local;

import android.app.Activity;

import com.app.gdzc.data.bean.TsxxBean;
import com.app.gdzc.net.ResponseListener;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

/**
 * Created by 王少岩 on 2016/8/25.
 */
public class TsxxDao extends BaseDao<TsxxBean, Integer> {

    public static final String TSXX_TAG = "tsxx";

    public TsxxDao(Activity activity) {
        super(activity);
    }

    @Override
    public Dao<TsxxBean, Integer> getDao() throws SQLException {
        return getHelper().getDao(TsxxBean.class);
    }

    public void getData(String tag, ResponseListener<List<TsxxBean>> listener) {
        getDataLike(tag, 0, new HashMap<String, String>(), listener);
    }

    public List<TsxxBean> getDataAll(){
        List<TsxxBean> list = null;
        try {
            list = queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

}
