package com.itheima.gmarket.holder;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.itheima.gmarket.R;
import com.itheima.gmarket.base.BaseHolder;
import com.itheima.gmarket.utils.CommonUtil;
import com.itheima.gmarket.utils.ServerAPI;

import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.layout_margin;
import static android.R.attr.layout_marginTop;
import static android.R.attr.pivotX;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;

/**
 * Created by Administrator on 2017/2/9 0009.
 * 轮播图的Holder
 */
public class HomeHeaderHolder extends BaseHolder<List<String>> {
    private  List<String> pictrues=new ArrayList<>();
    private TextView tv;
    private LinearLayout dotContainer;
    private ViewPager viewPager;
    private RollPager adapter;

    public HomeHeaderHolder(ViewGroup parent) {
        super(parent);
    }
   //1. 初始化轮播图的界面，xml或者java代码
    @Override
    protected View initView(ViewGroup parent) {
        //1.1 构建轮播图的根节点视图
        RelativeLayout rootRlt=new RelativeLayout(context);
        ListView.LayoutParams rootLayoutParams=
                new ListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,CommonUtil.dp2px(134));
        rootRlt.setLayoutParams(rootLayoutParams);

        //1.2 构建一个ViewPager，展示轮播图片
        viewPager = new ViewPager(context);
        RelativeLayout.LayoutParams  vpLayoutParams=new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        //把ViewPager添加到父控件中
        rootRlt.addView(viewPager,vpLayoutParams);

        //1.3 构建圆点的容器
        dotContainer = new LinearLayout(context);
        RelativeLayout.LayoutParams dotContainerLayoutParams=
                new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        //设置属性
        dotContainer.setOrientation(LinearLayout.HORIZONTAL);
        dotContainerLayoutParams.bottomMargin=CommonUtil.dp2px(6);
        //添加布局规则
        dotContainerLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        dotContainerLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        rootRlt.addView(dotContainer,dotContainerLayoutParams);

        //1.4 对ViewPager添加一个页面改变的监听
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                //圆点状态同步改变
                for (int i = 0; i <pictrues.size(); i++) {
                    if(i==position){
                        //对应的圆点是亮点显示
                        dotContainer.getChildAt(position).setBackgroundResource(R.drawable.indicator_selected);
                    }else{
                        dotContainer.getChildAt(i).setBackgroundResource(R.drawable.indicator_normal);
                    }
                    
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        //1.5 开始轮播图片
        CommonUtil.getHandler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //自动指向下一个页面
                viewPager.setCurrentItem((viewPager.getCurrentItem()+1)%pictrues.size());
                CommonUtil.getHandler().postDelayed(this,2000);
            }
        },1000);
        return rootRlt;
    }
  //2 加载数据，绑定数据
    @Override
    public void bindData(List<String> data) {
        if(data!=null&&data.size()>0) {
            //清除轮播图图片地址集合的数据
            pictrues.clear();
            pictrues.addAll(data);
            //2.1 初始化圆点
            initDotView();

            //2.2 ViewPager要装配数据
            if (adapter == null) {
                adapter = new RollPager();
                viewPager.setAdapter(adapter);
            } else {
                adapter.notifyDataSetChanged();
            }
        }
    }
     //初始化圆点
    private void initDotView() {
        //移除圆点容器的视图
        dotContainer.removeAllViews();
        for (int i = 0; i < pictrues.size(); i++) {
            View dotView=new View(context);
            LinearLayout.LayoutParams dotLayoutParams=
                    new LinearLayout.LayoutParams(CommonUtil.dp2px(8),CommonUtil.dp2px(8));
            dotLayoutParams.rightMargin=CommonUtil.dp2px(6);
            //第一个点默认设置为亮点
            if(i==0){
                dotView.setBackgroundResource(R.drawable.indicator_selected);
            }else{
                dotView.setBackgroundResource(R.drawable.indicator_normal);
            }
            //添加到容器中
            dotContainer.addView(dotView,dotLayoutParams);

        }

    }

    //轮播图页面适配器
    private  class  RollPager extends PagerAdapter{

        @Override
        public int getCount() {
            return pictrues.size();
        }
        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView iv=new ImageView(container.getContext());
            //todo
            x.image().bind(iv, ServerAPI.IMAGE_BASE_URL+pictrues.get(position));
            //内部 对 ImageView.set src   backgroud
            iv.setScaleType(ImageView.ScaleType.FIT_XY);
            container.addView(iv);
            return iv;
        }
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }

}
