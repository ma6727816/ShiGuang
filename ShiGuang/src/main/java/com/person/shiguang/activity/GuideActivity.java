package com.person.shiguang.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.person.shiguang.R;
import com.person.shiguang.adapter.GuideAdapter;
import com.person.shiguang.domain.CityInfo;
import com.person.shiguang.net.NetCallBack;
import com.person.shiguang.net.NetLoader;
import com.person.shiguang.util.Constant;
import com.person.shiguang.util.SPUtils;
import com.person.shiguang.util.UrlPath;

import org.xutils.http.HttpMethod;

import java.util.HashMap;
import java.util.Map;

import de.greenrobot.event.EventBus;

/**
 * 引导界面
 */
public class GuideActivity extends Activity {

    private ViewPager vp_guide;
    private int[] images = {R.drawable.lead_bg1,R.drawable.lead_bg2,R.drawable.lead_bg3,R.drawable.lead_bg4,};
    private int[] bottoms = {R.drawable.lead_bg1_iv,R.drawable.lead_bg2_iv,R.drawable.lead_bg3_iv,R.drawable.lead_bg4_iv};
    private LocationClient client;
    private Button btn_guide;
    private CityInfo citiInfo;


    private BDLocationListener listener = new BDLocationListener() {
        @Override
        public void onReceiveLocation(BDLocation bdLocation) {

            if (bdLocation != null) {
                double longitude = bdLocation.getLongitude();
                double latitude = bdLocation.getLatitude();
                Log.e("TAG", longitude + " , " + latitude);
                getCityName(longitude, latitude);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();
    }

    private void initData() {
        //获取地址
        EventBus.getDefault().register(this);

        client = new LocationClient(this);
        client.registerLocationListener(listener);
        initLocation();

        vp_guide.setAdapter(new GuideAdapter(this, images, bottoms));
        vp_guide.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == vp_guide.getAdapter().getCount() - 1) {
                    //说明是最后一个
                    btn_guide.setVisibility(View.VISIBLE);
                } else {
                    btn_guide.setVisibility(View.GONE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        btn_guide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //到选择城市的页面
                toChooseCity();
            }
        });
    }


    /**
     * 接收EventBus消息
     *
     * @param cityInfo
     */
    public void onEventMainThread(CityInfo cityInfo) {
        this.citiInfo = cityInfo;
    }

    /**
     * 初始化参数
     */
    private void initLocation() {
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        option.setIgnoreKillProcess(false);
        client.setLocOption(option);
        client.start();
    }

    /**
     * 获取地址
     * @param longitude
     * @param latitude
     */
    private void getCityName(double longitude, double latitude) {
        Map<String, String> map = new HashMap<>();
        map.put("longitude", longitude + "");
        map.put("latitude", latitude + "");
        //联网
        new NetLoader(UrlPath.CITY_NAME, HttpMethod.GET,map,new NetCallBack<CityInfo>(CityInfo.class,true));
    }

    /**
     * 到选择城市的页面
     */
    private void toChooseCity() {
        if (citiInfo != null) {
            Intent intent = new Intent(this,CityActivity.class);
            intent.putExtra(Constant.CITY_ID, citiInfo.getCityId());
            SPUtils.getInstance(this).add(SPUtils.CITY_LOCATION, citiInfo.getCityId());
            startActivity(intent);
            finish();
        }
    }

    private void initView() {
        setContentView(R.layout.activity_guide);
        vp_guide = (ViewPager) findViewById(R.id.vp_guide);
        btn_guide = (Button) findViewById(R.id.btn_guide);
    }

    @Override
    protected void onDestroy() {
        client.stop();
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }
}
