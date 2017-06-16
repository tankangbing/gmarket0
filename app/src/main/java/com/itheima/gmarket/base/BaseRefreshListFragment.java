package com.itheima.gmarket.base;

import android.view.View;
import android.widget.ListView;

import com.chanven.lib.cptr.PtrClassicFrameLayout;
import com.chanven.lib.cptr.PtrDefaultHandler;
import com.chanven.lib.cptr.PtrFrameLayout;
import com.chanven.lib.cptr.loadmore.OnLoadMoreListener;
import com.itheima.gmarket.R;
import com.itheima.gmarket.adapter.AppAdapter;
import com.itheima.gmarket.model.net.AppInfo;
import com.itheima.gmarket.model.net.ResultState;
import com.itheima.gmarket.protocol.AppProtocol;
import com.itheima.gmarket.utils.CommonUtil;
import com.itheima.gmarket.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import static android.R.id.list;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

/**
 * Created by Administrator on 2017/2/4 0004.
- 作用：所有带刷新的列表片段的基类
 - 继承： BaseFragment ：模板设计模式
 - 特点： 子类片段的成功界面的布局必须要包含：ListView、PtrClassicFrameLayout
 要求装配的数据是List数据
 */

public abstract class BaseRefreshListFragment<T> extends BaseFragment {
    private ListView listView;
   private PtrClassicFrameLayout ptrLayout;//下拉刷新布局
    private List<T> datas=new ArrayList<>();
    private GMBaseListAdapter<T> adapter;
    private BaseProtocol<List<T>> protocol;

    //1. 创建本片段的成功界面方法
    @Override
        protected View onSubCreateSuccessView() {
        /***************与UI界面相关**********/
        //1.1 初始化布局界面
        View view=CommonUtil.inflate(getLayoutResId());
        listView= (ListView) view.findViewById(R.id.test_list_view);
        ptrLayout= (PtrClassicFrameLayout) view.findViewById(R.id.test_list_view_frame);

        //1.2 准备适配器,设置适配器
        adapter =getAdapter();
        listView.setAdapter(adapter);

        //1.3 对ListView设置下拉刷新的监听
        ptrLayout.setPtrHandler(new PtrDefaultHandler() {
            //下拉刷新开始
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                ToastUtil.show("下拉刷新");
//                contentPage
                if(contentPage!=null){
                    //访问网络，加载数据
                    contentPage.loadDataOrRefresh();
                }

            }
        });

        //1.4 对listView设置加载更多方法
        ptrLayout.setLoadMoreEnable(true); //必须要设置该方法，加载更多功能才会开启
        ptrLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void loadMore() {
                ToastUtil.show("加载更多...");
                if(contentPage!=null){
                    //访问网络，加载数据
                    contentPage.loadDataOrRefresh();
                }

            }
        });

        /***************与数据相关**********/

        setupData();


        return view;
    }




    //设置数据的方法
    private void setupData() {
        if(adapter!=null){
            //数据来自网络或者缓存中解析的jsonbean
            adapter.setList(datas);
        }
    }

    //2.加载本片段数据的方法
    @Override
    protected ResultState onSubLoad() {
        //取得数据 todo
        protocol = getProtocol();

        /**依据刷新的类型分别处理数据
         * 1. 下拉刷新 ： 集合的数据应该是最新的数据，且是最多20条，index 索引号应该是 0
         * 2. 加载更多 ： 在当前的数据集合中再添加加载更多获取到的数据 ， 索引号要逐步增加
         */
        if(!ptrLayout.isLoadingMore()){
            //目前是下拉刷新操作
            datas.clear();//清除集合
        }

        //index ：页面 ，默认为0 ，取第0个页面的数据

        // 2.1 分离数据，取得要展示的数据
        //index=0(homelist0) ,20(homelist2),40(homelist3),60(homelist1) ,80(homelist2),100
        //
        List<T> lists = protocol.getData(datas.size());
        if(lists!=null){
            datas.addAll(lists);
            //重新更新listview的数据，展示listveiw界面 ,在UI线程操作
            CommonUtil.runOnUiThread(new Runnable() {
                @Override
                public void run() {
//                    adapter.setList(datas); //60条
                    adapter.notifyDataSetChanged();
                    ptrLayout.refreshComplete();//下拉刷新结束 ，隐藏下拉刷新的界面
                    ptrLayout.loadMoreComplete(true);//加载更多完成，隐藏加载更多界面
                }
            });
        }

        //更新返回的状态
        if(datas==null){
            return ResultState.LOAD_ERROR;
        }else{
            int size=datas.size();
            if(size>0){
                return ResultState.LOAD_SUCCESS;
            }else {
                return ResultState.LOAD_EMPTY;
            }
        }
    }



    //取得成功界面的xml布局资源id, 钩子方法 ，hook方法，把子类的方法钩处理，在模板设计模式中常用
    public int getLayoutResId() {
        return  R.layout.layout_listview_refresh;
    }
    //由于父类中不能决定子类界面的适配器，所以抽象出去，由子类来实现
    protected abstract GMBaseListAdapter<T> getAdapter() ;

    //由于父类中不能决定子类如何访问网络，所以抽象出去，由子类来实现
    protected abstract BaseProtocol<List<T>> getProtocol();
}
