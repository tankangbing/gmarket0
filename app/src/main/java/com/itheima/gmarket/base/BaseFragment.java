package com.itheima.gmarket.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.TextView;

import com.itheima.gmarket.model.net.ResultState;
import com.itheima.gmarket.views.ContentPage;

import static com.itheima.gmarket.R.id.textView;

/**
 * Created by Administrator on 2017/2/4 0004.
 * 所以片段的基类，继承V4库中的fragment
 */

public abstract  class BaseFragment extends Fragment {  // HomeFragment  AppFragment
    protected Context context; //公共的属性
    protected ContentPage contentPage; //片段的通用界面

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=getActivity();
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        //创建一个公共的片段内容视图对象
        //加载数据的方法
        if(contentPage==null) {
            //todo
            contentPage = new ContentPage(context) {
                //加载数据的方法
                @Override
                public ResultState onLoad() {
                    ResultState resultState = onSubLoad();
                    return resultState;
                }

                @Override
                public View onCreateSuccessView() {
                    //todo
                    View view = onSubCreateSuccessView();
                    return view;
                }
            };

        }
      //在低版本v4库中的FragmentManager，Fragment的界面视图会有一个父控件引用它，
        // 当重用Fragment的界面视图时，需先与父控件解除关系，再返回
        else{
            ViewParent parent = contentPage.getParent();
            if(parent!=null&&parent instanceof  ViewGroup){
                ViewGroup viewgroup= (ViewGroup) parent;
                viewgroup.removeView(contentPage);
            }


        }
        return contentPage;
    }


    //加载数据的方法，由于每个功能模块访问网络的url都有差异，且取得的数据也不同，所以不能在基类BaseFragment中实现，应该抽象出去，由子类来实现
    protected abstract ResultState onSubLoad() ;


    //创建成功界面方法 ,由于每个功能模块的成功界面都有差异，所以不能在基类BaseFragment中实现，应该抽象出去，由子类来实现
    protected abstract  View onSubCreateSuccessView();
}
