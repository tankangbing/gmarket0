package com.itheima.gmarket.base;

import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.itheima.gmarket.R;
import com.itheima.gmarket.model.net.AppInfo;
import com.itheima.gmarket.utils.CommonUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/2/5 0005.
 * 简单的列表适配器的基类
 */

public abstract class GMBaseListAdapter_back<T> extends BaseAdapter {
    protected List<T> list=new ArrayList<>();
    //对外一个一个设置list数据的方法
    public  void setList(List<T> datas){
        list=datas;
        notifyDataSetChanged();//数据集发送改变，更新listview界面
    }
    public  List<T> getList(){
        return  list;
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    //把getView（）方法公共能实现的操作，在基类中实现
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        BaseHolder viewholder=null;
        //1. 当ConvertView 为空时
        if(convertView==null){
            //2. 构建一个ViewHolder对象
            viewholder=getViewHolder(parent);
            //把 3、4、5步骤都放置在ViewHolder的构造方法中
            //3. 把列表条目的xml布局实例化为view对象，返回的是列表条目xml布局的根节点视图对象
//            convertView= CommonUtil.getInflate().inflate(R.layout.item_home,parent,false);
            //4. 通过ViewHolder绑定要操作的列表条目布局的子控件
//            homeHolder.holderNameTv= (TextView) convertView.findViewById(R.id.tv_name);
            //5. 把viewHolder以tag的形式添加到convertView中
//            convertView.setTag(homeHolder);
        }else {
            //6. 当ConvertView不为空时，ViewHolder通过convertView的tag 来获取
            viewholder= (BaseHolder) convertView.getTag();
        }
        //取得当前列表条目要装配的数据
        //7. 取得当前列表条目要展示的数据
//        AppInfo appInfo = list.get(position);

        //把绑定数据的操作 放置在ViewHolder中，通过调用ViewHolder.bindData()来执行
//        homeHolder.bindData(appInfo);
 /*
        //8. 通过ViewHolder绑定数据到对应的列表条目子控件中
//        homeHolder.holderNameTv.setText(appInfo.getName());
//        homeHolder.holderNameTv.setTextColor(Color.RED);*/
        //9. 返回的是列表条目xml布局的根节点视图对象
        return viewholder.getRootView();
    }
    // 由于Adapter的基类中不能决定Holder的实现，所以要抽象出去
    protected  abstract  BaseHolder getViewHolder(ViewGroup parent);
}
