package com.itheima.gmarket.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

import static android.R.attr.name;

/**
 * Created by Administrator on 2017/2/9 0009.
 */

public class RatioImageView extends ImageView {
    private  static  final  String NAMESPACE="http://schemas.android.com/apk/res-auto";
    private float ratio;
    public RatioImageView(Context context) {
        super(context);
    }
     //xml中会调用该构造方法
    public RatioImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        // 取得属性值， NAMESPACE：命名空间  ratio： 属性名   defvalues：缺省值
        ratio=attrs.getAttributeFloatValue(NAMESPACE,"ratio",1.0f);
    }

    public RatioImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        ratio=attrs.getAttributeFloatValue(NAMESPACE,"ratio",1.0f);
    }
    /**测量方法
     * 测量规格：  32位   前2位  模式    后30位  ，具体的值
     *   MeasureSpec.AT_MOST ：最多多少像素
     *  easureSpec.UNSPECIFIED  ：未确定 比如 ListView  ，当适配器装配好条目数据才好确定
     * MeasureSpec.EXACTLY :精确的值，具体的像素
     */

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        //取得图片的宽度
        int width = MeasureSpec.getSize(widthMeasureSpec);
        //依据比例取得高度,该高度就是图片要重新设置的高度
        int height= (int) (width/ratio);
//        MeasureSpec.getMode();//取得模式  ：
        heightMeasureSpec= MeasureSpec.makeMeasureSpec(height,MeasureSpec.EXACTLY);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
