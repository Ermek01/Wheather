package com.geektech.wheather.ui.onBoard;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.geektech.wheather.R;
import com.geektech.wheather.data.entity.OnBoardEntity;
import com.geektech.wheather.ui.base.BaseActivty;
import com.geektech.wheather.ui.main.MainActivity;

import java.util.ArrayList;

public class OnBoardActivity extends BaseActivty {
    private Toolbar toolbar;
    private ViewPager viewPager;
    private ViewPagerAdapter onBoardAdapter;
    private Button btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_board);
        initViews();
        onNextClick();
        setupClickListener();
        setupViewPager();
    }

    private void setupClickListener() {
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
            }
        });
    }

    public void setupViewPager() {
        onBoardAdapter = new ViewPagerAdapter(getResource());
        viewPager.setAdapter(onBoardAdapter);
    }

    private ArrayList<OnBoardEntity> getResource() {
        ArrayList<OnBoardEntity> list = new ArrayList<>();
        list.add(new OnBoardEntity("В данном приложении вы можете учиться", R.drawable.photo));
        list.add(new OnBoardEntity("В данном приложении вы можете обновлять", R.drawable.photo));
        list.add(new OnBoardEntity("В данном приложении вы можете удалять", R.drawable.photo));
        list.add(new OnBoardEntity("Спасибо что вы с нами))", R.drawable.photo));
        return list;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public void onNextClick() {
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (viewPager.getCurrentItem() == 3) {
                    btnNext.setText("Finish");
                    btnNext.setOnClickListener(v -> {
                        MainActivity.start(OnBoardActivity.this);
                        finish();
                    });
                } else {
                    btnNext.setText(R.string.next);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initViews() {
        toolbar = findViewById(R.id.toolbatTransparent);
        viewPager = findViewById(R.id.viewpager);
        btnNext = findViewById(R.id.buttonNext);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_skip:
                MainActivity.start(this);
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}