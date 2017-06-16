package com.itheima.gmarket.views;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;

import com.itheima.gmarket.R;
import com.itheima.gmarket.manager.ThreadPoolManager;
import com.itheima.gmarket.model.net.ResultState;
import com.itheima.gmarket.utils.CommonUtil;
import com.itheima.gmarket.utils.LogUtil;

/**
 * Created by Administrator on 2017/2/5 0005.
 * 片段的公共内容界面
 */

public abstract class ContentPage extends FrameLayout {
    //状态常量
    private static  final  int STATE_DEFAULT=0;//缺省状态
    private static  final  int STATE_LOADING=1;//正在加载数据状态
    public static  final  int STATE_ERROR=2;//访问网络出错状态
    public static  final  int STATE_EMPTY=3;//访问网络成功，但是数据为空的状态
    public static  final  int STATE_SUCCESS=4;//访问网络成功，且有数据状态

    //状态变量
    private int state=STATE_SUCCESS;//默认为缺省状态

    //包含四个子页面
    private View emptyView;//空界面
    private View errorView;//访问网络出错界面
    private View loadingView;//正在加载界面
    private View successView;//成功界面
    public ContentPage(Context context) {
        super(context);
        initPager();
    }
   //1. 初始化界面
    private void initPager() {
        //1.1 添加空界面
        if(emptyView==null){
            emptyView=onCreateEmptyView();
            this.addView(emptyView);//把空界面添加到片段的内容界面中
        }

        //1.2 添加出错界面
        if(errorView==null){
            errorView=onCreateErrorView();
            this.addView(errorView);
        }

        //1.3 添加正在加载界面
        if(loadingView==null){
            loadingView=onCreateLoadingView();
            this.addView(loadingView);
        }
        //1.4 添加成功界面 ， 由于每个模块的成功界面都相对复杂，建议再显示界面时创建成功界面
        showPager();

        //1.5 加载或者刷新数据方法的调用
        loadDataOrRefresh(); // ptrLayout


    }
    //2. 显示界面 ，依据状态来决定显示哪个子页面
    private void showPager() {
//        LogUtil.d("showPager"); //b
        //2.1 当处于缺省状态或者正在加载数据状态，都显示转圈圈界面（正在加载界面）
        if(loadingView!=null){
            if(state==STATE_DEFAULT||state==STATE_LOADING){
                loadingView.setVisibility(View.VISIBLE);
            }else{
                loadingView.setVisibility(View.INVISIBLE);
            }
        }
        //2.2  显示出错界面
        if(errorView!=null){
            errorView.setVisibility(state==STATE_ERROR?View.VISIBLE:View.INVISIBLE);
        }

        //2.3 显示为空界面
        if(emptyView!=null){
            emptyView.setVisibility(state==STATE_EMPTY?View.VISIBLE:View.INVISIBLE);
        }

        /** 2.4 显示成功界面
         *  1. 初始化成功界面
         *  2. 显示或者隐藏成功界面
         */
        //2.4.1 初始化成功界面
        if(successView==null){
            successView=onCreateSuccessView();
            if(successView!=null){
                this.addView(successView);//把成功界面添加到容器中
            }
        }
       //2.4.2 显示或者隐藏成功界面
        if(successView!=null){
            successView.setVisibility(state==STATE_SUCCESS?View.VISIBLE:View.INVISIBLE);
        }
    }

    //3. 加载数据或者刷新数据的方法
    public void loadDataOrRefresh(){
        //3.1 重置状态  ，把原理的状态重置为缺省状态
        // 3.2 当处于正在加载状态时，不能再重新加载数据 ，排除正在加载数据状态
        //state==state_loading
        if(state==STATE_DEFAULT||state==STATE_SUCCESS||state==STATE_ERROR||state==STATE_EMPTY){
            //重置四种状态
            state=STATE_DEFAULT;
        }

        //当状态处于缺省状态时，访问网络，加载数据
        if(state==STATE_DEFAULT){
            //访问网络，加载数据
            //通过线程池管理线程，访问网络
            ThreadPoolManager.getThreadPoolProxy().execute(new LoadTask());

        }

    }
    //加载数据的方法，由于每个模块访问网络的url都有差异，且取得的数据也不同，所以不能在公共模块实现，由实现者去实现
    //访问网络结果的状态： 出错、为空、成功，枚举几个状态的常量
    public abstract ResultState onLoad() ;


    //初始化成功界面 ,由于每个功能模块的成功界面都有差异，所以不能在公共模块界面ContentPager中实现，应该抽象出去，由实现者来实现
    public abstract  View onCreateSuccessView();

    //初始化正在加载界面
    private View onCreateLoadingView() {
        return CommonUtil.inflate(R.layout.layout_loading);
    }

    //初始化出错界面
    private View onCreateErrorView() {
        return CommonUtil.inflate(R.layout.layout_error);
    }

    //初始化空界面
    private View onCreateEmptyView() {
        return CommonUtil.inflate(R.layout.layout_empty);
    }


    //访问网络的任务
    private class  LoadTask implements  Runnable{
        @Override
        public void run() {
            LogUtil.d("ThreadPool","run+threadPool");  // a
            //todo  访问网络，加载数据的操作 ，返回访问网络的结果状态
            final ResultState resultState =onLoad();


            //依据访问网络的结果状态更新界面
            //与UI相关的操作要在UI线程执行
            CommonUtil.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if(resultState!=null){
                        state= resultState.getState();
                    }
                    showPager();
                }
            });
        }
    }



}
