package com.itheima.gmarket.holder;

import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.itheima.gmarket.R;
import com.itheima.gmarket.base.BaseHolder;
import com.itheima.gmarket.model.net.CategoryInfo;
import com.itheima.gmarket.utils.CommonUtil;
import com.itheima.gmarket.utils.ServerAPI;

import org.xutils.x;

import java.nio.BufferUnderflowException;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;

/**
 * Created by Administrator on 2017/2/11 0011.
 * 分类模块具体内容类型的Holder
 */

public class CategoryContentHolder extends BaseHolder<CategoryInfo> {
    @BindView(R.id.iv_image1)
    ImageView ivImage1;
    @BindView(R.id.tv_name1)
    TextView tvName1;
    @BindView(R.id.ll_sub1)
    LinearLayout llSub1;
    @BindView(R.id.iv_image2)
    ImageView ivImage2;
    @BindView(R.id.tv_name2)
    TextView tvName2;
    @BindView(R.id.ll_sub2)
    LinearLayout llSub2;
    @BindView(R.id.iv_image3)
    ImageView ivImage3;
    @BindView(R.id.tv_name3)
    TextView tvName3;
    @BindView(R.id.ll_sub3)
    LinearLayout llSub3;

    public CategoryContentHolder(ViewGroup parent) {
        super(parent);
    }

    @Override
    protected View initView(ViewGroup parent) {
        View view = CommonUtil.getInflate().inflate(R.layout.item_category_content, parent, false);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void bindData(CategoryInfo data) {
        //绑定第一个app的数据
        tvName1.setText(data.name1);
        x.image().bind(ivImage1, ServerAPI.IMAGE_BASE_URL+data.url1);

        //绑定第二个app的数据
        if(TextUtils.isEmpty(data.name2)){
            llSub2.setVisibility(View.INVISIBLE);
        }else{
            llSub2.setVisibility(View.VISIBLE);
            tvName2.setText(data.name2);
            x.image().bind(ivImage2, ServerAPI.IMAGE_BASE_URL+data.url2);
        }

        //绑定第三个app的数据
        if(TextUtils.isEmpty(data.name3)){
            llSub3.setVisibility(View.INVISIBLE);
        }else{
            llSub3.setVisibility(View.VISIBLE);
            tvName3.setText(data.name3);
            x.image().bind(ivImage3, ServerAPI.IMAGE_BASE_URL+data.url3);
        }

    }
}
