package com.bayram.budgetproject;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import io.realm.Realm;
import io.realm.RealmResults;

import static com.bayram.budgetproject.Stuff.getTotalPriceForTheDay;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener, CommunicatableBetweenActivityAndFragment {

    public static RecyclerView mRecyclerView;
    public static MyRecyclierViewAdapter myRecyclierViewAdapter;
    public RealmResults<Category> mRealmResults;
    static AppCompatTextView mTotalPrice;
    private static int mYear = Constants.THIS_YEAR;
    private static int mMonthOfYear = Constants.THIS_MONTH;
    private static int mDayOfMonth = Constants.TODAY;
    Realm mRealmDatabase;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mRealmDatabase = Realm.getDefaultInstance();
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle("BÜTÇE DENGESİ");

        setSupportActionBar(mToolbar);
        ImageButton mImageButton = (ImageButton) findViewById(R.id.calendar_image_button);
        mImageButton.setOnClickListener(this);
//        CoordinatorLayout mCoordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinator_layout);
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mRecyclerView.setHasFixedSize(true);
        mRealmResults = mRealmDatabase.where(Category.class).equalTo("mStuff." + Constants.STRING_DAY, Constants.TODAY).equalTo("mStuff." + Constants.STRING_MONTH, Constants.THIS_MONTH).equalTo("mStuff." + Constants.STRING_YEAR, Constants.THIS_YEAR).findAll();

        mTotalPrice = (AppCompatTextView) findViewById(R.id.total_price);
        mTotalPrice.setText(getTotalPriceForTheDay(Constants.THIS_YEAR, Constants.THIS_MONTH, Constants.TODAY));

        myRecyclierViewAdapter = new MyRecyclierViewAdapter(mRealmResults, this);
        mRecyclerView.setAdapter(myRecyclierViewAdapter);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent = new Intent(getBaseContext(), AdditionActivity.class);
                startActivity(mIntent);



            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem menuItem = menu.findItem(R.id.statistic_screen);

        if (menuItem != null) {
            tintMenuIcon(HomeActivity.this, menuItem, android.R.color.white);
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if(id==R.id.statistic_screen){
            Intent mIntent =  new Intent(this,StatisticActivity.class);

            startActivity(mIntent);
        }


        //noinspection SimplifiableIfStatement

        return super.onOptionsItemSelected(item);
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("bayram", "Home activity destroy\n");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.calendar_image_button:
                DatePickerFragment mDatePickerFragment = DatePickerFragment.newInstance(-1);
                mDatePickerFragment.show(getSupportFragmentManager(), "timePicker");
                Log.d("bayram", "HomeActivity OnCLick metodu case R.id.calendar_image_button'li kısımdayız! ");


        }

    }
    public static void changeTotalPrice(){
        mTotalPrice.setText(Stuff.getTotalPriceForTheDay(mYear, mMonthOfYear, mDayOfMonth));
///Adasdsadsa
    }

    @Override
    public void onBackPressed() {
       finish();
    }

    @Override
    public void changeDisplayingHome(int year, int monthOfYear, int dayOfMonth) {
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
}
