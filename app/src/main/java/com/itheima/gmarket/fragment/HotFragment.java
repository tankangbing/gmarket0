package com.itheima.gmarket.fragment;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.DrawableUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

import com.itheima.gmarket.base.BaseFragment;
import com.itheima.gmarket.model.net.ResultState;
import com.itheima.gmarket.protocol.HotProtocol;
import com.itheima.gmarket.utils.CommonUtil;
import com.itheima.gmarket.utils.DrawableUtil;
import com.itheima.gmarket.views.ContentPage;
import com.itheima.gmarket.views.FlowLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Administrator on 2017/2/4 0004.
 */

public class HotFragment extends BaseFragment {
    private List<String> datas=new ArrayList<>();
    private FlowLayout flowLayout;

    //1. 创建本片段的成功界面方法
    @Override
    protected View onSubCreateSuccessView() {
        ScrollView scrollView=new ScrollView(context);
        int padding= CommonUtil.dp2px(10);
        //设置内边距
        scrollView.setPadding(padding,padding,padding,padding);
        //没有滚动条效果
        scrollView.setVerticalScrollBarEnabled(false);
       //构建一个流布局对象
        flowLayout = new FlowLayout(context);
        //初始化flowLayout的水平和垂直间距
        flowLayout.setHorizontalSpacing(CommonUtil.dp2px(6));
        flowLayout.setVerticalSpacing(padding);
        
        //向FlowLayout添加子控件
//        addItemView(flowLayout);
        scrollView.addView(flowLayout);

        return scrollView;
    }
     //向流布局容器中添加子控件
    private void addItemView() {
         Random random=new Random();
        for (int i = 0; i < datas.size(); i++) {
            TextView tv=new TextView(context);
            tv.setText(datas.get(i));
            int padding=CommonUtil.dp2px(10);
            tv.setPadding(padding,padding,padding,padding);
            tv.setTextColor(Color.WHITE);
            tv.setGravity(Gravity.CENTER_HORIZONTAL|Gravity.CENTER_VERTICAL);

              //设置随机的背景色
            int red=10+random.nextInt(255-30);  // 范围10-235 ,避免极致
            int green=10+random.nextInt(255-30);  // 范围10-235 ,避免极致
            int blue=10+random.nextInt(255-30);  // 范围10-235 ,避免极致
            int argb= Color.argb(255,red,green,blue);
//            tv.setBackgroundColor(rgb);

            //边角的半径
            int radius=CommonUtil.dp2px(6);
            // 通过代码的形式创建Shape 圆角 Drawable
//            Drawable shapeDrawable= DrawableUtil.getShape(argb,radius);
//            tv.setBackgroundDrawable(shapeDrawable);

            //通过代码实现选择器效果  DrawableStateList :状态列表
            //normalDrawable ：默认的图片
            Drawable normalDrawable= DrawableUtil.getShape(argb,radius);
            //pressedDrawable：按下去的图片
            //颜色 ：默认的颜色 ： # aarrggbb      按下去的颜色  # FFrrggbb-0x33000000
            Drawable pressedDrawable= DrawableUtil.getShape(argb-0x55000000,radius);

            Drawable selectorDrawable=DrawableUtil.getStateListDrawable(normalDrawable,pressedDrawable);
            tv.setClickable(true);
            tv.setBackgroundDrawable(selectorDrawable);



            flowLayout.addView(tv);

        }

    }

    //加载本片段数据的方法
    @Override
    protected ResultState onSubLoad() {
        HotProtocol protocol=new HotProtocol();
        //取得数据
        datas = protocol.getData(0);
        //处理数据
        if(datas!=null){
            CommonUtil.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    addItemView();
                }
            });
        }

        //检测状态
        if(datas!=null){
            if(datas.size()==0){
                return  ResultState.LOAD_EMPTY;
            }else {
                return  ResultState.LOAD_SUCCESS;
            }
        }
        return ResultState.LOAD_ERROR;
    }
}
