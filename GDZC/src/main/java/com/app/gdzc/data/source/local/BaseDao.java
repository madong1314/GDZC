package com.app.gdzc.data.source.local;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by 王少岩 on 2016/8/24.
 */
public abstract class BaseDao<T, Integer> {
    protected DatabaseHelper mDataBaseHelper;
    protected Context mContext;

    public BaseDao(Context context) {
        mContext = context;
        getHelper();
    }

    public DatabaseHelper getHelper() {
        if (mDataBaseHelper == null) {
            mDataBaseHelper = DatabaseHelper.getHelper(mContext);
        }
        return mDataBaseHelper;
    }

    public abstract Dao<T, Integer> getDao() throws SQLException;

    public int save(T t) throws SQLException {
        return getDao().create(t);
    }

    public List<T> query(PreparedQuery<T> preparedQuery) throws SQLException {
        Dao<T, Integer> dao = getDao();
        return dao.query(preparedQuery);
    }

    public List<T> query(String attributeName, String attributeValue) throws SQLException {
        QueryBuilder<T, Integer> queryBuilder = getDao().queryBuilder();
        queryBuilder.where().eq(attributeName, attributeValue);
        PreparedQuery<T> preparedQuery = queryBuilder.prepare();
        return query(preparedQuery);
    }
}
