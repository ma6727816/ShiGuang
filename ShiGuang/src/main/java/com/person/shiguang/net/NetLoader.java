package com.person.shiguang.net;

import org.xutils.http.HttpMethod;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.Map;
import java.util.Set;

/**
 * 网络的连接工具
 * Created by Garbled on 2016/4/8.
 */
public class NetLoader {


    public NetLoader(String url, HttpMethod method, Map<String, String> params, NetCallBack callBack) {
        RequestParams entity = new RequestParams(url);
        entity.setMethod(method);
        if (params != null) {
            Set<Map.Entry<String, String>> entries = params.entrySet();
            for (Map.Entry<String, String> entry : entries) {
                entity.addBodyParameter(entry.getKey(), entry.getValue());
            }
        }
        x.http().get(entity, callBack);
    }


}
