package com.bayram.budgetproject.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.bayram.budgetproject.R;
import com.bayram.budgetproject.fragment.AdditionFragment;
import com.bayram.budgetproject.fragment.HomeFragment;
import com.bayram.budgetproject.utility.Constants;

import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity implements HomeFragment.CommunicatableBetweenHomeFragmentAndDataPickerFragment, AdditionFragment.CommunicatableBetweenIncomeAndOutcomeFragmentWithDataPickerFragment {

    public Toolbar mToolbar;
    private static int mYear = Constants.THIS_YEAR;
    private static int mMonthOfYear = Constants.THIS_MONTH;
    private static int mDayOfMonth = Constants.TODAY;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_home);
        ButterKnife.bind(this);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        if(savedInstanceState==null){
            FragmentManager mFragmentManager = getSupportFragmentManager();
            FragmentTransaction mFragmentTransaction = mFragmentManager.beginTransaction();
            mFragmentTransaction.replace(R.id.container, HomeFragment.newInstance());
            mFragmentTransaction.commit();
        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override

    public void changeDisplayingHome(int year, int monthOfYear, int dayOfMonth) {
        HomeFragment mHomeFragment = HomeFragment.newInstance();
        mHomeFragment.changeDisplayingHome(year, monthOfYear, dayOfMonth);

    }

    @Override
    public void sendData(int year, int monthOfYear, int dayOfMonth, int whichButton) {
        AdditionFragment mAdditionFragment = AdditionFragment.newInstance();
        mAdditionFragment.sendData(year, monthOfYear, dayOfMonth, whichButton);

    }
}
