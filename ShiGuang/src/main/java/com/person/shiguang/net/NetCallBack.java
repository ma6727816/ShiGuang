package com.person.shiguang.net;

import com.google.gson.Gson;

import org.xutils.common.Callback;

import de.greenrobot.event.EventBus;

/**
 * Created by Garbled on 2016/4/8.
 */
public class NetCallBack<T> implements Callback.CommonCallback<String>{

    private T t;
    private Class aClass;
    private boolean isSend;

    /**
     *
     * @param aClass
     * @param isSend 代表是否需要EventBus 发送消息
     */
    public NetCallBack( Class aClass ,boolean isSend) {
        this.aClass = aClass;
        this.isSend = isSend;
    }

    private String json;

    @Override
    public void onSuccess(String result) {
        if (isSend) {
            sendEventMessage(result);
        }
    }

    private void sendEventMessage(String result) {
        t = (T) new Gson().fromJson(result, aClass);
        EventBus.getDefault().post(t);
    }

    @Override
    public void onError(Throwable ex, boolean isOnCallback) {

    }

    @Override
    public void onCancelled(Callback.CancelledException cex) {

    }

    @Override
    public void onFinished() {

    }
}
