package com.person.shiguang.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import com.person.shiguang.R;
import com.person.shiguang.pager.BasePager;
import com.person.shiguang.pager.DiscoverPager;
import com.person.shiguang.pager.HomePager;
import com.person.shiguang.pager.PayTicketPager;
import com.person.shiguang.pager.ShopPager;
import com.person.shiguang.pager.UserPager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {

    private RadioGroup rg_bottom;
    private int pager;
    private List<BasePager> basePagers;
    private FragmentManager fm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();
        addListener();
    }

   // @Override
    protected void initView() {
        setContentView(R.layout.activity_main);
        rg_bottom = (RadioGroup) findViewById(R.id.rg_bottom);
    }

   // @Override
    public void initData() {
        fm = getSupportFragmentManager();
        basePagers = new ArrayList<>();
        basePagers.add(new HomePager(this));
        basePagers.add(new PayTicketPager(this));
        basePagers.add(new ShopPager(this));
        basePagers.add(new DiscoverPager(this));
        basePagers.add(new UserPager(this));
    }

  //  @Override
    public void addListener() {
        rg_bottom.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_tab_home:
                        pager = 0;
                        break;
                    case R.id.rb_tab_payticket:
                        pager = 1;
                        break;
                    case R.id.rb_tab_shop:
                        pager = 2;
                        break;
                    case R.id.rb_tab_discover:
                        pager = 3;
                        break;
                    case R.id.rb_tab_users:
                        pager = 4;
                        break;
                }
                changeShowPager();
            }
        });

        rg_bottom.check(R.id.rb_tab_home);
    }


    /**
     * 改变现实的界面
     * @param
     */
    private void changeShowPager() {

        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.fl_main, new Fragment(){
            @Nullable
            @Override
            public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
                BasePager basePager = basePagers.get(pager);
                if (!basePager.isInit) {
                    basePager.initData();
                }
                return basePager.rootView;
            }
        });
        transaction.commit();
    }
}
