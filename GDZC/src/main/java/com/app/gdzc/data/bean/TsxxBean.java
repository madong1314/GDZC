package com.app.gdzc.data.bean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by 王少岩 on 2016/8/25.
 */
@DatabaseTable(tableName = "s_tsxx")
public class TsxxBean {
    @DatabaseField(columnName = "字段名")
    private String colName;
    @DatabaseField(columnName = "英文字段")
    private String colNameEng;
    @DatabaseField(columnName = "必填否")
    private String canBeNull;
    @DatabaseField(columnName = "提示内容")
    private String hintContent;
    @DatabaseField(columnName = "显示内容")
    private String showContent;
    @DatabaseField(columnName = "id")
    private int id;

    public TsxxBean() {
    }

    public String getColName() {
        return colName;
    }

    public void setColName(String colName) {
        this.colName = colName;
    }

    public String getColNameEng() {
        return colNameEng;
    }

    public void setColNameEng(String colNameEng) {
        this.colNameEng = colNameEng;
    }

    public String getCanBeNull() {
        return canBeNull;
    }

    public void setCanBeNull(String canBeNull) {
        this.canBeNull = canBeNull;
    }

    public String getHintContent() {
        return hintContent;
    }

    public void setHintContent(String hintContent) {
        this.hintContent = hintContent;
    }

    public String getShowContent() {
        return showContent;
    }

    public void setShowContent(String showContent) {
        this.showContent = showContent;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
