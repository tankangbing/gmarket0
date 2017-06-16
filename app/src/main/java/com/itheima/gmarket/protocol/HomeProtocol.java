package com.itheima.gmarket.protocol;

import com.google.gson.Gson;
import com.itheima.gmarket.base.BaseProtocol;
import com.itheima.gmarket.model.net.HomeJsonBean;

/**
 * Created by Administrator on 2017/2/5 0005.
 * 首页访问网络的类
 */

public class HomeProtocol extends BaseProtocol<HomeJsonBean> {
    //取得访问首页的参数
    @Override
    protected String getParams() {
        return "";
    }
    //取得访问首页的 页面
    @Override
    protected String getKey() {
        return "home";
    }
   //解析首页json数据,通过Gson 来解析json数据
    @Override
    protected HomeJsonBean parseData(String jsonStr) {
        Gson gson=new Gson();
        HomeJsonBean homeJsonBean = gson.fromJson(jsonStr, HomeJsonBean.class);

        return homeJsonBean;
    }
}
