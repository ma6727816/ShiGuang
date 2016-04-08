package com.person.shiguang.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.person.shiguang.R;


/**
 * 引导页面ViewPager的适配器
 * Created by Garbled on 2016/4/8.
 */
public class GuideAdapter extends PagerAdapter {

    private final int[] images;
    private final int[] bottom;
    private Context context;

    public GuideAdapter(Context context, int[] images, int[] bottom) {
        this.images = images;
        this.context = context;
        this.bottom = bottom;
    }

    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = View.inflate(context, R.layout.item_guide_vp, null);
        ImageView view1 = (ImageView) view.findViewById(R.id.iv_item_guide_bg);
        ImageView view2 = (ImageView) view.findViewById(R.id.iv_item_guide_bottom);
        view1.setImageResource(images[position]);
        view2.setImageResource(bottom[position]);

        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
