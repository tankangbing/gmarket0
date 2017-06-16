package com.itheima.gmarket.base;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.itheima.gmarket.R;
import com.itheima.gmarket.model.net.AppInfo;
import com.itheima.gmarket.utils.CommonUtil;
import com.itheima.gmarket.utils.LogUtil;

import java.util.ArrayList;
import java.util.List;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

/**
 * Created by Administrator on 2017/2/5 0005.
 * 简单的列表适配器的基类
 */

public abstract class SimpleListBaseAdapter<T> extends BaseAdapter {
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
    @Override
    public abstract  View getView(int position, View convertView, ViewGroup parent) ;
}
