package com.itheima.gmarket.adapter;

import android.view.ViewGroup;

import com.itheima.gmarket.base.BaseHolder;
import com.itheima.gmarket.base.GMBaseListAdapter;
import com.itheima.gmarket.holder.AppHolder;
import com.itheima.gmarket.holder.HomeHolder;
import com.itheima.gmarket.model.net.AppInfo;

/**
 * Created by Administrator on 2017/2/5 0005.
 */


public class AppAdapter extends GMBaseListAdapter<AppInfo> {
    @Override
    protected BaseHolder getViewHolder(ViewGroup parent) {
        return new AppHolder(parent);
    }

}
