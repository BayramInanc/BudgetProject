package com.bayram.budgetproject;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by mac on 2.07.2016.
 */

public class StatisticAdapter extends FragmentPagerAdapter {

    private final int FRAGMENT_NUMBER = 2;
    private final String[] mTitle = {"GElİR ","GİDER"};

    public StatisticAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return StatisticIncomeFragment.newInstance();
            case 1:
                return StatisticOutcomeFragment.newInstance();
        }
        return null;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitle[position];
    }

    @Override
    public int getCount() {
        return FRAGMENT_NUMBER;
    }
}
