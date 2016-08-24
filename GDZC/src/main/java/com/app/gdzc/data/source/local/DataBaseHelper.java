package com.app.gdzc.data.source.local;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;

/**
 * Created by 王少岩 on 2016/8/23.
 */
public class DataBaseHelper extends OrmLiteSqliteOpenHelper {
    private Context mContext;

    private DataBaseHelper(Context context) {
        super(context, "/data/data/" + context.getPackageName() +"/databases/gdbc.db", null, 1);
        mContext = context;
        SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase("/data/data/" + mContext.getPackageName() +"/databases/gdbc.db",null);
        onCreate(db);
        db.close();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource, int i, int i1) {
    }

    @Override
    public synchronized SQLiteDatabase getWritableDatabase() {
        return SQLiteDatabase.openDatabase("/data/data/" + mContext.getPackageName() +"/databases/gdbc.db", null, SQLiteDatabase.OPEN_READWRITE);
    }

    public synchronized SQLiteDatabase getReadableDatabase() {
        return SQLiteDatabase.openDatabase("/data/data/" + mContext.getPackageName() +"/databases/gdbc.db", null, SQLiteDatabase.OPEN_READONLY);
    }

    /**
     * 释放资源
     */
    @Override
    public void close() {
        super.close();
    }
}
