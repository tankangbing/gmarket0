package com.itheima.gmarket.utils;

import android.content.Context;
import android.widget.Toast;

import com.itheima.gmarket.GMApplication;

/**
 * Created by Administrator on 2017/2/4 0004.
 * Toast工具类
 */

public class ToastUtil {
    private static Toast toast;
    public static void  show( String text){
        if(toast==null){
            toast=Toast.makeText(GMApplication.getContext(),text,Toast.LENGTH_SHORT);
        }
        toast.setText(text);
        toast.show();
    }
    public static void  show( int textRes){
        if(toast==null){
            toast=Toast.makeText(GMApplication.getContext(),textRes,Toast.LENGTH_SHORT);
        }
        toast.setText(textRes);
        toast.show();
    }
}
