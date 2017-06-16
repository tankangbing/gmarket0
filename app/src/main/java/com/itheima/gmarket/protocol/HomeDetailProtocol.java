package com.itheima.gmarket.protocol;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.itheima.gmarket.base.BaseProtocol;
import com.itheima.gmarket.model.net.HomeDetailBean;

/**
 * Created by Administrator on 2017/2/11 0011.
 */

public class HomeDetailProtocol extends BaseProtocol<HomeDetailBean> {
    private  String packageName;

    //提供一个设置包名的方法
    public  void setPackageName(String p){
        packageName=p;
    }
    @Override
    protected String getParams() {
        return "&packageName="+packageName;
    }

    @Override
    protected String getKey() {
        return "detail";
    }

    @Override
    protected HomeDetailBean parseData(String result) {
        if(!TextUtils.isEmpty(result)){
            Gson gson=new Gson();
            HomeDetailBean homeDetailBean = gson.fromJson(result, HomeDetailBean.class);
            return  homeDetailBean;
        }
        return null;
    }
}
