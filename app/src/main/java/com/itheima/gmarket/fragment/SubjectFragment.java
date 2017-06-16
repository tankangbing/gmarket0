package com.itheima.gmarket.fragment;

import android.view.View;
import android.widget.TextView;

import com.itheima.gmarket.adapter.SubjectAdapter;
import com.itheima.gmarket.base.BaseFragment;
import com.itheima.gmarket.base.BaseProtocol;
import com.itheima.gmarket.base.BaseRefreshListFragment;
import com.itheima.gmarket.base.GMBaseListAdapter;
import com.itheima.gmarket.model.net.ResultState;
import com.itheima.gmarket.model.net.SubjectInfo;
import com.itheima.gmarket.protocol.SubjectProtocol;
import com.itheima.gmarket.views.ContentPage;

import java.util.List;

/**
 * Created by Administrator on 2017/2/4 0004.
 */

public class SubjectFragment extends BaseRefreshListFragment<SubjectInfo> {

    @Override
    protected GMBaseListAdapter getAdapter() {
        return new SubjectAdapter();
    }

    @Override
    protected BaseProtocol<List<SubjectInfo>> getProtocol() {

        return new SubjectProtocol();
    }
}
