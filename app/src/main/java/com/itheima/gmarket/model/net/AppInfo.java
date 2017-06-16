package com.itheima.gmarket.model.net;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/2/4 0004.
 */

public class AppInfo implements Parcelable{
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

    //内容描述 ，不用管
    @Override
    public int describeContents() {
        return 0;
    }
  //打包 ，注意打包的顺序与拆包的顺序一致
    @Override
    public void writeToParcel(Parcel dest, int flags) {
/*        private String des;
        private String downloadUrl;
        private String iconUrl;
        private int id;
        private String name;
        private String packageName;
        private int size;
        private float stars;*/
        dest.writeString(getDes());
        dest.writeString(getDownloadUrl());
        dest.writeString(getIconUrl());
        dest.writeString(getName());
        dest.writeString(getPackageName());
        dest.writeInt(getId());
        dest.writeInt(getSize());
        dest.writeFloat(getStars());
    }
    public static final Parcelable.Creator<AppInfo> CREATOR
            = new Parcelable.Creator<AppInfo>() {
        public AppInfo createFromParcel(Parcel in) {
            return new AppInfo(in);
        }

        public AppInfo[] newArray(int size) {
            return new AppInfo[size];
        }
    };

    //构造成对象 ，拆包过程，注意拆包与打包要一致
    private AppInfo(Parcel in) {
        setDes(in.readString());
        setDownloadUrl(in.readString());
        setIconUrl(in.readString());
        setName(in.readString());
        setPackageName(in.readString());
        setId(in.readInt());
        setSize(in.readInt());
        setStars(in.readFloat());
    }

    //准备一个空的构造方法
    public  AppInfo(){

    }


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
