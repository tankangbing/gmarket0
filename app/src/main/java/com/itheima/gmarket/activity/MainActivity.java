package com.itheima.gmarket.activity;

import android.app.Activity;
import android.app.Service;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import com.itheima.gmarket.GMApplication;
import com.itheima.gmarket.R;
import com.itheima.gmarket.adapter.MainPagerAdapter;
import com.itheima.gmarket.base.BaseActivity;
import com.itheima.gmarket.model.db.AppEntity;
import com.itheima.gmarket.utils.CommonUtil;
import com.itheima.gmarket.utils.ToastUtil;

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //模拟网络访问，获取用户的昵称
        String nick="Android";
        //取得唯一的Application对象
        GMApplication application= (GMApplication) getApplication();
        //设置application维护的全局变量
        application.setNick(nick);
//        Toast.makeText(GMApplication.getContext(),application.getNick(),Toast.LENGTH_SHORT).show();
//        ToastUtil.show(R.string.app_name);

        initView();

    }
    //1. 初始化视图
    private void initView() {
        //1.1 取得NavigationView对象
        NavigationView navView= (NavigationView) findViewById(R.id.nav_view);
        //设置NavigationView 选择项监听
        navView.setNavigationItemSelectedListener(this);

        //1.2 引入 DrawerLayout对象
        drawerLayout= (DrawerLayout) findViewById(R.id.main_drawer);

        //1.3 引入及初始化Toolbar
        Toolbar toolbar= (Toolbar) findViewById(R.id.toolbar);
        //初始化Toolbar，注意在setSupportActionBar之前初始化Toolbar
        //设置Toolbar标题颜色
        toolbar.setTitleTextColor(Color.WHITE);
        //取代Actionbar ,要设置没有Actionbar主题
        setSupportActionBar(toolbar);

        //1.4 设置Toolbar的导航图标
        ActionBarDrawerToggle barDrawerToggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.drawer_open,R.string.drawer_close);
        barDrawerToggle.syncState();//同步状态
        drawerLayout.addDrawerListener(barDrawerToggle);


        //1.5 :初始化ViewPager
        ViewPager viewPager= (ViewPager) findViewById(R.id.main_viewpager);
        viewPager.setAdapter(new MainPagerAdapter(getSupportFragmentManager()));

        //1.6 :初始化TabLayout
        TabLayout tablayout= (TabLayout) findViewById(R.id.tab_layout);
        tablayout.setTabMode(TabLayout.MODE_SCROLLABLE);//滚动模式
        //与ViewPager 绑定在一起 ,TabLayout的页签标题通过PagerAdpater的getPagerTitle（）来取得
        tablayout.setupWithViewPager(viewPager);


    }

    //导航项选择监听
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        drawerLayout.closeDrawers();//关闭抽屉，隐藏菜单
        item.setChecked(true);//设置当前的菜单项为选中状态
        switch (item.getItemId()){
            case  R.id.nav_camera:
                ToastUtil.show("拍照");
                break;
            case R.id.nav_share:
                ToastUtil.show("分享");
                break;
            case R.id.nav_send:
                ToastUtil.show("发送");
                break;
        }
        return false;
    }
}
