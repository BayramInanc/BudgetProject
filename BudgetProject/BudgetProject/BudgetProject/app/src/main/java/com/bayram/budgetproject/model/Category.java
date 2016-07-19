package com.bayram.budgetproject.model;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.RealmResults;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Bayram-PC on 12.6.2016.
 */

public class Category extends RealmObject {
    @PrimaryKey
    private int id = 0;
    private String categoryName;
    private RealmList<Stuff> mStuff;
    private int type;

    public Category() {
    }

    public RealmList<Stuff> getmStuff() {
        return mStuff;
    }

    public static int getId() {
        Realm mRealm = Realm.getDefaultInstance();
        RealmResults<Category> mRealmResults = mRealm.where(Category.class).findAll();
        return mRealmResults.max("id").intValue() + 1;
    }


    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public void setType(int type) {
        this.type = type;
    }




    public void setId(int id) {
        this.id = id;
    }


    public static float getTotalPriceForCategoryAsPercentage(String categoryName , int type) {
        Realm mRealm = Realm.getDefaultInstance();
        RealmResults<Category> mRealmResults1 = mRealm.where(Category.class).equalTo("categoryName", categoryName).equalTo("type",type).findAll();
        RealmResults<Category> mRealmResults2 = mRealm.where(Category.class).equalTo("type",type).findAll();

        float sum1 = 0;
        for (int i = 0; i < mRealmResults1.size(); i++) {
            sum1 += mRealmResults1.get(i).getmStuff().get(0).getPrice();
        }

        float sum2 = 0;
        for (int i = 0; i < mRealmResults2.size(); i++) {
            sum2 += mRealmResults2.get(i).getmStuff().get(0).getPrice();
        }
        return (sum1 / sum2) * 100;


    }
}