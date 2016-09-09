package com.mobileapplicationsclass.pykandlxm.model;

/**
 * Created by Administrator on 2016/9/8 0008.
 */
public class StarGrid {
    public String name;
    private int logo;

    public StarGrid(String name, int logo) {
        this.name = name;
        this.logo = logo;
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
