package com.person.shiguang;

import android.app.Application;

import org.xutils.x;

/**
 * Created by Garbled on 2016/4/8.
 */
public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        x.Ext.init(this);
        x.Ext.setDebug(true);
    }
}
