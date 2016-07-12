package com.bayram.budgetproject;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.RealmResults;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Bayram-PC on 9.6.2016.
 */




public class Stuff extends RealmObject {
    @PrimaryKey
    private static long id = 1;
    private int price = 0;
    private String explanation = "";
    private String shortnote = "";
    private int year = 0;
    private int month = 0;
    public Stuff(){}
    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    private int day = 0;


    public static long getId() {
        return id;
    }

    public static void setId(long mid) {
       id = mid;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public String getShortnote() {
        return shortnote;
    }

    public void setShortnote(String shortnote) {
        this.shortnote = shortnote;
    }

    public static String getTotalPriceForTheDay(int year, int monthOfYear, int dayOfMonth) {
        Realm mRealm = Realm.getDefaultInstance();
        RealmResults<Stuff> mRealmResult = mRealm.where(Stuff.class).equalTo(Constants.STRING_DAY, dayOfMonth).equalTo(Constants.STRING_MONTH, monthOfYear).equalTo(Constants.STRING_YEAR, year).findAll();
        return String.valueOf(mRealmResult.sum("price"));
    }


}

