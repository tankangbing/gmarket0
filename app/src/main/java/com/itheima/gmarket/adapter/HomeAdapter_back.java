package com.itheima.gmarket.adapter;

import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.itheima.gmarket.R;
import com.itheima.gmarket.base.SimpleListBaseAdapter;
import com.itheima.gmarket.model.net.AppInfo;
import com.itheima.gmarket.utils.CommonUtil;

/**
 * Created by Administrator on 2017/2/5 0005.
 */


public class HomeAdapter_back extends SimpleListBaseAdapter<AppInfo> {

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
//        LogUtil.e("HomeAdapter-->getView");
        HomeHolder homeHolder=null;
        if(convertView==null){
            homeHolder=new HomeHolder();
            convertView= CommonUtil.getInflate().inflate(R.layout.item_home,parent,false);
            homeHolder.holderNameTv= (TextView) convertView.findViewById(R.id.tv_name);
            convertView.setTag(homeHolder);
        }else {
            homeHolder= (HomeHolder) convertView.getTag();
        }

        //取得当前列表条目要装配的数据
        AppInfo appInfo = list.get(position);
        homeHolder.holderNameTv.setText(appInfo.getName());
        homeHolder.holderNameTv.setTextColor(Color.RED);

        return convertView;
    }
    private static  class HomeHolder{
        TextView holderNameTv;

    }
}
