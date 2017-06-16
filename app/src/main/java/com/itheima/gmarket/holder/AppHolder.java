package com.itheima.gmarket.holder;

import android.graphics.Color;
import android.text.format.Formatter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.itheima.gmarket.R;
import com.itheima.gmarket.base.BaseHolder;
import com.itheima.gmarket.model.net.AppInfo;
import com.itheima.gmarket.utils.CommonUtil;
import com.itheima.gmarket.utils.ServerAPI;
import com.itheima.gmarket.utils.ToastUtil;

import org.xutils.x;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;

//首页的ViewHolder
public class AppHolder extends BaseHolder<AppInfo> {

    @BindView(R.id.iv_icon)
     ImageView ivIcon;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.rating_bar)
    RatingBar ratingBar;
    @BindView(R.id.tv_app_size)
    TextView tvAppSize;
    @BindView(R.id.tv_desc)
    TextView tvDesc;

    //注入字符串资源
    @BindString(R.string.app_name)
    String appName;




    public AppHolder(ViewGroup parent) {
        super(parent);
    }

    /**
     * 一、 ItemView初始化
     * - 把列表条目的xml布局实例化为view对象，返回的是列表条目xml布局的根节点视图对象
     * - 通过ViewHolder绑定要操作的列表条目布局的子控件
     */
    @Override
    protected View initView(ViewGroup parent) {
        View itemView = CommonUtil.getInflate().inflate(R.layout.item_app, parent, false);
        // object :表示当前操作的对象 ,view: 要自动注入的视图对象的根节点
        ButterKnife.bind(this,itemView);

        return itemView;
    }

    // 二、绑定数据
    @Override
    public void bindData(AppInfo data) {
        tvName.setText(data.getName());
        tvName.setTextColor(Color.RED);
        tvDesc.setText(data.getDes());
        tvAppSize.setText(Formatter.formatFileSize(CommonUtil.getContext(),data.getSize()));
        //评分
        ratingBar.setRating(data.getStars());

        //设置图片  app/com.itheima.www/icon.jpg
        String iconUrl=data.getIconUrl();
        x.image().bind(ivIcon, ServerAPI.IMAGE_BASE_URL+iconUrl);

    }
}