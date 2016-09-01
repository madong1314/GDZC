package com.app.gdzc.data.bean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by 王少岩 on 2016/9/1.
 * BJ, NR, BJ2, BJ3, Bj4, czdm, czmc, ID, 校编号, 校名称
 */
@DatabaseTable(tableName = "MK1")
public class MkBean {
    @DatabaseField(columnName = "BJ")
    private String bj;
    @DatabaseField(columnName = "NR")
    private String nr;
    @DatabaseField(columnName = "BJ2")
    private String bj2;
    @DatabaseField(columnName = "BJ3")
    private String bj3;
    @DatabaseField(columnName = "Bj4")
    private String bj4;
    @DatabaseField(columnName = "czdm")
    private String czdm;
    @DatabaseField(columnName = "czmc")
    private String czmc;
    @DatabaseField(columnName = "ID")
    private String Id;
    @DatabaseField(columnName = "校编号")
    private String code;
    @DatabaseField(columnName = "校名称")
    private String mc;

    public MkBean() {
    }

    public String getBj() {
        return bj;
    }

    public void setBj(String bj) {
        this.bj = bj;
    }

    public String getNr() {
        return nr;
    }

    public void setNr(String nr) {
        this.nr = nr;
    }

    public String getBj2() {
        return bj2;
    }

    public void setBj2(String bj2) {
        this.bj2 = bj2;
    }

    public String getBj3() {
        return bj3;
    }

    public void setBj3(String bj3) {
        this.bj3 = bj3;
    }

    public String getBj4() {
        return bj4;
    }

    public void setBj4(String bj4) {
        this.bj4 = bj4;
    }

    public String getCzdm() {
        return czdm;
    }

    public void setCzdm(String czdm) {
        this.czdm = czdm;
    }

    public String getCzmc() {
        return czmc;
    }

    public void setCzmc(String czmc) {
        this.czmc = czmc;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMc() {
        return mc;
    }

    public void setMc(String mc) {
        this.mc = mc;
    }
}
