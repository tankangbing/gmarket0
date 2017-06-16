package com.itheima.gmarket.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.itheima.gmarket.R;
import com.itheima.gmarket.fragment.HomeDetailFragment;
import com.itheima.gmarket.model.net.AppInfo;
import com.itheima.gmarket.utils.ToastUtil;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        //1.1 取得调用者传递过来的附件数据
//        String packageName = getIntent().getStringExtra("packageName");

        //1.2 取得取得调用者传递过来的附加数据（对象 实现 Serializable接口）
//       AppInfo appInfo= (AppInfo) getIntent().getSerializableExtra("appInfo");

        //1.3 取得取得调用者传递过来的附加数据（对象 实现 Parcelable接口）
        AppInfo appInfo= (AppInfo) getIntent().getParcelableExtra("appInfo");
//        ToastUtil.show(appInfo.getName()+appInfo.getIconUrl());

        //2 对ActionBar 的操作
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //显示返回箭头，且监听返回箭头
        getSupportActionBar().setTitle("应用详情");//设置ActionBar的标题

        //3. 向Activity动态管理片段 ,替换片段
        HomeDetailFragment detailFragment=new HomeDetailFragment();
        //3.1 Activity向片段传递参数
        Bundle args=new Bundle();
        args.putString("packageName",appInfo.getPackageName());
        //设置参数
        detailFragment.setArguments(args);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.flt_detail_content,detailFragment).commit();
    }


    //添加菜单按钮


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //把菜单布局实例化为菜单对象
        getMenuInflater().inflate(R.menu.test_actionbar_menu,menu);
//        menu.add(); 通过代码添加菜单按钮
        return super.onCreateOptionsMenu(menu);
    }

    //对菜单按钮，返回箭头做监听处理
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //getItemId：菜单的唯一标识
        switch (item.getItemId()){
            case android.R.id.home:
                ToastUtil.show("返回箭头");
                finish();//销毁当前Activity
                break;
            case R.id.menu_refresh:
                ToastUtil.show("刷新");
                break;

        }
        return super.onOptionsItemSelected(item);
    }

}
