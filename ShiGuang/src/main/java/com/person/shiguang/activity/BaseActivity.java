package com.person.shiguang.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

/**
 * Created by Garbled on 2016/4/8.
 */
public abstract class BaseActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();
        addListener();
    }

    /**
     * 添加监听
     */
    public void addListener() {

    }

    /**
     * 初始化数据
     */
    public void initData() {

    }

    /**
     * 初始化试图
     */
    protected abstract void initView();
}
