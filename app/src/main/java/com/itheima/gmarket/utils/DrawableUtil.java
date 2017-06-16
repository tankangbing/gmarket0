package com.itheima.gmarket.utils;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;

/**
 * Created by Administrator on 2017/2/9 0009.
 * Drawable的工具类
 */
public class DrawableUtil {
    /**
     * 取得Shape 的Drawable
     * @param rgb ：背景颜色
     * @param radius ： 边角的半径
     * @return
     */
    public static Drawable getShape(int rgb, int radius) {
        //渐变的Drawable
        GradientDrawable gradientDrawable=new GradientDrawable();
        //设置形状  矩形
        gradientDrawable.setShape(GradientDrawable.RECTANGLE);
        //设置颜色
        gradientDrawable.setColor(rgb);
        //设置边角半径
        gradientDrawable.setCornerRadius(radius);

        return  gradientDrawable;
    }

    /**
     * 通过代码创建选择器
     * @param normalDrawable ：默认的颜色
     * @param pressedDrawable ：按下去的颜色
     * @return
     */
    public static Drawable getStateListDrawable(Drawable normalDrawable, Drawable pressedDrawable) {
        //状态列表的Drawable  ：图片选择器
        StateListDrawable stateListDrawable=new StateListDrawable();
        stateListDrawable.addState(new int[]{android.R.attr.state_pressed,android.R.attr.state_enabled},pressedDrawable);
        stateListDrawable.addState(new int[]{android.R.attr.state_checked},pressedDrawable);
        stateListDrawable.addState(new int[]{},normalDrawable);
        return  stateListDrawable;
    }
}
