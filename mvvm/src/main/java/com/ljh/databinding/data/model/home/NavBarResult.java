package com.ljh.databinding.data.model.home;

import java.io.Serializable;

public class NavBarResult implements Serializable {

    /**
     * id : 1
     * navName : 店家分红
     * navSort : 1
     * url : null
     */

    private int id;
    private String navName;
    private int navSort;
    private String url;

    public boolean isSelected;//用于判断是否选中

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNavName() {
        return navName;
    }

    public void setNavName(String navName) {
        this.navName = navName;
    }

    public int getNavSort() {
        return navSort;
    }

    public void setNavSort(int navSort) {
        this.navSort = navSort;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
