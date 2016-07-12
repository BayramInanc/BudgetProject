package com.bayram.budgetproject;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatSpinner;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import io.realm.Realm;

/**
 * Created by Bayram-PC on 28.2.2016.
 */
public class AdditionFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    AppCompatSpinner categorySpinner;

    TextInputLayout explanationTextInputLayout;
    TextInputLayout shortNoteTextInputLayout;
    TextInputLayout priceTextInputLayout;
    Realm mRealmDatabase;
    AppCompatButton cancelButton;
    AppCompatButton mDateImageButton;
    String category;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ArrayAdapter<CharSequence> mCategoryAdapter = ArrayAdapter.createFromResource(getContext(), R.array.category, android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter<CharSequence> mSubCategoryAdapter = ArrayAdapter.createFromResource(getContext(), R.array.subCategory, android.R.layout.simple_spinner_dropdown_item);
        mRealmDatabase = Realm.getDefaultInstance();
        categorySpinner = (AppCompatSpinner) view.findViewById(R.id.categorySpinner);
        categorySpinner.setAdapter(mCategoryAdapter);
        explanationTextInputLayout = (TextInputLayout) view.findViewById(R.id.explanation);
        shortNoteTextInputLayout = (TextInputLayout) view.findViewById(R.id.shortNote);
        priceTextInputLayout = (TextInputLayout) view.findViewById(R.id.price);
        mDateImageButton = (AppCompatButton) view.findViewById(R.id.date_button);
        cancelButton = (AppCompatButton) view.findViewById(R.id.cancel);

        mDateImageButton.setOnClickListener(this);
        categorySpinner.setOnItemSelectedListener(this);
    }

    public AdditionFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment IncomeAdditionFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static IncomeAdditionFragment newInstance(String param1, String param2) {
        IncomeAdditionFragment fragment = new IncomeAdditionFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_addition, container, false);
    }


    @Override
    public void onClick(View v) {
    }

    public void mOnClick(final DatePickerFragment mDatePickerFragment, View v, final boolean isOutcome) {

        switch (v.getId()) {

            case R.id.date_button:
                Log.d("bayram", "Onclick R.id.date_button ye girdik");
                mDatePickerFragment.show(getActivity().getSupportFragmentManager(), "timePicker");
                break;
            case R.id.ok:
                SharedPreferences mSharedPreferences = getActivity().getSharedPreferences("key", Context.MODE_PRIVATE);
                SharedPreferences.Editor mEditor = mSharedPreferences.edit();
                boolean isPressed = mSharedPreferences.getBoolean("pressed", false);

                Log.d("bayram", "Onclick R.id.ok ye girdik");
                final String explanation = explanationTextInputLayout.getEditText().getText().toString();
                final String shortNote = shortNoteTextInputLayout.getEditText().getText().toString();
                final String price = priceTextInputLayout.getEditText().getText().toString();

                //Gelen price Outcome fragmentten geliyor. Bu yüzden, price negatif değere sahip.

                //Eğer bugünün dışında bir tarih belirlenmişse isPressed true olur.
                if (isPressed) {
                    mRealmDatabase.executeTransaction(new Realm.Transaction() {
                        @Override
                        public void execute(Realm realm) {
                            int price2 = Integer.parseInt(price);
                            Category mCategory = mRealmDatabase.createObject(Category.class);
                            mCategory.setId(Stuff.getId() + System.currentTimeMillis());
                            mCategory.setCategoryName(category);
                            Stuff mStuff = mRealmDatabase.createObject(Stuff.class);
                            if (isOutcome) {
                                price2 = -price2;
                            }
                            mStuff.setId(Stuff.getId() +System.currentTimeMillis());
                            mStuff.setPrice(price2);
                            mStuff.setExplanation(explanation);
                            mStuff.setShortnote(shortNote);
                            mStuff.setDay(mDatePickerFragment.getDay());
                            mStuff.setMonth(mDatePickerFragment.getMonth());
                            mStuff.setYear(mDatePickerFragment.getYear());
                            mCategory.getmStuff().add(mStuff);

                        }
                    });
                    mEditor.putBoolean("pressed", false);
                    mEditor.apply();
                } else {
                    mRealmDatabase.executeTransaction(new Realm.Transaction() {
                        @Override
                        public void execute(Realm realm) {
                            int price2 = Integer.valueOf(price);
                            Category mCategory = mRealmDatabase.createObject(Category.class);
                            mCategory.setCategoryName(category);
                            mCategory.setId(Stuff.getId() + System.currentTimeMillis());

                            Stuff mStuff = mRealmDatabase.createObject(Stuff.class);
                            if (isOutcome) {
                                price2 = -price2;
                            }
                            mStuff.setId(Stuff.getId() + System.currentTimeMillis());
                            mStuff.setPrice(price2);
                            mStuff.setExplanation(explanation);
                            mStuff.setShortnote(shortNote);
                            mStuff.setDay(Constants.TODAY);
                            mStuff.setMonth(Constants.THIS_MONTH);
                            mStuff.setYear(Constants.THIS_YEAR);
                            mCategory.getmStuff().add(mStuff);


                        }
                    });


//                    Database mDatabase = new Database(category, price, explanation, shortNote, Constants.TODAY
//                            , Constants.THIS_MONTH, Constants.THIS_YEAR);
//                    mDatabase.save();
                }
                Intent mIntent = new Intent(getContext(), HomeActivity.class);
                startActivity(mIntent);
                break;

        }

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        category = parent.getItemAtPosition(position).toString();
        Log.d("bayram", "OnITemselected\n");
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
