package com.bayram.budgetproject;

import com.orm.SugarRecord;
import com.orm.dsl.Ignore;
import com.orm.query.Condition;
import com.orm.query.Select;

import java.util.List;

/**
 * Created by Bayram-PC on 5.2.2016.
 */



public class Database extends SugarRecord {
    private String category = "";

    private String price = "";
    private String explanation = "";
    private String shortnote = "";
    private int year=0;
    private int month=0;
    private int day=0;
    List<Database> mDatabaseList;
    @Ignore




    public String getMonth() {
        return String.valueOf(month);
    }
    public String getDay() {
        return String.valueOf(day);
    }
    public String getYear() {
        return String.valueOf(year);
    }
    public String getShortnote() {
        return shortnote;
    }
    public String getExplanation() {
        return explanation;
    }
    public String getPrice() {
        return price;
    }
    public String getCategory() {
        return category;
    }

    public String getTotalPriceForTheDay(int year, int monthOfYear, int dayOfMonth ) {
        List<Database> mDatabaseList = Select.from(Database.class).where(Condition.prop(Constants.STRING_DAY).eq(dayOfMonth), Condition.prop(Constants.STRING_MONTH).eq(monthOfYear), Condition.prop(Constants.STRING_YEAR).eq(year)).list();
       float totalPrice = 0;
        for (int i = 0; i < mDatabaseList.size(); i++) {
            totalPrice += Float.valueOf(mDatabaseList.get(i).getPrice());
        }
        return String.valueOf(totalPrice);
    }

    public Database() {
    }

    public Database(String category, String price, String explanation, String shortnote,int day,int month,int year) {
        this.category = category;
        this.price = price;
        this.explanation = explanation;
        this.shortnote = shortnote;
        this.year = year;
        this.month = month;
        this.day = day;
    }






}
