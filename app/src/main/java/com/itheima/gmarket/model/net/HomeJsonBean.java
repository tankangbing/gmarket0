package com.itheima.gmarket.model.net;

import java.util.List;

/**
 * Created by Administrator on 2017/2/5 0005.
 * 首页界面的json bean
 */

public class HomeJsonBean {

    private List<AppInfo> list;
    private List<String> picture;

    public List<AppInfo> getList() {
        return list;
    }

    public void setList(List<AppInfo> list) {
        this.list = list;
    }

    public List<String> getPicture() {
        return picture;
    }

    public void setPicture(List<String> picture) {
        this.picture = picture;
    }

}
