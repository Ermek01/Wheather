package com.geektech.wheather.ui.onBoard;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.geektech.wheather.R;
import com.geektech.wheather.data.PreferenceHelper;
import com.geektech.wheather.data.entity.OnBoardEntity;
import com.geektech.wheather.ui.base.BaseActivty;
import com.geektech.wheather.ui.main.MainActivity;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class OnBoardActivity extends BaseActivty {
    private Toolbar toolbar;
    private ViewPager viewPager;
    private ViewPagerAdapter onBoardAdapter;
    private Button btnNext;
    private TabLayout tabLayout;
    private MenuItem menuItem;

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
        list.add(new OnBoardEntity("В данном приложении вы можете учиться", R.drawable.image1));
        list.add(new OnBoardEntity("В данном приложении вы можете обновлять", R.drawable.image2));
        list.add(new OnBoardEntity("В данном приложении вы можете удалять", R.drawable.delete));
        list.add(new OnBoardEntity("Спасибо что вы с нами))", R.drawable.image4));
        return list;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        menuItem = menu.findItem(R.id.action_skip);
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
                    btnNext.setText("Начать");
                    menuItem.setTitle("Готово");
                    btnNext.setOnClickListener(v -> {
                        MainActivity.start(OnBoardActivity.this);
                        finish();
                    });
                } else {
                    btnNext.setText(R.string.next);
                    menuItem.setTitle("Пропустить");
                }
            }


            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        tabLayout.setupWithViewPager(viewPager, true);
    }

    private void initViews() {
        toolbar = findViewById(R.id.toolbatTransparent);
        viewPager = findViewById(R.id.viewpager);
        btnNext = findViewById(R.id.buttonNext);
        tabLayout = findViewById(R.id.tab_layout);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_skip:
                MainActivity.start(this);
                PreferenceHelper.setIsShow(true);
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
