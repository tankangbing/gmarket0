package com.itheima.gmarket.protocol;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.itheima.gmarket.base.BaseProtocol;
import com.itheima.gmarket.model.net.SubjectInfo;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Administrator on 2017/2/9 0009.
 */

public class SubjectProtocol extends BaseProtocol<List<SubjectInfo>> {
    @Override
    protected String getParams() {
        return "";
    }
    @Override
    protected String getKey() {
        return "subject";
    }

    @Override
    protected List<SubjectInfo> parseData(String result) {
        if(!TextUtils.isEmpty(result)){
            Gson gson=new Gson();
            SubjectInfo[] subjectInfos = gson.fromJson(result, SubjectInfo[].class);
            return Arrays.asList(subjectInfos);
        }
        return null;
    }
}
