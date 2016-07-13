package com.bayram.budgetproject.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bayram.budgetproject.R;
import com.bayram.budgetproject.fragment.HomeFragment;
import com.bayram.budgetproject.model.Category;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;


/**
 * Created by Bayram-PC on 3.2.2016.
 */
public class HomeRecyclierViewAdapter extends RecyclerView.Adapter<HomeRecyclierViewAdapter.MyViewHolder> {
    RealmResults<Category> mRealmResults;
    private ImageView categoryImageView;
    private Realm mRealm;
    private TextView category;
    private CardView mCardview;
    private TextView price;
    private ImageView mOverFlowIcon;
    Context mContext;

    private OnCardClickCallback mCallback;
    public HomeRecyclierViewAdapter(RealmResults<Category> realmResults, Context context, OnCardClickCallback callback) {
        this.mRealmResults = realmResults;
        this.mContext = context;
        mCallback = callback;
        Log.d("bayram", "Recyclierview adampter constructor\n");












    }

    @Override
    public HomeRecyclierViewAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.
                from(parent.getContext()).inflate(R.layout.row_item, parent, false);

        return new MyViewHolder(itemView);
    }

    public void updateRealmResult(RealmResults<Category> realmResults) {
        mRealmResults = realmResults;
        notifyDataSetChanged();


    }

    @Override
    public void onBindViewHolder(HomeRecyclierViewAdapter.MyViewHolder holder, int position) {
        category.setText(mRealmResults.get(position).getCategoryName());
        price.setText(String.valueOf(mRealmResults.get(position).getmStuff().get(0).getPrice()));
    }

    @Override
    public int getItemCount() {
        return mRealmResults.size();
    }



    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public MyViewHolder(View itemView) {
            super(itemView);
            categoryImageView = (ImageView) itemView.findViewById(R.id.imageView);
            category = (TextView) itemView.findViewById(R.id.category);
            price = (TextView) itemView.findViewById(R.id.price);
            mOverFlowIcon = (ImageView) itemView.findViewById(R.id.overflow_icon);
            mOverFlowIcon.setOnClickListener(this);
            mCardview = (CardView) itemView.findViewById(R.id.my_card_view);
            mCardview.setOnClickListener(this);


        }

        @Override
        public void onClick(View v) {
            if(v.getId()==R.id.my_card_view){
                mCallback.onCardClicked(getAdapterPosition());
            }
            else{
                PopupMenu mPopup = new PopupMenu(mContext, v);
                mRealm = Realm.getDefaultInstance();
                mRealm.addChangeListener(new RealmChangeListener<Realm>() {
                    @Override
                    public void onChange(Realm element) {
                        updateRealmResult(mRealmResults);
                        HomeFragment.mRecyclerView.setAdapter(HomeFragment.myRecyclierViewAdapter);
                        HomeFragment.changeTotalPrice();
                    }
                });
                mPopup.getMenuInflater().inflate(R.menu.popup_menu, mPopup.getMenu());
                mPopup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.menu_delete:
                                Toast.makeText(mContext, "position is " + getAdapterPosition(), Toast.LENGTH_SHORT).show();
                                mRealm.executeTransaction(new Realm.Transaction() {
                                    @Override
                                    public void execute(Realm realm) {
                                        mRealmResults.get(getAdapterPosition()).getmStuff().deleteFromRealm(0);
                                        mRealmResults.deleteFromRealm(getAdapterPosition());

                                    }
                                });

                                break;
                        }
                        return true;
                    }
                });
                mPopup.show();
            }


        }
    }
    public interface OnCardClickCallback{
        void onCardClicked(int position);
    }
}
