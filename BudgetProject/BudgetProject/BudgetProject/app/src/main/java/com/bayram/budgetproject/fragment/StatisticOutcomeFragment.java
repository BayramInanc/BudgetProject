package com.bayram.budgetproject.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bayram.budgetproject.R;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link StatisticOutcomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StatisticOutcomeFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    public StatisticOutcomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *

     * @return A new instance of fragment StatisticOutcomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static StatisticOutcomeFragment newInstance() {
        StatisticOutcomeFragment fragment = new StatisticOutcomeFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_statistic_outcome, container, false);
    }

}
