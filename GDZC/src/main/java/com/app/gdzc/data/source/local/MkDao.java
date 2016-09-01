package com.app.gdzc.data.source.local;

import android.app.Activity;

import com.app.gdzc.data.bean.MkBean;
import com.app.gdzc.net.ResponseListener;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.HashMap;

/**
 * Created by 王少岩 on 2016/9/1.
 */
public class MkDao extends BaseDao<MkBean, Integer> {
    public static final String MKDAO_TAG_SBLY = "mkdao_sbly";
    public static final String MKDAO_TAG_XZ = "mkdao_xz";
    public static final String MKDAO_TAG_SYFX = "mkdao_tag_syfx";
    public static final String MKDAO_TAG_JFKM = "mkdao_tag_jfkm";
    public MkDao(Activity activity) {
        super(activity);
    }

    @Override
    protected Dao<MkBean, Integer> getDao() throws SQLException {
        return getHelper().getDao(MkBean.class);
    }

    public void getSbly(ResponseListener listener) {
        HashMap<String, String> map = new HashMap<>();
        map.put("BJ", "设备来源");
        map.put("BJ2", "1.0");
        getDataAndLike(MKDAO_TAG_SBLY, 0, map, listener, RIGHT);
    }

    public void getXz(ResponseListener listener){
        HashMap<String, String> map = new HashMap<>();
        map.put("BJ", "现状");
        map.put("BJ2", "1.0");
        getDataAndLike(MKDAO_TAG_XZ, 0, map, listener, RIGHT);
    }

    public void getSyfx(ResponseListener listener){
        HashMap<String, String> map = new HashMap<>();
        map.put("BJ", "使用方向");
        map.put("BJ2", "1.0");
        getDataAndLike(MKDAO_TAG_SYFX, 0, map, listener, RIGHT);
    }

    public void getJfkm(ResponseListener listener){
        HashMap<String, String> map = new HashMap<>();
        map.put("BJ", "经费科目");
        map.put("BJ2", "1.0");
        getDataAndLike(MKDAO_TAG_JFKM, 0, map, listener, RIGHT);
    }
}
