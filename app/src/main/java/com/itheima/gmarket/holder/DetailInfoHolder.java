package com.itheima.gmarket.holder;

import android.graphics.Color;
import android.text.format.Formatter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.itheima.gmarket.R;
import com.itheima.gmarket.base.BaseHolder;
import com.itheima.gmarket.model.net.HomeDetailBean;
import com.itheima.gmarket.utils.CommonUtil;
import com.itheima.gmarket.utils.ServerAPI;

import org.xutils.x;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/2/11 0011.
 */

public class DetailInfoHolder extends BaseHolder<HomeDetailBean> {
    @BindView(R.id.appicon)
    ImageView appicon;
    @BindView(R.id.appname)
    TextView appname;
    @BindView(R.id.appstar)
    RatingBar appstar;
    @BindView(R.id.appdownload)
    TextView appdownload;
    @BindView(R.id.appversion)
    TextView appversion;
    @BindView(R.id.apptime)
    TextView apptime;
    @BindView(R.id.appsize)
    TextView appsize;

    public DetailInfoHolder(ViewGroup parent) {
        super(parent);
    }

    @Override
    protected View initView(ViewGroup parent) {
        View infoView = CommonUtil.getInflate().inflate(R.layout.layout_detail_appinfo, null);
        ButterKnife.bind(this,infoView);
        return infoView;
    }
    @Override
    public void bindData(HomeDetailBean data) {
//        tv.setText
        appname.setText(data.getName());
        appdownload.setText(data.getDownloadNum());
        apptime.setText(data.getDate());
        appversion.setText(data.getVersion());
        appsize.setText(Formatter.formatFileSize(CommonUtil.getContext(),data.getSize()));
        appstar.setRating((float) data.getStars());

        //设置图片
        x.image().bind(appicon, ServerAPI.IMAGE_BASE_URL+data.getIconUrl());
    }
}
