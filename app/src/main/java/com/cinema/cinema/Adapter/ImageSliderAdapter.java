package com.cinema.cinema.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import com.bumptech.glide.Glide;
import java.util.List;

public class ImageSliderAdapter extends PagerAdapter {
  Context mContext;
  List<String> sliderImageUrls;


  public ImageSliderAdapter(Context context, List<String> sliderImageUrls) {
    this.mContext = context;
    this.sliderImageUrls = sliderImageUrls;
  }

  @Override
    public boolean isViewFromObject(View view, Object object) {
    return view == ((ImageView) object);
  }



  @Override
    public Object instantiateItem(ViewGroup container, int position) {
    ImageView imageView = new ImageView(mContext);
    imageView.setScaleType(ImageView.ScaleType.FIT_XY);
    Glide.with(mContext).load(sliderImageUrls.get(position)).into(imageView);
    ((ViewPager) container).addView(imageView, 0);
    return imageView;
  }

  @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
    ((ViewPager) container).removeView((ImageView) object);
  }

  @Override
    public int getCount() {
    return sliderImageUrls.size();
  }
}
