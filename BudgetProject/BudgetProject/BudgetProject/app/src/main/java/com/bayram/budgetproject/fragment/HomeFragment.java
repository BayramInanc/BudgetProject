package com.bayram.budgetproject.fragment;


import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.bayram.budgetproject.R;
import com.bayram.budgetproject.activity.AdditionActivity;
import com.bayram.budgetproject.activity.HomeActivity;
import com.bayram.budgetproject.activity.StatisticActivity;
import com.bayram.budgetproject.adapter.HomeRecyclierViewAdapter;
import com.bayram.budgetproject.model.Category;
import com.bayram.budgetproject.model.Stuff;
import com.bayram.budgetproject.utility.Constants;

import io.realm.Realm;
import io.realm.RealmResults;

import static com.bayram.budgetproject.model.Stuff.getTotalPriceForTheDay;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment implements View.OnClickListener, HomeRecyclierViewAdapter.OnCardClickCallback {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    public static RecyclerView mRecyclerView;
    public static HomeRecyclierViewAdapter myRecyclierViewAdapter;
    public RealmResults<Category> mRealmResults;
    static AppCompatTextView mTotalPrice;
    private static int mYear = Constants.THIS_YEAR;
    private static int mMonthOfYear = Constants.THIS_MONTH;
    private static int mDayOfMonth = Constants.TODAY;
    Realm mRealmDatabase;

    private Context mContext;

    // TODO: Rename and change types of parameters


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View mView = inflater.inflate(R.layout.fragment_home, container, false);
        mRealmDatabase = Realm.getDefaultInstance();
        ImageButton mImageButton = (ImageButton) mView.findViewById(R.id.calendar_image_button);
        mImageButton.setOnClickListener(this);
//        CoordinatorLayout mCoordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinator_layout);
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(mContext);
        mRecyclerView = (RecyclerView) mView.findViewById(R.id.my_recycler_view);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mRecyclerView.setHasFixedSize(true);
        mRealmResults = mRealmDatabase.where(Category.class).equalTo("mStuff." + Constants.STRING_DAY, Constants.TODAY).equalTo("mStuff." + Constants.STRING_MONTH, Constants.THIS_MONTH).equalTo("mStuff." + Constants.STRING_YEAR, Constants.THIS_YEAR).findAll();
        setHasOptionsMenu(true);
        mTotalPrice = (AppCompatTextView) mView.findViewById(R.id.total_price);
        mTotalPrice.setText(getTotalPriceForTheDay(Constants.THIS_YEAR, Constants.THIS_MONTH, Constants.TODAY));

        myRecyclierViewAdapter = new HomeRecyclierViewAdapter(mRealmResults, getActivity(), this);
        mRecyclerView.setAdapter(myRecyclierViewAdapter);


        FloatingActionButton fab = (FloatingActionButton) mView.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent = new Intent(getActivity(), AdditionActivity.class);
                startActivity(mIntent);


            }
        });
        return mView;

    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu);
        MenuItem menuItem = menu.findItem(R.id.statistic_screen);

        if (menuItem != null) {
            tintMenuIcon(getActivity(), menuItem, android.R.color.white);
        }

        super.onCreateOptionsMenu(menu, menuInflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.statistic_screen) {
            Intent mIntent = new Intent(getActivity(), StatisticActivity.class);

            startActivity(mIntent);
        }


        //noinspection SimplifiableIfStatement

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.calendar_image_button:
                DatePickerFragment mDatePickerFragment = DatePickerFragment.newInstance(-1);
                mDatePickerFragment.show(getActivity().getSupportFragmentManager(), "timePicker");
                Log.d("bayram", "HomeActivity OnCLick metodu case R.id.calendar_image_button'li kısımdayız! ");


        }

    }

    public static void changeTotalPrice() {
        mTotalPrice.setText(Stuff.getTotalPriceForTheDay(mYear, mMonthOfYear, mDayOfMonth));
    }



    public void changeDisplayingHome(int year, int monthOfYear, int dayOfMonth) {
        Realm mRealmDatabase = Realm.getDefaultInstance();
        RealmResults<Category> realmResults = mRealmDatabase.where(Category.class).equalTo("mStuff." + Constants.STRING_DAY, dayOfMonth).equalTo("mStuff." + Constants.STRING_MONTH, monthOfYear).equalTo("mStuff." + Constants.STRING_YEAR, year).findAll();
        mDayOfMonth = dayOfMonth;
        mYear = year;
        mMonthOfYear = monthOfYear;
        myRecyclierViewAdapter.updateRealmResult(realmResults);
        myRecyclierViewAdapter.notifyDataSetChanged();
        mRecyclerView.setAdapter(myRecyclierViewAdapter);
        mTotalPrice.setText(Stuff.getTotalPriceForTheDay(year, monthOfYear, dayOfMonth));
        Log.d("bayram", "HomeActivity changeDisplayingHome metodunun içine girdik!");

    }

    public static void tintMenuIcon(Context context, MenuItem item, @ColorRes int color) {
        Drawable normalDrawable = item.getIcon();
        Drawable wrapDrawable = DrawableCompat.wrap(normalDrawable);
        DrawableCompat.setTint(wrapDrawable, context.getResources().getColor(color));
        item.setIcon(wrapDrawable);
    }

    @Override
    public void onCardClicked(int position) {
        FragmentManager mFragmentManager = ((HomeActivity) mContext).getSupportFragmentManager();
        FragmentTransaction mFragmentTransaction = mFragmentManager.beginTransaction();
        mFragmentTransaction.replace(R.id.container, DetailScreenFragment.newInstance());
        mFragmentTransaction.commit();
        ((HomeActivity) mContext).mToolbar.setTitle("DETAY EKRANI");

    }
    public interface CommunicatableBetweenHomeFragmentAndDataPickerFragment{
        void changeDisplayingHome(int year, int monthOfYear, int dayOfMonth);
    }
}
