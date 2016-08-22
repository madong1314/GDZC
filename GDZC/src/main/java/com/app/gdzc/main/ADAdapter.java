package com.app.gdzc.main;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.app.gdzc.R;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by 王少岩 on 2016/8/22.
 */
public class ADAdapter extends PagerAdapter {
    private List<String> ads;
    private Context mContext;

    public ADAdapter(Context context) {
        mContext = context;
    }

    public void setAds(List<String> ads) {
        this.ads = ads;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = View.inflate(mContext, R.layout.item_viewpager, null);
        container.addView(view);
        ImageView imageView = (ImageView) view.findViewById(R.id.pic_viewPager);
        ImageLoader.getInstance().displayImage(ads.get(position), imageView);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return ads.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}
