package com.bayram.budgetproject;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

public class AdditionActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener, CommunicatableBetweenFragment {
    Toolbar mToolbar;
    public List<Fragment> mFragmentList = new ArrayList<>();
    public List<String> mFragmentTitleList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adding_activity);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        ViewPager mViewPager = (ViewPager) findViewById(R.id.my_viewpager);
        setupViewPager(mViewPager);
        TabLayout mTabLayout = (TabLayout) findViewById(R.id.my_tablayout);
        mTabLayout.setupWithViewPager(mViewPager);
        mViewPager.addOnPageChangeListener(this);
    }

    private void setupViewPager(ViewPager mViewPager) {
        MyViewPagerAdapter myViewPagerAdapter = new MyViewPagerAdapter(getSupportFragmentManager());
        myViewPagerAdapter.addFragment(new IncomeAdditionFragment(), "GELİR");
        myViewPagerAdapter.addFragment(new OutcomeAdditionFragment(), "GİDER");
        mViewPager.setAdapter(myViewPagerAdapter);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {


    }

    @Override
    public void onPageSelected(int position) {
        mToolbar.setTitle(mFragmentTitleList.get(position));
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


    public class MyViewPagerAdapter extends FragmentStatePagerAdapter {


        public MyViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);

        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

}
