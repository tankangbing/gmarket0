package com.itheima.gmarket.base;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

/**
     * 所有ViewHolder的基类
     */
    public abstract  class BaseHolder<T>{
    protected Context context;
        View rootView;//列表条目xml布局的根节点对象
        public BaseHolder(ViewGroup parent){//
            if(parent!=null){
                context=parent.getContext();
            }

            //当前的View就是列表条目xml布局的根节点对象
            rootView=initView(parent);
            //5. 把viewHolder以tag的形式添加到convertView中
            rootView.setTag(this);
        }
        /**
         *  一、 ItemView初始化
         *   - 把列表条目的xml布局实例化为view对象，返回的是列表条目xml布局的根节点视图对象
         *   - 通过ViewHolder绑定要操作的列表条目布局的子控件
         */

/*        private View initView(ViewGroup parent) {
            //3. 把列表条目的xml布局实例化为view对象，返回的是列表条目xml布局的根节点视图对象
            View itemView= CommonUtil.getInflate().inflate(R.layout.item_home,parent,false);
            //4. 通过ViewHolder绑定要操作的列表条目布局的子控件
            holderNameTv= (TextView) itemView.findViewById(R.id.tv_name);
            return  itemView;
        }*/

        /** 对外提供一个绑定数据的方法
         * 二、绑定数据给要操作的列表条目的子控件
          */

/*        public  void bindData(AppInfo data){
            // 8. 通过ViewHolder绑定数据到对应的列表条目子控件中
            holderNameTv.setText(data.getName());
            //todo
        }*/
        //取得列表条目xml布局的根节点对象
        public View getRootView(){
            return  rootView;
        }
         //由于每个功能页面的Holder都有差异，所以抽象出去，由子类来实现
        /**
         *  一、 ItemView初始化
         *   - 把列表条目的xml布局实例化为view对象，返回的是列表条目xml布局的根节点视图对象
         *   - 通过ViewHolder绑定要操作的列表条目布局的子控件
         */
        protected abstract  View initView(ViewGroup parent);
        //由于每个功能页面的列表条目都有差异，且要绑定的数据也不能确定，所以抽象出去，由子类来实现
        public abstract void bindData(T data);
    }