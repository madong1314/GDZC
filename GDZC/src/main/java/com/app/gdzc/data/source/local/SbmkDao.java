package com.app.gdzc.data.source.local;

import android.content.Context;

import com.app.gdzc.data.bean.SbmkBean;
import com.app.gdzc.data.source.DataSource;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.HashMap;

/**
 * Created by 王少岩 on 2016/8/29.
 */
public class SbmkDao extends BaseDao<SbmkBean, Integer> {

    public SbmkDao(Context context) {
        super(context);
    }

    @Override
    public Dao getDao() throws SQLException {
        return getHelper().getDao(SbmkBean.class);
    }

    public void getData(final int tag, final int pageNo, final String str, final DataSource.Callback callback) {
        HashMap<String, String> map = new HashMap<>();
        map.put("FLH", str);
        map.put("MC", str);
        getDataLike(tag, pageNo, map, callback);
    }
}
