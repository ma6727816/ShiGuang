package com.person.shiguang.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.person.shiguang.R;
import com.person.shiguang.util.Constant;
import com.person.shiguang.util.SPUtils;

import org.xutils.common.util.LogUtil;

/**
 * 欢迎界面
 */
public class SplashActivity extends Activity {

    private static final int HAVE_CITY = 0;
    private static final int GET_CITY = 1;


    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case HAVE_CITY:
                    //直接到达主界面
                    toMain();
                    break;
                case GET_CITY:
                    //说明是第一次进入 跳转到导航界面
                    toGuide();
                    break;
            }
        }
    };
    private int city;

    private void toGuide() {
        startActivity(new Intent(this, GuideActivity.class));
        finish();
    }

    private void toMain() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(Constant.CITY_ID,city);
        startActivity(intent);
        finish();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        //判断需不需要定位
        city = SPUtils.getInstance(this).getInt(SPUtils.CITY_LOCATION, -1);
        LogUtil.e(city +"");
        if (city == -1) {
            handler.sendEmptyMessageDelayed(GET_CITY, 2000);
        } else {
            handler.sendEmptyMessageDelayed(HAVE_CITY, 2000);
        }
    }



    @Override
    protected void onDestroy() {
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
            handler = null;
        }
        super.onDestroy();
    }
}
