package com.app.gdzc.data.source.local;

import android.content.Context;

import com.app.gdzc.data.bean.ZJBean;
import com.app.gdzc.data.source.DataSource;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;

/**
 * Created by 王少岩 on 2016/8/26.
 */
public class ZJDao extends BaseDao<ZJBean, Integer> {

    public ZJDao(Context context) {
        super(context);
    }

    @Override
    public Dao<ZJBean, Integer> getDao() throws SQLException {
        return getHelper().getDao(ZJBean.class);
    }

    public void createZJ(int tag, ZJBean zjBean, DataSource.Callback callback){
        try {
            if(save(zjBean)>0)
                callback.onComplete(tag, zjBean);
            else
                callback.onError(tag,"保存失败");
        } catch (SQLException e) {
            callback.onError(tag,"保存失败");
            e.printStackTrace();
        }
    }
}
