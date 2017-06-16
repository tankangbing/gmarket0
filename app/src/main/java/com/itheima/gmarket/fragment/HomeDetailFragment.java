package com.itheima.gmarket.fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.itheima.gmarket.R;
import com.itheima.gmarket.base.BaseFragment;
import com.itheima.gmarket.holder.DetailDesHolder;
import com.itheima.gmarket.holder.DetailInfoHolder;
import com.itheima.gmarket.holder.DetailSafeHolder;
import com.itheima.gmarket.holder.DetailScreenHolder;
import com.itheima.gmarket.model.net.HomeDetailBean;
import com.itheima.gmarket.model.net.ResultState;
import com.itheima.gmarket.protocol.HomeDetailProtocol;
import com.itheima.gmarket.utils.CommonUtil;
import com.itheima.gmarket.utils.LogUtil;
import com.itheima.gmarket.utils.ServerAPI;
import com.itheima.gmarket.utils.ToastUtil;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.R.attr.content;
import static android.R.attr.data;

/**
 * Created by Administrator on 2017/2/11 0011.
 */

public class HomeDetailFragment extends BaseFragment {
    @BindView(R.id.flt_detail_info)
    FrameLayout fltDetailInfo;
    @BindView(R.id.flt_detail_safe)
    FrameLayout fltDetailSafe;
    @BindView(R.id.hsv_detail_screen)
    HorizontalScrollView hsvDetailScreen;
    @BindView(R.id.flt_detail_des)
    FrameLayout fltDetailDes;
    @BindView(R.id.pb_bar)
    ProgressBar pbBar; //驼峰法则

    @BindView(R.id.tv_download_state)
    TextView  tvDownloadState;

    private String packageName;
    private DetailInfoHolder infoHolder;
    private HomeDetailBean datas;
    private DetailSafeHolder safeHolder;
    private DetailScreenHolder screenHolder;
    private DetailDesHolder desHolder;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //取得调用者（Activity）传递过来的参数: 当前应用详情app的包名
        packageName = getArguments().getString("packageName");
        ToastUtil.show(packageName);
    }

    //1. 创建成功界面
    @Override
    protected View onSubCreateSuccessView() {
//        View view = CommonUtil.inflate(R.layout.fragment_detail_home);
        View view=LayoutInflater.from(getActivity()).inflate(R.layout.fragment_detail_home,null);
        ButterKnife.bind(this, view);

        //1.1 初始化模块界面，即把四个模块的界面根节点添加到相应的容器中
        initModeView();
        return view;
    }

    //1. 初始化模块视图，即把四个模块的界面根节点添加到相应的容器中
    private void initModeView() {
        //1.1 把详情信息模块的界面添加到相应的容器中
        infoHolder = new DetailInfoHolder(fltDetailInfo);
        //把信息模块的界面添加到容器中
        fltDetailInfo.addView(infoHolder.getRootView());

        //1.2 添加安全模块界面
        safeHolder = new DetailSafeHolder(fltDetailSafe);

        //1.3 添加截屏模块
        screenHolder = new DetailScreenHolder(hsvDetailScreen);

        //1.4 添加简介模块
        desHolder = new DetailDesHolder(fltDetailDes);
    }
    //2.初始花模块视图的数据
    private void initModeData() {
        //2.1 绑定信息模块数据
        infoHolder.bindData(datas);
        //2.2 绑定安全模块数据
        safeHolder.bindData(datas);

        //2.3 绑定截屏模块数据
        screenHolder.bindData(datas);
        //2.4 绑定简介模块数据
        desHolder.bindData(datas);

    }


    //2. 加载数据
    @Override
    protected ResultState onSubLoad() {
        //HomeDetailProtocol
        HomeDetailProtocol protocol=new HomeDetailProtocol();
        //传递包名
        protocol.setPackageName(packageName);
        datas = protocol.getData(0);
        //设置数据
        CommonUtil.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                initModeData();
            }
        });

        //检测状态
        if(datas==null){
            return ResultState.LOAD_ERROR;
        }else{
            if(datas.getSize()==0){
                return ResultState.LOAD_EMPTY;
            }else {
                return ResultState.LOAD_SUCCESS;
            }
        }


    }


    /**********************下载模块，用xutils的 http()来实现下载功能*****************************/


    /**用xutils3 实现下载
     *   1. 下载apk的url
     *  2. 下载到哪里
     *  3. 下载状态的更新
     *  4. 正在下载的话，是不能点击重复下载
     *
     * @param v
     */
    @OnClick(R.id.pb_bar)
    public void download(View v){
        String apkUrl= ServerAPI.APK_BASE_URL+datas.getDownloadUrl();
        RequestParams requestParams=new RequestParams(apkUrl);
        //本地存储apk的位置,外部存储的私有路径中
        // mnt/sdcard/android/data/cn.itheima.gmarket/files/downloads/youyuan.apk
        String savaApkFilePath=context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath()
                +"/"+datas.getPackageName()+".apk";
        requestParams.setSaveFilePath(savaApkFilePath);
//        requestParams.addBodyParameter("account","android");
//        requestParams.addBodyParameter("pwd","123");
        x.http().post(requestParams,new Callback.ProgressCallback<File>(){
            @Override
            public void onSuccess(File result) {
                LogUtil.e("download","onSuccess");
                //改文本显示的状态
                tvDownloadState.setText("下载完成");
                pbBar.setEnabled(true);
            }
            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
            }
            @Override
            public void onCancelled(CancelledException cex) {
            }
            //请求不管什么状态，最后都会调用该方法
            @Override
            public void onFinished() {
            }
            @Override
            public void onWaiting() {
            }
            @Override
            public void onStarted() {
                LogUtil.e("download","onStarted");
                tvDownloadState.setText("开始下载");
                //设置 进度条是不能点击的
                pbBar.setEnabled(false);
            }
            @Override
            public void onLoading(long total, long current, boolean isDownloading) {
                LogUtil.e("download","onLoading");
                pbBar.setMax((int) total); //进度的最大值
                pbBar.setProgress((int) current); //设置当前的进度
                tvDownloadState.setText("下载中...");
            }
        });


    }
  /*  private AsyncTask MyTask =new AsyncTask<String,Integer,String>() {
        @Override
        protected String doInBackground(String[] params) {
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }
        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }
    };*/




}
