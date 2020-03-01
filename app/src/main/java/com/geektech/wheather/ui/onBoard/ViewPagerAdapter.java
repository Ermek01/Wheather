package com.geektech.wheather.ui.onBoard;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.geektech.wheather.R;
import com.geektech.wheather.data.entity.OnBoardEntity;

import java.util.ArrayList;

public class ViewPagerAdapter extends PagerAdapter {
    private ArrayList<OnBoardEntity> resource;

    public ViewPagerAdapter(ArrayList<OnBoardEntity> resours) {
        this.resource = resours;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.viewpagerlayout, null);
        initView(view, container, position);
        return view;
    }

    private void initView(View view, ViewGroup container, int position) {
        ImageView imageView = view.findViewById(R.id.imageview);
        imageView.setImageDrawable(container.getContext().getResources().getDrawable(resource.get(position).getImg()));
        container.addView(view);
    }

    @Override
    public int getCount() {
        return resource.size();
    }


    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }
}