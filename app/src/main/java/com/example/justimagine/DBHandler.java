package com.example.justimagine;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;


public class DBHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "properties.db";

    private static final String TABLE_PACKAGE = "package";
    private static final String COLUMN_PACKAGE_ID = "p_id";
    private static final String COLUMN_PACKAGE_NAME = "name";
    private static final String COLUMN_PACKAGE_IS_ACTIVE = "is_active";

    public DBHandler(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String CREATE_PACKAGE_TABLE =
                "CREATE TABLE IF NOT EXISTS " + TABLE_PACKAGE + " ( " +
                        COLUMN_PACKAGE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_PACKAGE_NAME + " TEXT, "+
                        COLUMN_PACKAGE_IS_ACTIVE + " BOOLEAN "+
                        " );";

        db.execSQL(CREATE_PACKAGE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PACKAGE);
        onCreate(db);
    }

    public Cursor getPackages(){
        final String query = "SELECT * FROM " + TABLE_PACKAGE + " ;";
        SQLiteDatabase db = this.getReadableDatabase();
        return  db.rawQuery(query, null);
    }

    public Cursor getPackages(String name){
        final String query = "SELECT * FROM " + TABLE_PACKAGE + " WHERE " + COLUMN_PACKAGE_NAME + " = \"" + name + "\";";
        SQLiteDatabase db = this.getReadableDatabase();
        return  db.rawQuery(query, null);
    }

    public  void addPackage(CardPackage day){
        ContentValues val = new ContentValues();
        val.put(COLUMN_PACKAGE_NAME, day.mName);
        val.put(COLUMN_PACKAGE_IS_ACTIVE, day.isActive);

        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_PACKAGE, null, val);
        db.close();
    }

    public void deletePackage(String name){
        String query = "SELECT * FROM " + TABLE_PACKAGE + " WHERE " + COLUMN_PACKAGE_NAME + " = \"" + name + "\";";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cur = db.rawQuery(query, null);

        if(cur.moveToFirst()){
            db.delete( TABLE_PACKAGE, COLUMN_PACKAGE_NAME + " = ?", new String[]{ name });
        }
        cur.close();
    }

    public void updatePackage(CardPackage pack){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues val = new ContentValues();
        val.put(COLUMN_PACKAGE_NAME, pack.mName);
        val.put(COLUMN_PACKAGE_IS_ACTIVE, pack.isActive);


            db.update( TABLE_PACKAGE, val, COLUMN_PACKAGE_NAME + " = ?", new String[]{ pack.mName });

    }
}
