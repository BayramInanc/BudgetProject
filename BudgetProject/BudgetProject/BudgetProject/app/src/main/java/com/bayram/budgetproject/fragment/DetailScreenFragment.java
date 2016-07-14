package com.bayram.budgetproject.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bayram.budgetproject.R;
import com.bayram.budgetproject.activity.HomeActivity;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DetailScreenFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetailScreenFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private Context mContext;
    // TODO: Rename and change types of parameters


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    public DetailScreenFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment DetailScreenFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DetailScreenFragment newInstance() {
        return new DetailScreenFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ((HomeActivity) mContext).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        return inflater.inflate(R.layout.fragment_detail_screen, container, false);
    }


}
