package com.person.shiguang.pager;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

/**
 * 商城界面
 * Created by Garbled on 2016/4/8.
 */
public class ShopPager extends BasePager {
    private TextView textView;

    public ShopPager(Activity mActivity) {
        super(mActivity);
    }

    @Override
    protected View initView() {
        textView = new TextView(mActivity);
        textView.setTextSize(30);
        textView.setGravity(Gravity.CENTER);
        textView.setTextColor(Color.RED);
        return textView;
    }

    @Override
    public void initData() {
        super.initData();
        textView.setText("商城");
    }
}
