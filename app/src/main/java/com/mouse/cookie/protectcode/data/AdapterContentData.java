package com.mouse.cookie.protectcode.data;

/**
 * Created by cookie on 2016/12/16.
 */

public class AdapterContentData {
    private boolean statu;
    private int rowid;
    private int type;
    private String title;
    private String descrip;
    private String password;

    public AdapterContentData(int type, String title, String descrip, String password) {
        this.statu = false;
        this.type = type;
        this.title = title;
        this.descrip = descrip;
        this.password = password;
    }

    public AdapterContentData(int rowid, int type, String title, String descrip, String password) {
        this.statu = false;
        this.rowid = rowid;
        this.type = type;
        this.title = title;
        this.descrip = descrip;
        this.password = password;
    }

    public boolean getStatu() {
        return statu;
    }

    public void setStatu(boolean statu) {
        this.statu = statu;
    }

    public int getRowid() {
        return rowid;
    }

    public void setRowid(int rowid) {
        this.rowid = rowid;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescrip() {
        return descrip;
    }

    public void setDescrip(String descrip) {
        this.descrip = descrip;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
