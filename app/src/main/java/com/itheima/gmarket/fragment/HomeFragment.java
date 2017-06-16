package com.itheima.gmarket.fragment;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.chanven.lib.cptr.PtrClassicFrameLayout;
import com.chanven.lib.cptr.PtrDefaultHandler;
import com.chanven.lib.cptr.PtrFrameLayout;
import com.chanven.lib.cptr.loadmore.OnLoadMoreListener;
import com.itheima.gmarket.R;
import com.itheima.gmarket.activity.DetailActivity;
import com.itheima.gmarket.adapter.HomeAdapter;
import com.itheima.gmarket.base.BaseFragment;
import com.itheima.gmarket.base.BaseProtocol;
import com.itheima.gmarket.holder.HomeHeaderHolder;
import com.itheima.gmarket.model.net.AppInfo;
import com.itheima.gmarket.model.net.HomeJsonBean;
import com.itheima.gmarket.model.net.ResultState;
import com.itheima.gmarket.protocol.HomeProtocol;
import com.itheima.gmarket.utils.CommonUtil;
import com.itheima.gmarket.utils.ToastUtil;
import com.itheima.gmarket.views.ContentPage;

import java.util.ArrayList;
import java.util.List;

import static com.itheima.gmarket.utils.CommonUtil.inflate;

/**
 * Created by Administrator on 2017/2/4 0004.
 */

public class HomeFragment extends BaseFragment {
    private ListView listView;
    private PtrClassicFrameLayout ptrLayout;//下拉刷新布局
    private List<AppInfo> datas=new ArrayList<>();
    private List<String> pictrues=new ArrayList<>();
    private HomeAdapter adapter;
    private HomeHeaderHolder headerHolder;

    //1. 创建本片段的成功界面方法
    @Override
        protected View onSubCreateSuccessView() {
        /***************与UI界面相关**********/
        //1.1 初始化布局界面
        View view=CommonUtil.inflate(R.layout.layout_listview_refresh);
        listView= (ListView) view.findViewById(R.id.test_list_view);
        ptrLayout= (PtrClassicFrameLayout) view.findViewById(R.id.test_list_view_frame);

        //1.2 准备适配器,设置适配器
        adapter = new HomeAdapter();
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


        //1.5 轮播图以listView头部的形式添加进来

        if(listView.getHeaderViewsCount()==0){
            headerHolder = new HomeHeaderHolder(listView);
            listView.addHeaderView(headerHolder.getRootView());
            //设置轮播图的数据
            headerHolder.bindData(pictrues);
        }


        //1.6 对列表条目设置点击监听
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //todo
                //1.6.1
                //取得当前条目的数据 ,因为ListView添加了头部，所以position会在集合数据中+1
                int dataPosition=position-listView.getHeaderViewsCount();
               AppInfo appInfo= datas.get(dataPosition);
                //1.6.2 通过Intent跳转到DetailActivity
                Intent intent=new Intent();
                //附加参数
//                intent.putExtra("packageName",appInfo.getPackageName());

                //附加对象数据 ,对应要传递对象，需要序列化
                intent.putExtra("appInfo",appInfo);


                //显示调用
                intent.setClass(CommonUtil.getContext(), DetailActivity.class);
                startActivity(intent);
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
        HomeProtocol protocol=new HomeProtocol();

        /**依据刷新的类型分别处理数据
         * 1. 下拉刷新 ： 集合的数据应该是最新的数据，且是最多20条，index 索引号应该是 0
         * 2. 加载更多 ： 在当前的数据集合中再添加加载更多获取到的数据 ， 索引号要逐步增加
         */
        if(!ptrLayout.isLoadingMore()){
            //目前是下拉刷新操作
            datas.clear();//清除集合
            pictrues.clear();
        }

        //index ：页面 ，默认为0 ，取第0个页面的数据

        // 2.1 分离数据，取得要展示的数据
        //index=0(homelist0) ,20(homelist2),40(homelist3),60(homelist1) ,80(homelist2),100
        //
        HomeJsonBean homeJsonBean = protocol.getData(datas.size());
        if(homeJsonBean!=null){
            List<AppInfo> appinfos = homeJsonBean.getList();
            datas.addAll(appinfos);
            //分离轮播图的数据
            pictrues.addAll(homeJsonBean.getPicture());
            //重新更新listview的数据，展示listveiw界面 ,在UI线程操作
            CommonUtil.runOnUiThread(new Runnable() {
                @Override
                public void run() {
//                    adapter.setList(datas); //60条
                    adapter.notifyDataSetChanged();
                    //绑定数据给轮播图的Holder
                    headerHolder.bindData(pictrues);
                    ptrLayout.refreshComplete();//下拉刷新结束 ，隐藏下拉刷新的界面
                    ptrLayout.loadMoreComplete(true);//加载更多完成，隐藏加载更多界面
                }
            });
        }

        //更新返回的状态
        if(homeJsonBean==null){
            return ResultState.LOAD_ERROR;
        }else{
            int size=homeJsonBean.getList().size();
            if(size>0){
                return ResultState.LOAD_SUCCESS;
            }else {
                return ResultState.LOAD_EMPTY;
            }
        }
    }
}
