package com.bayram.budgetproject.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.bayram.budgetproject.R;
import com.bayram.budgetproject.adapter.StatisticAdapter;

import info.hoang8f.android.segmented.SegmentedGroup;

public class StatisticActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener{
    private ViewPager mViewPager;
    private StatisticAdapter mStatisticAdapter;
    private TabLayout mTabLayout;
    private Toolbar mToolbar;
    private SegmentedGroup mSegmentedGroup;
    private Spinner mMonthSpinner, mYearSpinner;

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
        mSegmentedGroup = (SegmentedGroup) findViewById(R.id.segmentedGroup);
        mSegmentedGroup.setOnCheckedChangeListener(this);
        mMonthSpinner = (Spinner) findViewById(R.id.spinner_month);
        mYearSpinner = (Spinner) findViewById(R.id.spinner_year);
        ArrayAdapter<CharSequence> mMonthAdapter = ArrayAdapter.createFromResource(this,R.array.string_array_month,R.layout.support_simple_spinner_dropdown_item);
        mMonthSpinner.setAdapter(mMonthAdapter);
        mMonthSpinner.setVisibility(View.GONE);
        ArrayAdapter<CharSequence> mYearArrayAdapter = ArrayAdapter.createFromResource(this,R.array.string_array_year,R.layout.support_simple_spinner_dropdown_item);
        mYearSpinner.setAdapter(mYearArrayAdapter);
        mYearSpinner.setVisibility(View.GONE);



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

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int id) {
        switch (id){
             case R.id.radio_month:
                 mMonthSpinner.setVisibility(View.VISIBLE);
                break;
            case R.id.radio_year:
                mYearSpinner.setVisibility(View.VISIBLE);
                break;

        }
    }


}
