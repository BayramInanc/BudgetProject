package com.bayram.budgetproject.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.bayram.budgetproject.adapter.AdditionAdapter;
import com.bayram.budgetproject.interfaces.CommunicatableBetweenFragment;
import com.bayram.budgetproject.fragment.IncomeAdditionFragment;
import com.bayram.budgetproject.fragment.OutcomeAdditionFragment;
import com.bayram.budgetproject.R;

import java.util.ArrayList;
import java.util.List;

public class AdditionActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener, CommunicatableBetweenFragment {
    Toolbar mToolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adding_activity);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle("İŞLEM");
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ViewPager mViewPager = (ViewPager) findViewById(R.id.my_viewpager);
        setupViewPager(mViewPager);
        TabLayout mTabLayout = (TabLayout) findViewById(R.id.my_tablayout);
        mTabLayout.setupWithViewPager(mViewPager);
        mViewPager.addOnPageChangeListener(this);
    }

    private void setupViewPager(ViewPager mViewPager) {
        AdditionAdapter mAdditionAdapter = new AdditionAdapter(getSupportFragmentManager());
        mAdditionAdapter.addFragment(new IncomeAdditionFragment(), "GELİR");
        mAdditionAdapter.addFragment(new OutcomeAdditionFragment(), "GİDER");
        mViewPager.setAdapter(mAdditionAdapter);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {


    }

    @Override
    public void onPageSelected(int position) {
    }

    @Override
    public void onPageScrollStateChanged(int state) {


    }

    @Override
    public void sendData(int year, int monthOfYear, int dayOfMonth, int whichButton) {
        if (whichButton == 0) {
            IncomeAdditionFragment mIncomeFragment = new IncomeAdditionFragment();
            mIncomeFragment.changeTextView(year, monthOfYear, dayOfMonth);

        } else if (whichButton == 1) {
            OutcomeAdditionFragment mOutcomeFragment = new OutcomeAdditionFragment();
            mOutcomeFragment.changeTextView(year, monthOfYear, dayOfMonth);

        }


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }



}
