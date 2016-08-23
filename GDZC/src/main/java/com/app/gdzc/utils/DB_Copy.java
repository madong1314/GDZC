package com.app.gdzc.utils;

import android.content.Context;
import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by 王少岩 on 2016/8/23.
 */
public class DB_Copy {
    private Context mContext;
    private String mDB_PATH = Environment.getExternalStorageDirectory() + "/gdzc/database/";
    private String mDATABASE_NAME = "gdzc.db";

    public DB_Copy(Context context) {
        mContext = context;
    }

    /**
     * 判断数据库文件是否存在
     *
     * @return
     */
    public boolean isDataBaseExist() {
        File dbFile = new File(mDB_PATH + mDATABASE_NAME);
        return dbFile.exists();
    }

    /**
     * 删除数据库文件
     */
    public void delteFile(){
        String collectName = mDB_PATH + mDATABASE_NAME;
        File collectFile = new File(collectName);
        collectFile.delete();
    }

    /**
     * 存储数据库文件
     */
    public void copyDataBase() {
        try {
            InputStream myInput = mContext.getAssets().open(mDATABASE_NAME);
            File dir = new File(mDB_PATH);
            if(!dir.exists()){
                dir.mkdirs();
            }
            File file = new File(mDB_PATH+mDATABASE_NAME);
            if(!file.exists()){
                file.createNewFile();
            }
            OutputStream myOutput = new FileOutputStream(file);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = myInput.read(buffer)) > 0) {
                myOutput.write(buffer, 0, length);
            }
            // Close the streams
            myOutput.flush();
            myOutput.close();
            myInput.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
