package com.itheima.gmarket.adapter;

import android.view.View;
import android.view.ViewGroup;

import com.itheima.gmarket.base.BaseHolder;
import com.itheima.gmarket.base.GMBaseListAdapter;
import com.itheima.gmarket.holder.CategoryContentHolder;
import com.itheima.gmarket.holder.CategoryTitleHolder;
import com.itheima.gmarket.model.net.CategoryInfo;

/**
 * Created by Administrator on 2017/2/11 0011.
 */

    public class CategoryAdpater extends GMBaseListAdapter<CategoryInfo> {
    private  static  final  int ITEM_CATEGORY_TITLE=0;//标题类型
    private  static  final  int ITEM_CATEGORY_CONTENT=1;//具体内容类型
      //分类ListView要展示两种不同的条目类型（标题类型，具体内容类型） ，由Adapter来完成

    private int currentPosition;//表示当前的位置


    //重写getView方法，对currentPositon赋值
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        currentPosition=position;
        return super.getView(position, convertView, parent);
    }

    //取得列表条目的类型数量 ，默认一种类型，当前有2种类型
    @Override
    public int getViewTypeCount() {
        return super.getViewTypeCount()+1;
    }
    //取得当前要展示的列表条目的类型 ，类型用int 来描述的 ，默认的类型是0  ，假如有两种：0：标题类型 ,1：具体内容类型
    @Override
    public int getItemViewType(int position) {
        //具体返回哪种类型，由业务来决定 ，由要展示的数据来决定  categoryInfo.isTitle
        //取得当前要展示的数据
        CategoryInfo categoryInfo = list.get(position);
        if(categoryInfo.isTitle){
//            return super.getItemViewType(position);
            return ITEM_CATEGORY_TITLE;//标题类型
        }else{
            return ITEM_CATEGORY_CONTENT; //返回具体内容类型
        }
    }

    //有两种条目类型，即有两种Holder ：CategoryTitleHolder   CategoryContentHolder
    //依据当前条目要展示的条目类型来决定取得哪个Holder
    @Override
    protected BaseHolder getViewHolder(ViewGroup parent) {
//        int currentPosition=0;
        int type=getItemViewType(currentPosition);
        if(type==ITEM_CATEGORY_TITLE){
            //标题的holder
            return new CategoryTitleHolder(parent);
        }else{
            //具体内容的Holder
            return new CategoryContentHolder(parent);
        }

    }
}
