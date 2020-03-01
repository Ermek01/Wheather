package com.geektech.wheather.ui.test;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.geektech.wheather.R;
import com.geektech.wheather.ui.base.BaseFragment;

public class TestFragment extends BaseFragment {

    @Override
    protected int getViewLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
