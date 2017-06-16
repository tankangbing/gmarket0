package com.itheima.gmarket.holder;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.itheima.gmarket.R;
import com.itheima.gmarket.base.BaseHolder;
import com.itheima.gmarket.model.net.CategoryInfo;
import com.itheima.gmarket.utils.CommonUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/2/11 0011.
 * 分类模块标题类型的Holder
 */

public class CategoryTitleHolder extends BaseHolder<CategoryInfo> {
    @BindView(R.id.tv_title)
    TextView tvTitle;

    public CategoryTitleHolder(ViewGroup parent) {
        super(parent);
    }

    @Override
    protected View initView(ViewGroup parent) {
        View view = CommonUtil.getInflate().inflate(R.layout.item_category_title, parent, false);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void bindData(CategoryInfo data) {
        tvTitle.setText(data.title);
    }
}
