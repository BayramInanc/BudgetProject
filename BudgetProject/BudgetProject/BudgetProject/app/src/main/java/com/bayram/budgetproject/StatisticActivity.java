package com.bayram.budgetproject;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

public class StatisticActivity extends AppCompatActivity {
    private ViewPager mViewPager;
    private StatisticAdapter mStatisticAdapter;
    private TabLayout mTabLayout;
    private Toolbar mToolbar;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistic);
        mTabLayout= (TabLayout) findViewById(R.id.my_tablayout);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle("İSTATİSTİK");
        mViewPager = (ViewPager) findViewById(R.id.viewPager);
        mTabLayout.setupWithViewPager(mViewPager);
        mStatisticAdapter = new StatisticAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mStatisticAdapter);
    }
}
