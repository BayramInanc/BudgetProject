package com.bayram.budgetproject.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.bayram.budgetproject.R;
import com.bayram.budgetproject.activity.HomeActivity;
import com.bayram.budgetproject.model.Category;
import com.bayram.budgetproject.model.IncomeAdditionType;
import com.bayram.budgetproject.model.OutcomeAdditionType;
import com.bayram.budgetproject.model.Stuff;
import com.bayram.budgetproject.utility.Constants;

import info.hoang8f.android.segmented.SegmentedGroup;
import io.realm.Realm;

/**
 * Created by Bayram-PC on 28.2.2016.
 */
public class AdditionFragment extends Fragment implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {

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
    private SegmentedGroup mSegmentedGroup;

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mRealmDatabase = Realm.getDefaultInstance();
        explanationTextInputLayout = (TextInputLayout) view.findViewById(R.id.explanation);
        shortNoteTextInputLayout = (TextInputLayout) view.findViewById(R.id.shortNote);
        priceTextInputLayout = (TextInputLayout) view.findViewById(R.id.price);
        mDateImageButton = (AppCompatButton) view.findViewById(R.id.date_button);
        cancelButton = (AppCompatButton) view.findViewById(R.id.cancel);
        mSegmentedGroup = (SegmentedGroup) view.findViewById(R.id.segmentedGroup);
        mSegmentedGroup.setOnCheckedChangeListener(this);
        mDateImageButton.setOnClickListener(this);

    }

    public AdditionFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment IncomeAdditionFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AdditionFragment newInstance() {
        return new AdditionFragment();
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
                Toast.makeText(getActivity(), "Click Tıklandı!", Toast.LENGTH_SHORT).show();
                break;
            case R.id.cancel:
                getActivity().finish();
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
                            mCategory.setId(Category.getId());
                            mCategory.setCategoryName(category);

                            Stuff mStuff = mRealmDatabase.createObject(Stuff.class);
                            if (isOutcome) {
                                price2 = -price2;
                            }
                            mStuff.setId(Stuff.getId());
                            mStuff.setPrice(price2);
                            mStuff.setExplanation(explanation);
                            mStuff.setShortnote(shortNote);
                            mStuff.setDay(mDatePickerFragment.getDay());
                            mStuff.setMonth(mDatePickerFragment.getMonth());
                            mStuff.setYear(mDatePickerFragment.getYear());
                            mCategory.getmStuff().add(mStuff);

                            if (isOutcome) {
                                OutcomeAdditionType mOutcomeAdditionType = mRealmDatabase.createObject(OutcomeAdditionType.class);
                                mOutcomeAdditionType.setId(OutcomeAdditionType.getId());
                                mOutcomeAdditionType.getmCategory().add(mCategory);
                            } else {
                                IncomeAdditionType mIncomeAdditionType = mRealmDatabase.createObject(IncomeAdditionType.class);
                                mIncomeAdditionType.setId(IncomeAdditionType.getId());
                                mIncomeAdditionType.getmCategory().add(mCategory);
                            }

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
                            mCategory.setId(Category.getId());
                            Toast.makeText(getContext(), "category id " + String.valueOf(Category.getId() - 1), Toast.LENGTH_SHORT).show();

                            Stuff mStuff = mRealmDatabase.createObject(Stuff.class);
                            if (isOutcome) {
                                price2 = -price2;
                            }
                            mStuff.setId(Stuff.getId());
                            mStuff.setPrice(price2);
                            mStuff.setExplanation(explanation);
                            mStuff.setShortnote(shortNote);
                            mStuff.setDay(Constants.TODAY);
                            mStuff.setMonth(Constants.THIS_MONTH);
                            mStuff.setYear(Constants.THIS_YEAR);

                            if (isOutcome) {
                                mCategory.setType(1);
                                mCategory.getmStuff().add(mStuff);
                                OutcomeAdditionType mOutcomeAdditionType = mRealmDatabase.createObject(OutcomeAdditionType.class);
                                mOutcomeAdditionType.setId(OutcomeAdditionType.getId());
                                mOutcomeAdditionType.getmCategory().add(mCategory);
                            } else {
                                mCategory.setType(0);
                                mCategory.getmStuff().add(mStuff);
                                IncomeAdditionType mIncomeAdditionType = mRealmDatabase.createObject(IncomeAdditionType.class);
                                mIncomeAdditionType.setId(IncomeAdditionType.getId());
                                mIncomeAdditionType.getmCategory().add(mCategory);
                            }

                        }
                    });


                }

                Intent mIntent = new Intent(getContext(), HomeActivity.class);
                mIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(mIntent);

                break;

        }

    }


    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int id) {
        switch (id) {
            case R.id.radio1:
                category = "Kira";
                break;
            case R.id.radio2:
                category = "Fatura";
                break;
            case R.id.radio3:
                category = "Ulaşım";
                break;
            case R.id.radio4:
                category = "Sağlık";
                break;
            case R.id.radio5:
                category = "Gıda";
                break;
            case R.id.radio6:
                category = "Giyim";
                break;
            default:
                break;

        }
    }

    public void sendData(int year, int monthOfYear, int dayOfMonth, int whichButton) {
        if (whichButton == 0) {
            IncomeAdditionFragment mIncomeAdditionFragment = (IncomeAdditionFragment) IncomeAdditionFragment.newInstance();
            mIncomeAdditionFragment.changeTextView(year, monthOfYear, dayOfMonth);
        } else if (whichButton == 1) {
            OutcomeAdditionFragment mOutcomeAdditionFragment = (OutcomeAdditionFragment) OutcomeAdditionFragment.newInstance();
            mOutcomeAdditionFragment.changeTextView(year, monthOfYear, dayOfMonth);
        }
    }

    public interface CommunicatableBetweenIncomeAndOutcomeFragmentWithDataPickerFragment {
        void sendData(int year, int monthOfYear, int dayOfMonth, int whichButton);
    }
}
