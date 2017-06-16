package com.itheima.gmarket.protocol;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.itheima.gmarket.base.BaseProtocol;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Administrator on 2017/2/9 0009.
 */

public class HotProtocol extends BaseProtocol<List<String>> {
    @Override
    protected String getParams() {
        return "";
    }

    @Override
    protected String getKey() {
        return "hot";
    }

    @Override
    protected List<String> parseData(String result) {
        if(!TextUtils.isEmpty(result)) {
            Gson gson = new Gson();
            String[] strings = gson.fromJson(result, String[].class);

            return Arrays.asList(strings);
        }
        return  null;
    }
}
