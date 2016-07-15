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
    private boolean isIncome = false;
    private boolean isOutcome = false;

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

    public void setId(int id) {
        this.id = id;
    }


    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public void setType(int type) {
        if(type==0){
            isIncome = true;
        }else{
            isOutcome = true;
        }
    }
    public boolean isIncome(){
        return isIncome;
    }
    public boolean isOutcome(){
        return isOutcome;
    }


}
