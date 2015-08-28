package com.pixisolutions.loyaltymanager.models;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

/**
 * Created by sanjoy on 8/28/15.
 */
public class OfferDbHelper extends SQLiteOpenHelper {

    public static abstract class OfferEntry implements BaseColumns {
        public static final String TABLE_NAME = "offers";
        public static final String COLUMN_NAME_ID = "offer_id";
        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_SUMMARY = "summary";
        public static final String COLUMN_NAME_DESCRIPTION = "description";
        public static final String COLUMN_NAME_POINT = "point";
        public static final String COLUMN_NAME_PRICE = "price" ;
        public static final String COLUMN_NAME_CREATED = "created";
        public static final String COLUMN_NAME_EXPIRES = "expires";


    }
    public static final String SQL_CREATE_TABLE = "CREATE TABLE " + OfferEntry.TABLE_NAME + " (" +
            OfferEntry.COLUMN_NAME_ID + " INTEGER PRIMARY KEY," +
            OfferEntry.COLUMN_NAME_NAME + " TEXT" + "," +
            OfferEntry.COLUMN_NAME_SUMMARY + " TEXT" + ","+
            OfferEntry.COLUMN_NAME_DESCRIPTION + " TEXT" + ","+
            OfferEntry.COLUMN_NAME_POINT + " INTEGER" + ","+
            OfferEntry.COLUMN_NAME_PRICE + " REAL" + ","+
            OfferEntry.COLUMN_NAME_CREATED + " TEXT" + ","+
            OfferEntry.COLUMN_NAME_EXPIRES + " TEXT" + ","+
            ";";
    public static final  String SQL_DROP_TABLE = "DROP TABLE IF EXISTS " + OfferEntry.TABLE_NAME;

    public static final Integer DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "loyalty_manager.db";

    public OfferDbHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2) {
        sqLiteDatabase.execSQL(SQL_DROP_TABLE);
        sqLiteDatabase.execSQL(SQL_CREATE_TABLE);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
