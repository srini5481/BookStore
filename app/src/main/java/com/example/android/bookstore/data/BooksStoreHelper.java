package com.example.android.bookstore.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.android.bookstore.data.BooksInfoContract.BooksStoreEntry;

/**
 * Created by qze713 on 1/14/18.
 */

public class BooksStoreHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "bookstore1.db";

    public static final int DATABASE_VERSION = 1;

    public BooksStoreHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {


        String SQL_CREATE_PRODUCT_TABLE = "CREATE TABLE " + BooksStoreEntry.TABLE_NAME + " ("
                +                 BooksStoreEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                +                BooksStoreEntry.COLUMN_PRODUCT_NAME + " TEXT NOT NULL, "
                +                BooksStoreEntry.COLUMN_PRODUCT_TYPE + " INTEGER NOT NULL, "
                +                BooksStoreEntry.COLUMN_PRODUCT_PRICE + " INTEGER NOT NULL, "
                +                BooksStoreEntry.COLUMN_PRODUCT_QUANTITY + " INTEGER NOT NULL, "
                +                BooksStoreEntry.COLUMN_PRODUCT_SUPPLIER_NAME + " TEXT NOT NULL,"
                +                BooksStoreEntry.COLUMN_PRODUCT_SUPPLIER_EMAIL + " TEXT,"
                +                BooksStoreEntry.COLUMN_PRODUCT_SUPPLIER_PHONE_NUMBER + " TEXT)";


        db.execSQL(SQL_CREATE_PRODUCT_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
