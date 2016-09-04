package com.mobileapplicationsclass.pykandlxm.model;

/**
 * Created by Administrator on 2016/9/2 0002.
 */
public class DrawerList {
    public String name;
    public int logo;

    public DrawerList(String name, int logo) {
        this.name = name;
        this.logo = logo;
    }

    public DrawerList(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLogo() {
        return logo;
    }

    public void setLogo(int logo) {
        this.logo = logo;
    }
}
