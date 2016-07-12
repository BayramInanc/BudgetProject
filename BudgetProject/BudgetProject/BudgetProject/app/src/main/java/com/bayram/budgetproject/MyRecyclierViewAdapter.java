package com.bayram.budgetproject;

import android.content.Context;
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

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;


/**
 * Created by Bayram-PC on 3.2.2016.
 */
public class MyRecyclierViewAdapter extends RecyclerView.Adapter<MyRecyclierViewAdapter.MyViewHolder> {
    RealmResults<Category> mRealmResults;
    private ImageView categoryImageView;
    private Realm mRealm;
    private TextView category;
    private TextView price;
    private ImageView mOverFlowIcon;
    Context mContext;

    public MyRecyclierViewAdapter(RealmResults<Category> realmResults, Context context) {
        this.mRealmResults = realmResults;
        this.mContext = context;
        Log.d("bayram", "Recyclierview adampter constructor\n");
    }

    @Override
    public MyRecyclierViewAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.
                from(parent.getContext()).inflate(R.layout.row_item, parent, false);
        return new MyViewHolder(itemView);
    }

    public void updateRealmResult(RealmResults<Category> realmResults) {
        mRealmResults = realmResults;
        notifyDataSetChanged();


    }

    @Override
    public void onBindViewHolder(MyRecyclierViewAdapter.MyViewHolder holder, int position) {
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

        }

        @Override
        public void onClick(View v) {
            PopupMenu mPopup = new PopupMenu(mContext, v);
            mRealm = Realm.getDefaultInstance();
            mRealm.addChangeListener(new RealmChangeListener<Realm>() {
                @Override
                public void onChange(Realm element) {
                    updateRealmResult(mRealmResults);
                    HomeActivity.mRecyclerView.setAdapter(HomeActivity.myRecyclierViewAdapter);
                    HomeActivity.changeTotalPrice();
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
