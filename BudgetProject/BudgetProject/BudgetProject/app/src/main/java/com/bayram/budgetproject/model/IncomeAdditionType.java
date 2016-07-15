package com.bayram.budgetproject.model;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.RealmList;
import io.realm.RealmResults;
import io.realm.annotations.PrimaryKey;

/**
 * Created by mac on 14.07.2016.
 */

public class IncomeAdditionType extends RealmObject {
    @PrimaryKey
    private int id=0;
    private RealmList<Category> mCategory;

    public static int getId() {
        Realm mRealm = Realm.getDefaultInstance();
        RealmResults<IncomeAdditionType> mRealmList = mRealm.where(IncomeAdditionType.class).findAll();
        return mRealmList.max("id").intValue() + 1;
    }

    public void setId(int id) {
        this.id = id;
    }

    public RealmList<Category> getmCategory() {
        return mCategory;
    }

    public void setmCategory(RealmList<Category> mCategory) {
        this.mCategory = mCategory;
    }

  public static float getTotalPriceForCategoryAsPercentage(String categoryName) {
        Realm mRealm = Realm.getDefaultInstance();
        RealmResults<IncomeAdditionType> mRealmResults1 = mRealm.where(IncomeAdditionType.class).equalTo("mCategory.categoryName", categoryName).findAll();
        RealmResults<IncomeAdditionType> mRealmResults2 = mRealm.where(IncomeAdditionType.class).findAll();
        float sum1 = 0;
        for (int i = 0; i < mRealmResults1.size(); i++) {
            sum1 += mRealmResults1.get(i).getmCategory().get(0).getmStuff().get(0).getPrice();
        }

        float sum2 = 0;
        for (int i = 0; i < mRealmResults2.size(); i++) {
            sum2 += mRealmResults2.get(i).getmCategory().get(0).getmStuff().get(0).getPrice();
        }
        return (sum1 / sum2) * 100;

    }



}
