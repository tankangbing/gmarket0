package com.itheima.gmarket.fragment;

import android.view.View;
import android.widget.ListView;

import com.chanven.lib.cptr.PtrClassicFrameLayout;
import com.chanven.lib.cptr.PtrDefaultHandler;
import com.chanven.lib.cptr.PtrFrameLayout;
import com.chanven.lib.cptr.loadmore.OnLoadMoreListener;
import com.itheima.gmarket.R;
import com.itheima.gmarket.adapter.AppAdapter;
import com.itheima.gmarket.adapter.HomeAdapter;
import com.itheima.gmarket.base.BaseFragment;
import com.itheima.gmarket.base.BaseProtocol;
import com.itheima.gmarket.base.BaseRefreshListFragment;
import com.itheima.gmarket.base.GMBaseListAdapter;
import com.itheima.gmarket.model.net.AppInfo;
import com.itheima.gmarket.model.net.HomeJsonBean;
import com.itheima.gmarket.model.net.ResultState;
import com.itheima.gmarket.protocol.AppProtocol;
import com.itheima.gmarket.protocol.HomeProtocol;
import com.itheima.gmarket.utils.CommonUtil;
import com.itheima.gmarket.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/2/4 0004.
 */

public class AppFragment extends BaseRefreshListFragment<AppInfo> {
    @Override
    protected GMBaseListAdapter<AppInfo> getAdapter() {
        return new AppAdapter();
    }
    @Override
    protected BaseProtocol<List<AppInfo>> getProtocol() {
        return new AppProtocol();
    }
}
