package com.app.gdzc.data.bean;

import android.util.Log;

import com.app.gdzc.base.BaseApplication;
import com.app.gdzc.utils.Utils;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.List;
import java.util.Map;

/**
 * Created by 王少岩 on 2016/8/26.
 */
@DatabaseTable(tableName = "S_ZJ")
public class ZJBean {
    @DatabaseField(columnName = "仪器名称")
    private String yqmc;
    @DatabaseField(columnName = "分类号")
    private String flh;
    @DatabaseField(columnName = "领用单位号")
    private String lydwh;
    @DatabaseField(columnName = "型号")
    private String xh;
    @DatabaseField(columnName = "规格")
    private String gg;
    @DatabaseField(columnName = "厂家")
    private String cj;
    @DatabaseField(columnName = "出厂号")
    private String cch;
    @DatabaseField(columnName = "购置日期")
    private String gzrq;
    @DatabaseField(columnName = "出厂日期")
    private String ccrq;
    @DatabaseField(columnName = "保修期限")
    private String bxqx;
    @DatabaseField(columnName = "存放地名称")
    private String cfdmc;
    @DatabaseField(columnName = "经费科目")
    private String jfkm;
    @DatabaseField(columnName = "使用方向")
    private String syfx;
    @DatabaseField(columnName = "领用人")
    private String lyr;
    @DatabaseField(columnName = "经手人")
    private String jsr;
    @DatabaseField(columnName = "科研号")
    private String kyh;
    @DatabaseField(columnName = "国别码")
    private String gbm;
    @DatabaseField(columnName = "供货商")
    private String ghs;
    @DatabaseField(columnName = "字符字段7")
    private String flmc;

    public ZJBean() {
    }

    public String getYqmc() {
        return yqmc;
    }

    public void setYqmc(String yqmc) {
        this.yqmc = yqmc;
    }

    public String getFlh() {
        return flh;
    }

    public void setFlh(String flh) {
        this.flh = flh;
    }

    public String getLydwh() {
        return lydwh;
    }

    public void setLydwh(String lydwh) {
        this.lydwh = lydwh;
    }

    public String getXh() {
        return xh;
    }

    public void setXh(String xh) {
        this.xh = xh;
    }

    public String getGg() {
        return gg;
    }

    public void setGg(String gg) {
        this.gg = gg;
    }

    public String getCj() {
        return cj;
    }

    public void setCj(String cj) {
        this.cj = cj;
    }

    public String getCch() {
        return cch;
    }

    public void setCch(String cch) {
        this.cch = cch;
    }

    public String getGzrq() {
        return gzrq;
    }

    public void setGzrq(String gzrq) {
        this.gzrq = gzrq;
    }

    public String getCcrq() {
        return ccrq;
    }

    public void setCcrq(String ccrq) {
        this.ccrq = ccrq;
    }

    public String getBxqx() {
        return bxqx;
    }

    public void setBxqx(String bxqx) {
        this.bxqx = bxqx;
    }

    public String getCfdmc() {
        return cfdmc;
    }

    public void setCfdmc(String cfdmc) {
        this.cfdmc = cfdmc;
    }

    public String getJfkm() {
        return jfkm;
    }

    public void setJfkm(String jfkm) {
        this.jfkm = jfkm;
    }

    public String getSyfx() {
        return syfx;
    }

    public void setSyfx(String syfx) {
        this.syfx = syfx;
    }

    public String getLyr() {
        return lyr;
    }

    public void setLyr(String lyr) {
        this.lyr = lyr;
    }

    public String getJsr() {
        return jsr;
    }

    public void setJsr(String jsr) {
        this.jsr = jsr;
    }

    public String getKyh() {
        return kyh;
    }

    public void setKyh(String kyh) {
        this.kyh = kyh;
    }

    public String getGbm() {
        return gbm;
    }

    public void setGbm(String gbm) {
        this.gbm = gbm;
    }

    public String getGhs() {
        return ghs;
    }

    public void setGhs(String ghs) {
        this.ghs = ghs;
    }

    public String getFlmc() {
        return flmc;
    }

    public void setFlmc(String flmc) {
        this.flmc = flmc;
    }

    public static ZJBean mapToZJBean(Map<String, String> map) {
        ZJBean zjBean = new ZJBean();
        for (String key : map.keySet()) {
            Log.e(key, map.get(key));
            switch (key) {
                case "仪器名称":
                    zjBean.setYqmc(map.get(key));
                    break;
                case "分类号":
                    zjBean.setFlh(map.get(key));
                    break;
                case "领用单位号":
                    zjBean.setLydwh(map.get(key));
                    break;
                case "型号":
                    zjBean.setXh(map.get(key));
                    break;
                case "规格":
                    zjBean.setGg(map.get(key));
                    break;
                case "厂家":
                    zjBean.setCj(map.get(key));
                    break;
                case "出厂号":
                    zjBean.setCch(map.get(key));
                    break;
                case "购置日期":
                    zjBean.setGzrq(map.get(key));
                    break;
                case "出厂日期":
                    zjBean.setCcrq(map.get(key));
                    break;
                case "保修期限":
                    zjBean.setBxqx(map.get(key));
                    break;
                case "存放地名称":
                    zjBean.setCfdmc(map.get(key));
                    break;
                case "经费科目":
                    zjBean.setJfkm(map.get(key));
                    break;
                case "使用方向":
                    zjBean.setSyfx(map.get(key));
                    break;
                case "领用人":
                    zjBean.setLyr(map.get(key));
                    break;
                case "经手人":
                    zjBean.setJsr(map.get(key));
                    break;
                case "科研号":
                    zjBean.setKyh(map.get(key));
                    break;
                case "国别码":
                    zjBean.setGbm(map.get(key));
                    break;
                case "供货商":
                    zjBean.setGhs(map.get(key));
                    break;
                case "分类名称":
                    zjBean.setFlmc(map.get(key));
                    break;
            }
        }
        return zjBean;
    }

    public static boolean isCorrect(Map<String, String> map, List<TsxxBean> list){
        boolean isCorrect = true;
        for(TsxxBean bean:list){
            if(bean.getCanBeNull().equals("1")){
                if(!map.keySet().contains(bean.getShowContent())){
                    isCorrect = false;
                    Utils.showToast(BaseApplication.getAppContext(), bean.getHintContent());
                    return isCorrect;
                }
            }
        }
        return isCorrect;
    }
}
