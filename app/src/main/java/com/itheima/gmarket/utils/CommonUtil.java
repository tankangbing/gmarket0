package com.itheima.gmarket.utils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.itheima.gmarket.GMApplication;

/**
 * Created by Administrator on 2017/2/4 0004.
 * 常用工具类，与UI相关的工具类
 */

public class CommonUtil {
    // 把GMApplication的方法通过CommonUtil代理的形式调用
    //
    public static Context getContext(){
        return GMApplication.getContext();
    }
    public static Handler getHandler(){
        return  GMApplication.getHandler();
    }
    public static  int getMainThreadId(){
        return  GMApplication.getMainThreadId();
    }
    public  static  Thread getMainThread(){
        return  GMApplication.getMainThread();
    }

    //取得资源 Resource
    public static Resources getResuouces(){
        return  getContext().getResources();
    }
    //取得字符串资源
    public static  String getString(int stringResId){
       return getResuouces().getString(stringResId);
    }

    //通过资源id取得字符串数组
    public  static  String[] getStringArray(int stringArrayId){
        return  getResuouces().getStringArray(stringArrayId);
    }

    //取得颜色
    public static  int getColor(int colorResid){
        return  getResuouces().getColor(colorResid);
    }
    //取得Drawable
    public  static Drawable getDrawable(int drawableResId){
        return  getResuouces().getDrawable(drawableResId);
    }

    //把xml布局文件转化为View对象,返回的是xml布局的根节点视图对象
    public  static View inflate(int layoutResId){
        return LayoutInflater.from(getContext()).inflate(layoutResId,null);
    }
    //取得LayoutInflate对象
    public  static  LayoutInflater getInflate(){
        return  LayoutInflater.from(getContext());
    }

    //把dp转化为px
    public static  int dp2px(int dp){
        // dp转px: 依据当地的手机屏幕密度与标准密度的比例来转化
        // 标准密度 ： 160 dpi : mdpi
        // 高清密度  :240 dpi  :

        //density: 1.5  2   3
        float density = getResuouces().getDisplayMetrics().density;
        return (int) (dp*density+0.5f);
    }

    //把px转化为dp
    public static  int px2dp(int px){
        // dp转px: 依据当地的手机屏幕密度与标准密度的比例来转化
        // 标准密度 ： 160 dpi : mdpi
        // 高清密度  :240 dpi  :
        //density: 1.5  2   3
        float density = getResuouces().getDisplayMetrics().density;
        return (int) (px/density+0.5f);
    }

    //判断当前线程是否为主线程
    public static  boolean isMainThread(){
        return  getMainThreadId()==android.os.Process.myTid();
    }

    /**
     * 确保任务在主线程 -UI线程运行 ，比如涉及到UI的操作
     *  1. 当前任务就是在主线程
     *  2. 当前任务不是在主线程
     */
    public  static  void runOnUiThread(Runnable r){
        if(isMainThread()){
            //当前任务在主线程
            r.run();//直接执行
        }else {
            //通过Handler 把任务发到主线程的语句队列中，主线程再通过轮询器 取得任务，再执行
            getHandler().post(r);

        }

    }




}
