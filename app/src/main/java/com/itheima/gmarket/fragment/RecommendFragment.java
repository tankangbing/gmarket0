package com.itheima.gmarket.fragment;

import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import com.itheima.gmarket.base.BaseFragment;
import com.itheima.gmarket.model.net.AppInfo;
import com.itheima.gmarket.model.net.ResultState;
import com.itheima.gmarket.protocol.RecommendProtocol;
import com.itheima.gmarket.randomlayout.StellarMap;
import com.itheima.gmarket.utils.CommonUtil;
import com.itheima.gmarket.utils.ToastUtil;
import com.itheima.gmarket.views.ContentPage;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static android.R.attr.name;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

/**
 * Created by Administrator on 2017/2/4 0004.
 */

public class RecommendFragment extends BaseFragment {
    private int currentPositon=0;

    private List<String> datas=new ArrayList<>();
    private StellAdapter adapter;
    private StellarMap stellarMap;

    //1. 创建本片段的成功界面方法
    @Override
    protected View onSubCreateSuccessView() {
        //星空图视图 ，继承ViewGroup
        stellarMap = new StellarMap(context);
        //设置适配器
        adapter = new StellAdapter();
//        stellarMap.setAdapter(adapter);
        int padding= CommonUtil.dp2px(10);
        //设置内不安静
        stellarMap.setInnerPadding(padding,padding,padding,padding);
        //设置组的起始动画
        stellarMap.setGroup(1,true);
        /** 设置隐藏组和显示组的x和y的规则 */
        stellarMap.setRegularity(12,8);
        return stellarMap;
    }
    //加载本片段数据的方法
    @Override
    protected ResultState onSubLoad() {
        RecommendProtocol protocol=new RecommendProtocol();
        //取得服务端获取的json数据
        datas = protocol.getData(100);
        //重新设置适配器
        if(datas!=null){
            stellarMap.setAdapter(adapter);
        }
        //检测状态
        if(datas!=null){
            if(datas.size()==0){
                return  ResultState.LOAD_EMPTY;
            }else {
                return  ResultState.LOAD_SUCCESS;
            }
        }
        return ResultState.LOAD_ERROR;

    }

    private class  StellAdapter implements StellarMap.Adapter ,View.OnClickListener{
        Random random=null;
        public  StellAdapter(){
            random=new Random();
        }


//        分为2组
        @Override
        public int getGroupCount() {
            return 2;
        }

        @Override
        public int getCount(int group) {
            return 20;
        }
        //返回StellerMap的 itemView ，stellarMap的子控件
        @Override
        public View getView(int group, final int position, View convertView) {
            currentPositon=position;
            TextView tv=new TextView(context);
            tv.setText(datas.get(position));
            //设置随机颜色
            int red=10+random.nextInt(255-30);  // 范围10-235 ,避免极致
            int green=10+random.nextInt(255-30);  // 范围10-235 ,避免极致
            int blue=10+random.nextInt(255-30);  // 范围10-235 ,避免极致
            int rgb= Color.rgb(red,green,blue);
            tv.setTextColor(rgb);
            //设置随机大小
            int fontSize=10+random.nextInt(20);  // 范围10-30 ,
            tv.setTextSize(fontSize);
            //对TextView设置点击监听
             //1.直接设置点击监听
/*            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ToastUtil.show(datas.get(position));
                }
            });*/
            //2. 通过类实现接口的方式
            tv.setOnClickListener(this);

            //3. 通过tag附加参数
            AppInfo appInfo=new AppInfo();
            appInfo.setName(datas.get(position));
            appInfo.setPackageName("包名");
            tv.setTag(appInfo);
            return tv;
        }

        @Override
        public int getNextGroupOnPan(int group, float degree) {
            return 0;
        }

        @Override
        public int getNextGroupOnZoom(int group, boolean isZoomIn) {
            return 0;
        }
       //点击监听的回调方法
        @Override
        public void onClick(View v) {
            //该方式取得的是最后一个数据
//            ToastUtil.show(datas.get(currentPositon));

            //方式1
/*          TextView tv= (TextView) v;
            String name=tv.getText().toString();
            ToastUtil.show(name+"-");*/
            //通过tag附加参数
/*            AppInfo appInfo= (AppInfo) v.getTag();
            ToastUtil.show(appInfo.getName()+appInfo.getPackageName());*/
        }
    }
}
