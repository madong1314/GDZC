package com.app.gdzc.data.bean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by 王少岩 on 2016/8/29.
 */
@DatabaseTable(tableName = "S_SBMK")
public class SbmkBean {
    @DatabaseField(columnName = "FLH")
    private String flh;
    @DatabaseField(columnName = "MC")
    private String mc;

    public SbmkBean() {
    }

    public String getFlh() {
        return flh;
    }

    public void setFlh(String flh) {
        this.flh = flh;
    }

    public String getMc() {
        return mc;
    }

    public void setMc(String mc) {
        this.mc = mc;
    }
}
