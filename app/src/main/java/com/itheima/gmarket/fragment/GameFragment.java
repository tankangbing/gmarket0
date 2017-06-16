package com.itheima.gmarket.fragment;

import android.view.View;
import android.widget.TextView;

import com.itheima.gmarket.base.BaseFragment;
import com.itheima.gmarket.model.net.ResultState;
import com.itheima.gmarket.views.ContentPage;

/**
 * Created by Administrator on 2017/2/4 0004.
 */

public class GameFragment extends BaseFragment {
    //1. 创建本片段的成功界面方法
    @Override
    protected View onSubCreateSuccessView() {
        TextView tv=new TextView(context);
        tv.setText("游戏成功界面");
        return tv;
    }
    //加载本片段数据的方法
    @Override
    protected ResultState onSubLoad() {
        return ResultState.LOAD_EMPTY;
    }
}
