package com.itheima.gmarket.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.itheima.gmarket.R;
import com.itheima.gmarket.base.BaseFragment;
import com.itheima.gmarket.fragment.AppFragment;
import com.itheima.gmarket.fragment.CategoryFragment;
import com.itheima.gmarket.fragment.FragmentFractory;
import com.itheima.gmarket.fragment.GameFragment;
import com.itheima.gmarket.fragment.HomeFragment;
import com.itheima.gmarket.fragment.HotFragment;
import com.itheima.gmarket.fragment.RecommendFragment;
import com.itheima.gmarket.fragment.SubjectFragment;
import com.itheima.gmarket.utils.CommonUtil;
import com.itheima.gmarket.utils.LogUtil;

/**
 * Created by Administrator on 2017/2/4 0004.
 */

public class MainPagerAdapter extends FragmentPagerAdapter {
    String[] tabs=null;
    public MainPagerAdapter(FragmentManager fm) {
        super(fm);
        tabs= CommonUtil.getStringArray(R.array.tab_names);
    }
   //取得片段
    @Override
    public Fragment getItem(int position) {
//        LogUtil.d("MainPagerAdapter-->getItem"+position);
        return FragmentFractory.createFragment(position);
    }
    @Override
    public int getCount() {
        return tabs.length;
    }
   //TabLayout的页签标题依赖该方法进行展示
    @Override
    public CharSequence getPageTitle(int position) {
        return tabs[position];
    }
}
