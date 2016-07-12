package com.bayram.budgetproject;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.util.Log;
import android.view.View;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link IncomeAdditionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class IncomeAdditionFragment extends AdditionFragment {
      static AppCompatTextView mDateTextView;
    AppCompatButton okButton;
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        ArrayAdapter<CharSequence> mCategoryAdapter = ArrayAdapter.createFromResource(getContext(), R.array.category, android.R.layout.simple_spinner_dropdown_item);
//        ArrayAdapter<CharSequence> mSubCategoryAdapter = ArrayAdapter.createFromResource(getContext(), R.array.subCategory, android.R.layout.simple_spinner_dropdown_item);
//        categorySpinner = (AppCompatSpinner) view.findViewById(R.id.categorySpinner);
//        categorySpinner.setAdapter(mCategoryAdapter);
//        explanationTextInputLayout = (TextInputLayout) view.findViewById(R.id.explanation);
//        shortNoteTextInputLayout = (TextInputLayout) view.findViewById(R.id.shortNote);
//        priceTextInputLayout = (TextInputLayout) view.findViewById(R.id.price);
        mDateTextView = (AppCompatTextView) view.findViewById(R.id.date_text_view);
        mDateTextView.setText(Constants.TODAY + "  " + Constants.THIS_MONTH + "  " + Constants.THIS_YEAR);
        okButton = (AppCompatButton) view.findViewById(R.id.ok);
        okButton.setOnClickListener(this);

//        mDateImageButton = (AppCompatButton) view.findViewById(R.id.date_button);
//        okButton = (AppCompatButton) view.findViewById(R.id.ok);
//        cancelButton = (AppCompatButton) view.findViewById(R.id.cancel);
//        okButton.setOnClickListener(this);
//        mDateImageButton.setOnClickListener(this);
//        categorySpinner.setOnItemSelectedListener(this);
    }



    @Override
    public void onClick(View v) {
        DatePickerFragment mDatePickerFragment = DatePickerFragment.newInstance(0);
        mOnClick(mDatePickerFragment, v, false);
    }
    public void changeTextView(int year, int monthOfYear, int dayOfMonth) {
        mDateTextView.setText(String.valueOf(dayOfMonth) + "  " + String.valueOf(monthOfYear) + "  " + String.valueOf(year));
        Log.d("bayram", "IncomeAdditionFragment Changetextviewa girdik.");
    }



}

