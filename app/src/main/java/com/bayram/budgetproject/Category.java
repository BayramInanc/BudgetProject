package com.bayram.budgetproject;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Bayram-PC on 12.6.2016.
 */

public class Category extends RealmObject{
    @PrimaryKey
    private long id;
    private String categoryName;
    private RealmList<Stuff> mStuff;
    public Category(){}

    public RealmList<Stuff> getmStuff() {
        return mStuff;
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }


    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
