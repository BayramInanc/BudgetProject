package com.bayram.budgetproject.model;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.RealmResults;
import io.realm.annotations.PrimaryKey;

/**
 * Created by mac on 14.07.2016.
 */

public class OutcomeAdditionType extends RealmObject {
    @PrimaryKey
    private int id = 0;

    public static int getId() {
        Realm mRealm = Realm.getDefaultInstance();
        RealmResults<OutcomeAdditionType> mRealmResults = mRealm.where(OutcomeAdditionType.class).findAll();
        return mRealmResults.max("id").intValue() + 1;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAdditionType() {
        return additionType;
    }

    public void setAdditionType(int additionType) {
        this.additionType = additionType;
    }

    public RealmList<Category> getmCategory() {
        return mCategory;
    }

    public void setmCategory(RealmList<Category> mCategory) {
        this.mCategory = mCategory;
    }

    public static float getTotalPriceForCategoryAsPercentage(String categoryName) {
        Realm mRealm = Realm.getDefaultInstance();
        RealmResults<OutcomeAdditionType> mRealmResults1 = mRealm.where(OutcomeAdditionType.class).equalTo("mCategory.categoryName", categoryName).findAll();
        RealmResults<OutcomeAdditionType> mRealmResults2 = mRealm.where(OutcomeAdditionType.class).findAll();
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

    private int additionType;
    private RealmList<Category> mCategory;

}
