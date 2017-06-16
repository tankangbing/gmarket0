package com.itheima.gmarket.holder;

import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.itheima.gmarket.R;
import com.itheima.gmarket.activity.ScaleImageActivity;
import com.itheima.gmarket.base.BaseHolder;
import com.itheima.gmarket.model.net.HomeDetailBean;
import com.itheima.gmarket.utils.CommonUtil;
import com.itheima.gmarket.utils.ServerAPI;

import org.xutils.x;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/2/12 0012.
 */

public class DetailScreenHolder extends BaseHolder<HomeDetailBean> {
    @BindView(R.id.llt_screen)
    LinearLayout lltScreen;

    public DetailScreenHolder(ViewGroup parent) {
        super(parent);
    }
    @Override
    protected View initView(ViewGroup parent) {
        View screenView = CommonUtil.getInflate().inflate(R.layout.layout_detail_screen, parent);
        ButterKnife.bind(this,screenView);
        return screenView;
    }

    @Override
    public void bindData(HomeDetailBean data) {
        lltScreen.removeAllViews();
        //取得截屏数据
        final ArrayList<String> screenList = (ArrayList<String>) data.getScreen();
        for ( int i = 0; i < screenList.size(); i++) {
            ImageView iv=new ImageView(context);
            //设置图片的宽和高
           LinearLayout.LayoutParams ivLayoutParams=new LinearLayout.LayoutParams(CommonUtil.dp2px(90),CommonUtil.dp2px(150));
            ivLayoutParams.leftMargin=CommonUtil.dp2px(8);
            iv.setLayoutParams(ivLayoutParams);
            x.image().bind(iv, ServerAPI.IMAGE_BASE_URL+screenList.get(i));

            //对图片控件设置点击监听
            final  int currentPosition=i;
            iv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //1. 跳转到目标界面 ScaleImageActivity
                    Intent intent=new Intent();
                    intent.setClass(CommonUtil.getContext(), ScaleImageActivity.class);
                    //通过Intent传递对象需要序列化对象
                    intent.putExtra("screenList",screenList);
                    //把位置传递过去
                    intent.putExtra("position",currentPosition);
                    //context
                    //开启一个新的任务栈的标记
//                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                    CommonUtil.getContext().startActivity(intent);
                    //context:就是MainActivity对象
                    context.startActivity(intent);
                }
            });

            //添加到screen图片的直接父控件
            lltScreen.addView(iv);
        }

    }
}
