package com.app.gdzc.data.source.local;

import android.app.Activity;

import com.app.gdzc.data.bean.ZJBean;
import com.app.gdzc.net.ResponseListener;
import com.j256.ormlite.dao.Dao;

import org.json.JSONException;

import java.sql.SQLException;

/**
 * Created by 王少岩 on 2016/8/26.
 */
public class ZJDao extends BaseDao<ZJBean, Integer> {

    public static final String ZJ_CREATE = "zj_create";

    public ZJDao(Activity activity) {
        super(activity);
    }

    @Override
    public Dao<ZJBean, Integer> getDao() throws SQLException {
        return getHelper().getDao(ZJBean.class);
    }

    public void createZJ(ZJBean zjBean, ResponseListener listener) {
        try {
            if (save(zjBean) > 0)
                listener.requestCompleted(ZJ_CREATE, zjBean);
            else
                listener.requestError(ZJ_CREATE, "保存失败");
        } catch (JSONException e) {
            listener.requestError(ZJ_CREATE, "保存失败");
            e.printStackTrace();
        } catch (SQLException e) {
            listener.requestError(ZJ_CREATE, "保存失败");
            e.printStackTrace();
        }
    }
}
