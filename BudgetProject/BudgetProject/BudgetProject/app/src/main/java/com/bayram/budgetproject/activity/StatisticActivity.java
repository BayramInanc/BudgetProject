package com.bayram.budgetproject.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.bayram.budgetproject.R;
import com.bayram.budgetproject.adapter.StatisticAdapter;

public class StatisticActivity extends AppCompatActivity {
    private ViewPager mViewPager;
    private StatisticAdapter mStatisticAdapter;
    private TabLayout mTabLayout;
    private Toolbar mToolbar;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistic);
        mTabLayout = (TabLayout) findViewById(R.id.my_tablayout);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("İSTATİSTİK");
        mViewPager = (ViewPager) findViewById(R.id.viewPager);
        mTabLayout.setupWithViewPager(mViewPager);
        mStatisticAdapter = new StatisticAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mStatisticAdapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
