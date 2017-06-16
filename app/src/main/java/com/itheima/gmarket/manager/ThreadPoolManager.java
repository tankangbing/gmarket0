package com.itheima.gmarket.manager;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
/**
 * Created by Administrator on 2016/12/15 0015.
 * 线程池管理工具类  ，设计模式 ： 单例模式 ，代理模式
 */
public class ThreadPoolManager {
    //取得单例的代理对象
    private static  ThreadPoolProxy threadPoolProxy;
    public static   ThreadPoolProxy  getThreadPoolProxy(){
        synchronized (ThreadPoolManager.class) {
            //同步代码块
            if (threadPoolProxy == null) {
                threadPoolProxy = new ThreadPoolProxy(3, 5, 30);
            }
        }
        return  threadPoolProxy;
    }
    //线程池执行者代理类
    public static class ThreadPoolProxy{
            // 线程池执行者，具体由该对象来管理线程，执行任务
            private ThreadPoolExecutor threadPoolExecutor;
            private int corePoolSize; //核心线程数
            private int maximumPoolSize; //最大线程数
            private long keepAliveTime;//保持存活时间，当线程数大于corePoolSize的空闲线程能保持的最大时间
            public ThreadPoolProxy(int corePoolSize,int maximumPoolSize,long keepAliveTime){
                this.corePoolSize = corePoolSize;
                this.maximumPoolSize = maximumPoolSize;
                this.keepAliveTime = keepAliveTime;
            }
            // 执行任务
            public void execute(Runnable r) {
                if (threadPoolExecutor == null || threadPoolExecutor.isShutdown()) {
                    threadPoolExecutor = new ThreadPoolExecutor(corePoolSize,
                            maximumPoolSize,
                            keepAliveTime,
                            TimeUnit.SECONDS, // 时间单元
                            new LinkedBlockingDeque<Runnable>(), // 保存任务的阻塞队列
                            Executors.defaultThreadFactory() // 创建线程的工厂
                            ,new ThreadPoolExecutor.AbortPolicy()//拒绝策略 ，直接抛异常
                    );
                }
                threadPoolExecutor.execute(r);
            }

        }

    }

