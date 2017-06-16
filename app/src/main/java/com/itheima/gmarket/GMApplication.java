package com.itheima.gmarket;

import android.app.Application;
import android.content.Context;
import android.os.Handler;

import org.xutils.x;

/**
 * Created by Administrator on 2017/2/4 0004.
 * 拓展一个Application，用于维护全局的变量，初始化项目的相关操作
 */

public class GMApplication extends Application {
    private static Context context;//全局上下文
    private static Handler handler;
    private static  Thread mainThread;//主线程
    private  static  int mainThreadId;//主线程id
    private String nick;//昵称  ，维护一个全局的昵称变量，非静态

    @Override
    public void onCreate() {
        context=getApplicationContext();
        handler=new Handler();
        mainThread=Thread.currentThread();
        mainThreadId=android.os.Process.myTid();//取得进程的当前线程
        //初始化xutils3框架
        x.Ext.init(this);
    }

    public static Context getContext() {
        return context;
    }

    public static Handler getHandler() {
        return handler;
    }

    public static Thread getMainThread() {
        return mainThread;
    }

    public static int getMainThreadId() {
        return mainThreadId;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }
}
