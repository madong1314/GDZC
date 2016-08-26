package com.app.gdzc.data.bean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by 王少岩 on 2016/8/26.
 */
@DatabaseTable(tableName = "S_DW")
public class LydwBean {
    @DatabaseField(columnName = "单位编号")
    private String dwbh;
    @DatabaseField(columnName = "单位名称")
    private String dwmc;
    @DatabaseField(columnName = "单位简称")
    private String dwjc;
    @DatabaseField(columnName = "单位简码")
    private String dwjm;

    public LydwBean() {
    }

    public String getDwbh() {
        return dwbh;
    }

    public void setDwbh(String dwbh) {
        this.dwbh = dwbh;
    }

    public String getDwmc() {
        return dwmc;
    }

    public void setDwmc(String dwmc) {
        this.dwmc = dwmc;
    }

    public String getDwjc() {
        return dwjc;
    }

    public void setDwjc(String dwjc) {
        this.dwjc = dwjc;
    }

    public String getDwjm() {
        return dwjm;
    }

    public void setDwjm(String dwjm) {
        this.dwjm = dwjm;
    }
}
