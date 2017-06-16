package com.itheima.gmarket.holder;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.itheima.gmarket.R;
import com.itheima.gmarket.base.BaseHolder;
import com.itheima.gmarket.model.net.HomeDetailBean;
import com.itheima.gmarket.utils.CommonUtil;
import com.itheima.gmarket.utils.ServerAPI;

import org.xutils.x;

import java.util.List;

import static android.R.attr.id;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.L;

/**
 * Created by Administrator on 2017/2/12 0012.
 */

public class DetailSafeHolder extends BaseHolder<HomeDetailBean> {
    private LinearLayout rootLlt;//安全界面的根节点
    private  LinearLayout footLlt;//安全界面底部详情的根节点
    private ImageView arrowIv;//箭头视图

    private ImageView[] safeIconIvs;//安全图标的数组
    private LinearLayout[] detailItemLlts;//每个详情条目的根节点
    private ImageView[] detailIcons;//详情条目图标的数组
    private TextView[] detailContents;//详情条目具体内容的数组
    private boolean isOpen;//展开与折叠的状态变量
    private LinearLayout.LayoutParams footLayoutParams;

    public DetailSafeHolder(ViewGroup parent) {
        super(parent);
    }

    //1. 初始化视图
    @Override
    protected View initView(ViewGroup parent) {
        isOpen = false;//默认是折叠
       View safeView= CommonUtil.getInflate().inflate(R.layout.layout_detail_safe,parent);
        //1.1 初始化视图
        rootLlt= (LinearLayout) safeView.findViewById(R.id.ll_root);
        footLlt= (LinearLayout) safeView.findViewById(R.id.ll_footer);
        arrowIv= (ImageView) safeView.findViewById(R.id.arrow);

        //1.2 初始化安全图标控件
        safeIconIvs=new ImageView[4];
        safeIconIvs[0]= (ImageView) safeView.findViewById(R.id.imagesafe1);
        safeIconIvs[1]= (ImageView) safeView.findViewById(R.id.imagesafe2);
        safeIconIvs[2]= (ImageView) safeView.findViewById(R.id.imagesafe3);
        safeIconIvs[3]= (ImageView) safeView.findViewById(R.id.imagesafe4);

        //1.3 初始化每个详情条目的根节点
        detailItemLlts=new LinearLayout[4];
        detailItemLlts[0]= (LinearLayout) safeView.findViewById(R.id.ll1);
        detailItemLlts[1]= (LinearLayout) safeView.findViewById(R.id.ll2);
        detailItemLlts[2]= (LinearLayout) safeView.findViewById(R.id.ll3);
        detailItemLlts[3]= (LinearLayout) safeView.findViewById(R.id.ll4);

        //1.4 详情条目图标的数组
        detailIcons=new ImageView[4];
        detailIcons[0]= (ImageView) safeView.findViewById(R.id.ll1_image1);
        detailIcons[1]= (ImageView) safeView.findViewById(R.id.ll2_image2);
        detailIcons[2]= (ImageView) safeView.findViewById(R.id.ll3_image3);
        detailIcons[3]= (ImageView) safeView.findViewById(R.id.ll4_image4);

        //1.5 详情条目具体内容的数组
        detailContents=new TextView[4];
        detailContents[0]= (TextView) safeView.findViewById(R.id.ll1_text1);
        detailContents[1]= (TextView) safeView.findViewById(R.id.ll2_text2);
        detailContents[2]= (TextView) safeView.findViewById(R.id.ll3_text3);
        detailContents[3]= (TextView) safeView.findViewById(R.id.ll4_text4);

        //1.6 默认底部详情界面是隐藏的 ,通过底部详情布局的根节点设置布局参数 height=0
        footLayoutParams = (LinearLayout.LayoutParams) footLlt.getLayoutParams();
        footLayoutParams.height=0;
        footLlt.setLayoutParams(footLayoutParams);

        //1.7 对整个安全模块界面设置点击监听
        rootLlt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //展开或者折叠安全详情界面操作
                expandOrCollapse();
            }
        });


        return safeView;
    }


    //展开或者折叠方法,通过属性动画来实现 ，ValueAnimator
    private void expandOrCollapse() {
       //依据当前的状态来决定展开与折叠
        footLlt.clearAnimation();//清除动画
        ValueAnimator valueAnimator=null;
        //取得footLlt的测量高度
        footLlt.measure(0,0);
        int scaleFootHeight=footLlt.getMeasuredHeight();//取得测量高度
        if(isOpen){
            //折叠
            valueAnimator=ValueAnimator.ofInt(scaleFootHeight,0);
        }else {
            //展开
            valueAnimator=ValueAnimator.ofInt(0,scaleFootHeight);
        }
        //对动画设置状态值更新的监听
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                //取得当前的状态值
                Integer currentHeight = (Integer) animation.getAnimatedValue();
                footLayoutParams.height=currentHeight;
                footLlt.setLayoutParams(footLayoutParams);
            }
        });

        //对动画作监听
        valueAnimator.addListener(new Animator.AnimatorListener() {
            //动画开始
            @Override
            public void onAnimationStart(Animator animation) {
            }

            //动画介绍
            @Override
            public void onAnimationEnd(Animator animation) {

                if(isOpen){
                    //展开状态，箭头向上
                    arrowIv.setBackgroundResource(R.drawable.arrow_up);
                }else{
                    arrowIv.setBackgroundResource(R.drawable.arrow_down);
                }
            }
            //取消
            @Override
            public void onAnimationCancel(Animator animation) {
            }
           //重复
            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

        isOpen=!isOpen;
        valueAnimator.setDuration(1000);//动画持续时间 1秒
        valueAnimator.start();//开始动画

    }

    @Override
    public void bindData(HomeDetailBean data) {
        //取得安全模块的数据
        List<HomeDetailBean.SafeBean> safeBean = data.getSafe();
        //最多只能展示前四个安全图片、安全详情信息
        for (int i = 0; i < 4; i++) {
            // size=2
            if(i<safeBean.size()){
                //显示安全图片和详情
                safeIconIvs[i].setVisibility(View.VISIBLE);
                detailItemLlts[i].setVisibility(View.VISIBLE);
                //装配数据
                x.image().bind(safeIconIvs[i], ServerAPI.IMAGE_BASE_URL+safeBean.get(i).getSafeUrl());
                x.image().bind(detailIcons[i],ServerAPI.IMAGE_BASE_URL+safeBean.get(i).getSafeDesUrl());
                detailContents[i].setText(safeBean.get(i).getSafeDes());
            }else{
                //隐藏安全图标和详情
                safeIconIvs[i].setVisibility(View.GONE);
                detailItemLlts[i].setVisibility(View.GONE);
            }

        }


    }
}
