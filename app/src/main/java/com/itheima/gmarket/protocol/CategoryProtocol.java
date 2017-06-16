package com.itheima.gmarket.protocol;

import android.text.TextUtils;

import com.itheima.gmarket.base.BaseProtocol;
import com.itheima.gmarket.model.net.CategoryInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/2/11 0011.
 */

public class CategoryProtocol extends BaseProtocol<List<CategoryInfo>> {
    @Override
    protected String getParams() {
        return "";
    }

    @Override
    protected String getKey() {
        return "category";
    }

    /**
     * 手动解析json数据 ，看到什么解析什么,一层一层剥离
     *
     * @param result
     * @return
     */
/*    [
    {
        "infos": [
        {
            "name1": "休闲"
        },
        {
            "name1": "射击"
        }
        ],
        "title": "游戏"
    },
    {
        "infos": [
        {
            "name1": "浏览器"
        }
        ],
        "title": "应用"
    }
    ]*/
    @Override
    protected List<CategoryInfo> parseData(String jsonStr) {
        List<CategoryInfo> datas=null;
         if(!TextUtils.isEmpty(jsonStr)){
             try {
                 datas=new ArrayList<>();
                 //最外层为json数组
                 JSONArray jsonArray=new JSONArray(jsonStr);
                 //遍历最外层的json数组
                 for (int i = 0; i < jsonArray.length(); i++) {
                     //外层json数组内部元素是json对象
                     JSONObject jsonObject = jsonArray.getJSONObject(i);
                      //json对象元素包含两个属性  title： string       infos： json数组

                     //取标题属性 ,把标题的数据添加到集合中
                     String title=jsonObject.getString("title");
                     CategoryInfo titleInfo=new CategoryInfo();
                     titleInfo.title=title;
                     titleInfo.isTitle=true;
                     datas.add(titleInfo);

                     //取具体内容类型的数组数据
                     //取得内层的json数组
                     JSONArray innerJsonArray = jsonObject.getJSONArray("infos");
                     //遍历内层的json数组
                     for (int j = 0; j < innerJsonArray.length(); j++) {
                         CategoryInfo categoryInfo=new CategoryInfo();
                         JSONObject innerJsonObject = innerJsonArray.getJSONObject(j);
                         categoryInfo.name1=innerJsonObject.getString("name1");
                         categoryInfo.name2=innerJsonObject.getString("name2");
                         categoryInfo.name3=innerJsonObject.getString("name3");
                         categoryInfo.url1=innerJsonObject.getString("url1");
                         categoryInfo.url2=innerJsonObject.getString("url2");
                         categoryInfo.url3=innerJsonObject.getString("url3");
                         categoryInfo.isTitle=false;//具体内容类型的数据
                         datas.add(categoryInfo);

                     }



                 }
             } catch (JSONException e) {
                 e.printStackTrace();
             }

         }

        return datas;
    }
}
