package com.itheima.gmarket.model.db;

import android.app.Application;

import com.itheima.gmarket.utils.CommonUtil;
import com.itheima.gmarket.views.ContentPage;

/**
 * Created by Administrator on 2017/2/4 0004.
 */

public class AppEntity {
    private String name;
    public  AppEntity(){
        CommonUtil.isMainThread();
    }

}
