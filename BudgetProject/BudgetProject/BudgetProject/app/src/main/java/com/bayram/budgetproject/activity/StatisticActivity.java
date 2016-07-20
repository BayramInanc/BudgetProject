package com.bayram.budgetproject.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;

import com.bayram.budgetproject.R;
import com.bayram.budgetproject.adapter.StatisticAdapter;
import com.bayram.budgetproject.utility.Constants;
import com.github.lguipeng.library.animcheckbox.AnimCheckBox;
import com.jaredrummler.materialspinner.MaterialSpinner;

public class StatisticActivity extends AppCompatActivity implements View.OnClickListener, MaterialSpinner.OnItemSelectedListener {
    private ViewPager mViewPager;
    private StatisticAdapter mStatisticAdapter;
    private TabLayout mTabLayout;
    private Toolbar mToolbar;
    private AppCompatTextView mMonthAppCompatTextView, mYearAppCompatTextView, mMonthSelectedAppCompatTextView, mYearSelectedAppCompatTextView;
    private MaterialSpinner mMonthSpinner, mYearSpinner;
    private AnimCheckBox mMonthCheckedBox, mYearCheckedBox;
    private String mSelectedSpinnerItemForMonth;
    private String mSelectedSpinnerItemForYear;
    private String[] MONTHS;
    private String[] YEARS;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistic);

        mMonthSelectedAppCompatTextView = (AppCompatTextView) findViewById(R.id.textView_selected_date_month);
        mYearSelectedAppCompatTextView = (AppCompatTextView) findViewById(R.id.textView_selected_date_year);
        MONTHS = getResources().getStringArray(R.array.string_array_month);
        YEARS = getResources().getStringArray(R.array.string_array_year);
        mTabLayout = (TabLayout) findViewById(R.id.my_tablayout);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("İSTATİSTİK");
        mViewPager = (ViewPager) findViewById(R.id.viewPager);
        mTabLayout.setupWithViewPager(mViewPager);
        mStatisticAdapter = new StatisticAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mStatisticAdapter);
        mMonthAppCompatTextView = (AppCompatTextView) findViewById(R.id.textView_month);
        mSelectedSpinnerItemForMonth = MONTHS[Constants.THIS_MONTH];
        mSelectedSpinnerItemForYear = String.valueOf(Constants.THIS_YEAR);
        mYearAppCompatTextView = (AppCompatTextView) findViewById(R.id.textView_year);

        mMonthSpinner = (MaterialSpinner) findViewById(R.id.spinner_month);


        mYearSpinner = (MaterialSpinner) findViewById(R.id.spinner_year);
        ArrayAdapter<CharSequence> mMonthAdapter = ArrayAdapter.createFromResource(this, R.array.string_array_month, R.layout.support_simple_spinner_dropdown_item);

        mMonthSpinner.setOnItemSelectedListener(this);
        mYearSpinner.setOnItemSelectedListener(this);
        mMonthCheckedBox = (AnimCheckBox) findViewById(R.id.checkbox_month);
        mYearCheckedBox = (AnimCheckBox) findViewById(R.id.checkbox_year);
        mMonthCheckedBox.setChecked(false);
        mYearCheckedBox.setChecked(false);
        ArrayAdapter<CharSequence> mYearArrayAdapter = ArrayAdapter.createFromResource(this, R.array.string_array_year, R.layout.support_simple_spinner_dropdown_item);
        MONTHS = getResources().getStringArray(R.array.string_array_month);
        YEARS = getResources().getStringArray(R.array.string_array_year);
        mMonthSpinner.setItems(MONTHS);
        mYearSpinner.setItems(YEARS);

        mMonthSpinner.setVisibility(View.GONE);
        mYearSpinner.setVisibility(View.GONE);

        mMonthCheckedBox.setOnClickListener(this);
        mYearCheckedBox.setOnClickListener(this);

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
    public void onClick(View view) {
        boolean checked = ((AnimCheckBox) view).isChecked();
        boolean animation = true;
        switch (view.getId()) {
            case R.id.checkbox_month:
                if (checked) {
                    mMonthCheckedBox.setChecked(false, animation);
                    mMonthAppCompatTextView.setVisibility(View.GONE);
                    mMonthSelectedAppCompatTextView.setText("");
                    mMonthSpinner.setVisibility(View.GONE);
                } else {
                    mMonthCheckedBox.setChecked(true);
                    mMonthAppCompatTextView.setText(mSelectedSpinnerItemForMonth);
                    mMonthAppCompatTextView.setVisibility(View.VISIBLE);
                    mMonthSpinner.setVisibility(View.VISIBLE);
                    mMonthSelectedAppCompatTextView.setText(mSelectedSpinnerItemForMonth);
                }
                break;
            case R.id.checkbox_year:
                if (checked) {
                    mYearAppCompatTextView.setVisibility(View.GONE);
                    mYearSelectedAppCompatTextView.setText("");
                    mYearCheckedBox.setChecked(false, animation);
                    mYearSpinner.setVisibility(View.GONE);
                } else {
                    mYearCheckedBox.setChecked(true);
                    mYearSelectedAppCompatTextView.setText(mSelectedSpinnerItemForYear);
                    mYearAppCompatTextView.setVisibility(View.VISIBLE);
                    mYearAppCompatTextView.setText(mSelectedSpinnerItemForYear);
                    mYearSpinner.setVisibility(View.VISIBLE);
                }
                break;
        }
    }


    @Override
    public void onItemSelected(MaterialSpinner parent, int position, long id, Object item) {
        switch (parent.getId()) {
            case R.id.spinner_month:
                mSelectedSpinnerItemForMonth = item.toString();
                mMonthAppCompatTextView.setText(mSelectedSpinnerItemForMonth);
                mMonthSelectedAppCompatTextView.setText(mSelectedSpinnerItemForMonth);

                break;
            case R.id.spinner_year:
                mSelectedSpinnerItemForYear = item.toString();
                mYearAppCompatTextView.setText(mSelectedSpinnerItemForYear);
                mYearSelectedAppCompatTextView.setText(mSelectedSpinnerItemForYear);
                break;

        }
    }
}
