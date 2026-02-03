package com.example.dictionary.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class DatabaseHelper extends SQLiteAssetHelper {



    public DatabaseHelper(Context context) {
        super(context, "dictionary.db", null, 1);
    }

    public Cursor getAllData(){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Dictionary",null);
        return cursor;
    }


    public Cursor searchWord(String key){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Dictionary WHERE word LIKE '"+key+"%'", null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }





}
