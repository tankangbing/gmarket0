package com.itheima.gmarket.adapter;

import android.view.ViewGroup;

import com.itheima.gmarket.base.BaseHolder;
import com.itheima.gmarket.base.GMBaseListAdapter;
import com.itheima.gmarket.holder.SubjectHolder;
import com.itheima.gmarket.model.net.SubjectInfo;

/**
 * Created by Administrator on 2017/2/9 0009.
 */

public class SubjectAdapter extends GMBaseListAdapter<SubjectInfo> {
    @Override
    protected BaseHolder getViewHolder(ViewGroup parent) {
        return new SubjectHolder(parent);
    }
}
