package com.person.shiguang.pager;

import android.app.Activity;
import android.view.View;

/**
 * 页面的基类
 * Created by Garbled on 2016/4/8.
 */
public abstract class BasePager {
    public Activity mActivity;
    public View rootView;
    public boolean isInit;

    public BasePager(Activity mActivity) {
        this.mActivity = mActivity;
        rootView = initView();
    }

    protected abstract View initView();

    public void initData(){
        isInit = true;
    }
}
