package com.itheima.gmarket.model.net;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/2/4 0004.
 */

public class AppInfo_back implements Serializable{
    /**
     * des : 产品介绍：google市场app测试。
     * downloadUrl : app/com.itheima.www/com.itheima.www.apk
     * iconUrl : app/com.itheima.www/icon.jpg
     * id : 1
     * name : 黑马程序员
     * packageName : com.itheima.www
     * size : 91767
     * stars : 5
     */

    private String des;
    private String downloadUrl;
    private String iconUrl;
    private int id;
    private String name;
    private String packageName;
    private int size;
    private float stars;

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public float getStars() {
        return stars;
    }

    public void setStars(float stars) {
        this.stars = stars;
    }
}
