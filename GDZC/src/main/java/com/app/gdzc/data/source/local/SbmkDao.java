package com.app.gdzc.data.source.local;

import android.app.Activity;

import com.app.gdzc.data.bean.FlhBean;
import com.app.gdzc.net.ResponseListener;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.HashMap;

/**
 * Created by 王少岩 on 2016/8/29.
 */
public class SbmkDao extends BaseDao<FlhBean, Integer> {

    public static final String SBMKDAO_TAG = "sbmkdao";
    public SbmkDao(Activity activity) {
        super(activity);
    }

    @Override
    public Dao getDao() throws SQLException {
        return getHelper().getDao(FlhBean.class);
    }

    public void getData(int pageNo, String keyWord, ResponseListener listener) {
        HashMap<String, String> map = new HashMap<>();
        map.put("FLH", keyWord);
        map.put("MC", keyWord);
        getDataOrLike(SBMKDAO_TAG, pageNo, map, listener);
    }

    public String getMcByBh(String flh){
        String mc = "";
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("FLH",flh);
        try {
            FlhBean flhBean = query(0, hashMap).get(0);
            mc = flhBean.getMc();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return mc;
    }
}
