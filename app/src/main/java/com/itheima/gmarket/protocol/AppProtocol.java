package com.itheima.gmarket.protocol;

import com.google.gson.Gson;
import com.itheima.gmarket.base.BaseProtocol;
import com.itheima.gmarket.model.net.AppInfo;
import com.itheima.gmarket.model.net.HomeJsonBean;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Administrator on 2017/2/5 0005.
 * 首页访问网络的类
 */

public class AppProtocol extends BaseProtocol< List<AppInfo>> {
    //取得访问首页的参数
    @Override
    protected String getParams() {
        return "";
    }
    //取得访问首页的 页面
    @Override
    protected String getKey() {
        return "app";
    }
   //解析首页json数据,通过Gson 来解析json数据
    @Override
    protected List<AppInfo> parseData(String jsonStr) {
        Gson gson=new Gson();
        AppInfo[] appInfos = gson.fromJson(jsonStr, AppInfo[].class);
        //把数组转化为集合
        List<AppInfo> applist = Arrays.asList(appInfos);

        return applist;
    }
}
