package com.bayram.budgetproject.fragment;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bayram.budgetproject.R;
import com.bayram.budgetproject.model.OutcomeAdditionType;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;


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
        View mView = inflater.inflate(R.layout.fragment_statistic_income, container, false);

        PieChart mPieChart = (PieChart) mView.findViewById(R.id.piechart);

        mPieChart.addPieSlice(new PieModel("Kira", OutcomeAdditionType.getTotalPriceForCategoryAsPercentage("Kira"), Color.parseColor("#C62828")));
        mPieChart.addPieSlice(new PieModel("Fatura", OutcomeAdditionType.getTotalPriceForCategoryAsPercentage("Fatura"), Color.parseColor("#C6FF00")));
        mPieChart.addPieSlice(new PieModel("Ulaşım", OutcomeAdditionType.getTotalPriceForCategoryAsPercentage("Ulaşım"), Color.parseColor("#4527A0")));
        mPieChart.addPieSlice(new PieModel("Sağlık", OutcomeAdditionType.getTotalPriceForCategoryAsPercentage("Sağlık"), Color.parseColor("#FF6F00")));
        mPieChart.addPieSlice(new PieModel("Gıda", OutcomeAdditionType.getTotalPriceForCategoryAsPercentage("Gıda"), Color.parseColor("#558B2F")));
        mPieChart.addPieSlice(new PieModel("Giyim", OutcomeAdditionType.getTotalPriceForCategoryAsPercentage("Giyim"), Color.parseColor("#EF6C00")));

        ///////asdasadlklkasndlkn//
        //sdsad
        mPieChart.startAnimation();

        return mView;
    }

}
