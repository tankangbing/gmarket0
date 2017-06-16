package com.itheima.gmarket.model.net;

import java.util.List;

/**
 * Created by Administrator on 2016/12/14 0014.
 * 首页应用的详情jsonbean
 */

public class HomeDetailBean {

    /**
     * author : 有缘网
     * date : 2014-04-24
     * des : 产
     * downloadNum : 200万+
     * downloadUrl : app/com.youyuan.yyhl/com.youyuan.yyhl.apk
     * iconUrl : app/com.youyuan.yyhl/icon.jpg
     * id : 1525490
     * name : 有缘网
     * packageName : com.youyuan.yyhl
     * safe : [{"safeDes":"已通过安智市场官方认证，是正版软件","safeDesColor":0,"safeDesUrl":"app/com.youyuan.yyhl/safeDesUrl0.jpg","safeUrl":"app/com.youyuan.yyhl/safeIcon0.jpg"},{"safeDes":"已通过安智市场安全检测，请放心使用","safeDesColor":0,"safeDesUrl":"app/com.youyuan.yyhl/safeDesUrl1.jpg","safeUrl":"app/com.youyuan.yyhl/safeIcon1.jpg"},{"safeDes":"无任何形式的广告","safeDesColor":0,"safeDesUrl":"app/com.youyuan.yyhl/safeDesUrl2.jpg","safeUrl":"app/com.youyuan.yyhl/safeIcon2.jpg"}]
     * screen : ["app/com.youyuan.yyhl/screen0.jpg","app/com.youyuan.yyhl/screen1.jpg","app/com.youyuan.yyhl/screen2.jpg","app/com.youyuan.yyhl/screen3.jpg","app/com.youyuan.yyhl/screen4.jpg"]
     * size : 3876203
     * stars : 4.0
     * version : 4.1.9
     */

    private String author;
    private String date;
    private String des;
    private String downloadNum;
    private String downloadUrl;
    private String iconUrl;
    private int id;
    private String name;
    private String packageName;
    private int size;
    private double stars;
    private String version;
    private List<SafeBean> safe;
    private List<String> screen;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    public String getDes() {
        return des;
    }
    public void setDes(String des) {
        this.des = des;
    }

    public String getDownloadNum() {
        return downloadNum;
    }

    public void setDownloadNum(String downloadNum) {
        this.downloadNum = downloadNum;
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

    public double getStars() {
        return stars;
    }

    public void setStars(double stars) {
        this.stars = stars;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public List<SafeBean> getSafe() {
        return safe;
    }

    public void setSafe(List<SafeBean> safe) {
        this.safe = safe;
    }

    public List<String> getScreen() {
        return screen;
    }

    public void setScreen(List<String> screen) {
        this.screen = screen;
    }

    public static class SafeBean {
        /**
         * safeDes : 已通过安智市场官方认证，是正版软件
         * safeDesColor : 0
         * safeDesUrl : app/com.youyuan.yyhl/safeDesUrl0.jpg
         * safeUrl : app/com.youyuan.yyhl/safeIcon0.jpg
         */

        private String safeDes;
        private int safeDesColor;
        private String safeDesUrl;
        private String safeUrl;

        public String getSafeDes() {
            return safeDes;
        }

        public void setSafeDes(String safeDes) {
            this.safeDes = safeDes;
        }

        public int getSafeDesColor() {
            return safeDesColor;
        }

        public void setSafeDesColor(int safeDesColor) {
            this.safeDesColor = safeDesColor;
        }

        public String getSafeDesUrl() {
            return safeDesUrl;
        }

        public void setSafeDesUrl(String safeDesUrl) {
            this.safeDesUrl = safeDesUrl;
        }

        public String getSafeUrl() {
            return safeUrl;
        }

        public void setSafeUrl(String safeUrl) {
            this.safeUrl = safeUrl;
        }
    }
}
