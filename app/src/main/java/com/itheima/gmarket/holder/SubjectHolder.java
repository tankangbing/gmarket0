package com.itheima.gmarket.holder;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.itheima.gmarket.R;
import com.itheima.gmarket.base.BaseHolder;
import com.itheima.gmarket.model.net.SubjectInfo;
import com.itheima.gmarket.utils.CommonUtil;
import com.itheima.gmarket.utils.ServerAPI;

import org.xutils.x;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/2/9 0009.
 */

public class SubjectHolder extends BaseHolder<SubjectInfo> {
    @BindView(R.id.iv_picture)
    ImageView ivPicture;
    @BindView(R.id.tv_des)
    TextView tvDes;

    public SubjectHolder(ViewGroup parent) {
        super(parent);
    }

    //初始化专题界面
    @Override
    protected View initView(ViewGroup parent) {
        View view = CommonUtil.getInflate().inflate(R.layout.item_subject, parent, false);
        ButterKnife.bind(this,view);
        return view;
    }

    //绑定数据
    @Override
    public void bindData(SubjectInfo data) {
        x.image().bind(ivPicture, ServerAPI.IMAGE_BASE_URL+data.url);
        tvDes.setText(data.des);

    }
}
