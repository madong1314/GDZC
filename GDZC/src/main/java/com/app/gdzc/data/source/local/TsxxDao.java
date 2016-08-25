package com.app.gdzc.data.source.local;

import android.content.Context;

import com.app.gdzc.data.bean.TsxxBean;
import com.app.gdzc.data.source.DataSource;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by 王少岩 on 2016/8/25.
 */
public class TsxxDao extends BaseDao<TsxxBean, Integer>{

    public TsxxDao(Context context) {
        super(context);
    }

    @Override
    public Dao<TsxxBean, Integer> getDao() throws SQLException {
        return getHelper().getDao(TsxxBean.class);
    }

    public void getData(int tag, DataSource.Callback<List<TsxxBean>> callback){
        try {
            callback.onComplete(tag, queryForAll());
        } catch (SQLException e) {
            callback.onError(tag, "查询失败");
            e.printStackTrace();
        }
    }

}
