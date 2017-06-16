package com.itheima.gmarket.holder;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.itheima.gmarket.R;
import com.itheima.gmarket.base.BaseHolder;
import com.itheima.gmarket.model.net.HomeDetailBean;
import com.itheima.gmarket.utils.CommonUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/2/12 0012.
 */

public class DetailDesHolder extends BaseHolder<HomeDetailBean> {
    @BindView(R.id.tv_detail_desc)
    TextView tvDetailDesc;
    @BindView(R.id.tv_detail_author)
    TextView tvDetailAuthor;

    public DetailDesHolder(ViewGroup parent) {
        super(parent);
    }

    @Override
    protected View initView(ViewGroup parent) {
        View desView = CommonUtil.getInflate().inflate(R.layout.layout_detail_des, parent);
        ButterKnife.bind(this,desView);
        return desView;
    }

    @Override
    public void bindData(HomeDetailBean data) {
        //设置数据  ，作者    简介
        tvDetailDesc.setText(data.getDes());
        tvDetailAuthor.setText(data.getAuthor());


    }
}
