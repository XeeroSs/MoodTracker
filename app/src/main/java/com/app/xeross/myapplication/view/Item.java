package com.app.xeross.myapplication.view;

/**
 * Created by XeroSs on 14/02/2018.
 */

public class Item {

    private String name;
    private int in;
    private long id;
    private String ColorFrameLayout;
    private String st;

    public Item() {
    }

    public Item(String name, String ColorFrameLayout, int in, String st) {
        this.name = name;
        this.ColorFrameLayout = ColorFrameLayout;
        this.in = in;
        this.st = st;
        //params.setMargins(1, 1, i, 1);
        //mFrameLayout.setLayoutParams(params);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColorFrameLayout() {
        return ColorFrameLayout;
    }

    public void setColorFrameLayout(String colorFrameLayout) {
        ColorFrameLayout = colorFrameLayout;
    }

    public int getInt() {
        return in;
    }

    public void setInt(int in) {
        this.in = in;
    }

    public String getSt() {
        return st;
    }

    public void setSt(String st) {
        this.st = st;
    }
}
