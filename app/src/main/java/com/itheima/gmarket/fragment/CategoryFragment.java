package com.itheima.gmarket.fragment;

import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.itheima.gmarket.adapter.CategoryAdpater;
import com.itheima.gmarket.base.BaseFragment;
import com.itheima.gmarket.model.net.CategoryInfo;
import com.itheima.gmarket.model.net.ResultState;
import com.itheima.gmarket.protocol.CategoryProtocol;
import com.itheima.gmarket.utils.CommonUtil;
import com.itheima.gmarket.views.ContentPage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/2/4 0004.
 */

public class CategoryFragment extends BaseFragment {
    private List<CategoryInfo> datas=new ArrayList<>();
    private CategoryAdpater adpater;
    //1. 创建本片段的成功界面方法
    @Override
    protected View onSubCreateSuccessView() {
        ListView listView=new ListView(context);
        //没有滚动条
         listView.setVerticalScrollBarEnabled(false);
        listView.setDivider(null);//没有条目分割线

        //设置适配器
        adpater=new CategoryAdpater();
        listView.setAdapter(adpater);

        //设置数据
        adpater.setList(datas);

        return listView;
    }
    //加载本片段数据的方法
    @Override
    protected ResultState onSubLoad() {
        CategoryProtocol protocol=new CategoryProtocol();
        datas.clear();

         datas.addAll(protocol.getData(0));

        //设置数据
        if(datas!=null){
            CommonUtil.runOnUiThread(new Runnable() {
                @Override
                public void run() {
//                    adpater.setList(datas);
                    adpater.notifyDataSetChanged();
                }
            });

        }

        //更新状态
        if(datas!=null){
            if(datas.size()==0){
                return  ResultState.LOAD_EMPTY;
            }else {
                return  ResultState.LOAD_SUCCESS;
            }
        }
        return ResultState.LOAD_ERROR;
    }
}
