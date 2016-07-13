package com.bayram.budgetproject.fragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.widget.DatePicker;

import com.bayram.budgetproject.utility.Constants;

/**
 * Created by Bayram-PC on 11.2.2016.
 */
public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {
    private static int year = Constants.THIS_YEAR;
    private static int month = Constants.THIS_MONTH;
    private static int day = Constants.TODAY;
    public  int whichButton = -1;
    private HomeFragment.CommunicatableBetweenHomeFragmentAndDataPickerFragment mCommunicatableBetweenHomeFragmentAndDataPickerFragment;
    private AdditionFragment.CommunicatableBetweenIncomeAndOutcomeFragmentWithDataPickerFragment mCommunicatableBetweenIncomeAndOutcomeFragmentWithDataPickerFragment;

    public int getMonth() {

        Log.d("bayram", "DatePickerFragment getmonthdayız.");
        return month;
    }

    public int getDay() {
        Log.d("bayram", "DatePickerFragment getDAydeyiz.");

        return day;
    }

    public int getYear() {
        Log.d("bayram", "DatePickerFragment getYeardayız..");

        return year;
    }

    public void setYear(int year) {
        Log.d("bayram", "DatePickerFragment setYeardayız..");

        this.year = year;
    }

    public void setMonth(int month) {
        Log.d("bayram", "DatePickerFragment setMonthdayuz..");

        this.month = month;
    }

    public void setDay(int day) {
        Log.d("bayram", "DatePickerFragment setDAydayiz..");

        this.day = day;
    }

    public DatePickerFragment() {
        whichButton = -1;
        Log.d("bayram", "Datepicker fragment empty constructurdayız.");
    }


    public static DatePickerFragment newInstance(int whichButton) {
        DatePickerFragment myFragment = new DatePickerFragment();
        Log.d("bayram", "DatePickerFragment new instance int whichButton metodundayız.");
        Bundle args = new Bundle();
        args.putInt("year", year);
        args.putInt("month", month);
        args.putInt("year", year);
        args.putInt("whichButton", whichButton);

        myFragment.setArguments(args);
        return myFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("bayram", "DatePickerFragment onCreate metodundayız.");
        year = getArguments().getInt("year", year);
        month = getArguments().getInt("year", month);
        day = getArguments().getInt("year", day);
        whichButton = getArguments().getInt("whichButton", whichButton);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Log.d("bayram", "DatePickerFragment onCreateDialog metodundayız.");
        return new DatePickerDialog(getActivity(), this, Constants.THIS_YEAR, Constants.THIS_MONTH - 1, Constants.TODAY);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d("bayram", "DatePickerFragment onActivityCreated metodundayız.");
        if (getActivity() instanceof HomeFragment.CommunicatableBetweenHomeFragmentAndDataPickerFragment) {
            mCommunicatableBetweenHomeFragmentAndDataPickerFragment = (HomeFragment.CommunicatableBetweenHomeFragmentAndDataPickerFragment) getActivity();
            Log.d("bayram", "DatePickerFragment OnactivityCreated if (getActivity() instanceof CommunicatableBetweenFragment)'li kısımdayız! ");

        } else if (getActivity() instanceof AdditionFragment.CommunicatableBetweenIncomeAndOutcomeFragmentWithDataPickerFragment) {

            mCommunicatableBetweenIncomeAndOutcomeFragmentWithDataPickerFragment = (AdditionFragment.CommunicatableBetweenIncomeAndOutcomeFragmentWithDataPickerFragment) getActivity();
            Log.d("bayram", "DatePickerFragment OnactivityCreatedelse if (getActivity() instanceof CommunicatableBetweenActivityAndFragment)'li kısımdayız! ");

        }
    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        // whichButton == 0 ||whichButton ==1 it means that we use picker for addingActivity.
        //Also we are getting information from addition....
        if (whichButton == 0 || whichButton == 1) {
            SharedPreferences mSharedPreferences = getActivity().getSharedPreferences("key", Context.MODE_PRIVATE);
            SharedPreferences.Editor mEditor = mSharedPreferences.edit();
            if(whichButton==1){
                Log.d("bayram","outcomefragmentte ekleme whichbutton bir outcome true yapıldı.");
            }
            mEditor.putBoolean("pressed", true);
            mEditor.apply();
            setDay(dayOfMonth);
            setMonth(monthOfYear + 1);
            setYear(year);
            Log.d("bayram", "DatePickerFragment OndateSet if (whichButton == 0 || whichButton == 1)'li koşuldayız!");
            mCommunicatableBetweenIncomeAndOutcomeFragmentWithDataPickerFragment.sendData(year, monthOfYear + 1, dayOfMonth, whichButton);

        }
        //WE use this else part for displaying income-outcome in home activity.
        else if (whichButton == -1) {
            mCommunicatableBetweenHomeFragmentAndDataPickerFragment.changeDisplayingHome(year, monthOfYear + 1, dayOfMonth);
            Log.d("bayram", "DatePickerFragment OndateSet else if(whichButton==-1) 'li koşuldayız!");

        }


    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d("bayram","DataPicker fragment onSaveInstanceState metodundayız!");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d("bayram","DataPicker fragment onDestroyView metodundayız!");

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("bayram","DataPicker fragment onDestroy metodundayız!");

    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("bayram","DataPicker fragment onPause metodundayız!");

    }
}
