package com.pam.finalproject_2500;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.pam.finalproject_2500.Model.Event;

import java.util.ArrayList;


public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "db_soccer";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "table_favorite";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_TITLE = "title";
    private static final String COLUMN_DATE = "date";
    private static final String COLUMN_TIME = "time";
    private static final String COLUMN_STATUS = "status";
    private static final String COLUMN_IMAGE = "image";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql =
                " CREATE TABLE " + TABLE_NAME + " ( " +
                        COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                        COLUMN_TITLE + " TEXT," +
                        COLUMN_DATE + " DATE," +
                        COLUMN_TIME + " TEXT," +
                        COLUMN_STATUS + " TEXT," +
                        COLUMN_IMAGE + " TEXT" +
                        " ) ";

        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion != newVersion) {
            db.execSQL(" DROP TABLE IF EXISTS " + TABLE_NAME);
            onCreate(db);
        }
    }

    public ArrayList<Event> getAllFavorite() {
        ArrayList<Event> listEvent = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.query(TABLE_NAME, new String[]{COLUMN_ID, COLUMN_TITLE, COLUMN_DATE,
                COLUMN_TIME, COLUMN_STATUS, COLUMN_IMAGE}, null, null,
                null, null, null);

        if( c != null && c.moveToFirst()){
            do {
                Event e = new Event();

                e.setIdEvent(c.getString(0));
                e.setStrEvent(c.getString(1));
                e.setDateEvent(c.getString(2));
                e.setStrTime(c.getString(3));
                e.setStrStatus(c.getString(4));
                e.setStrThumb(c.getString(5));

                listEvent.add(e);
            } while (c.moveToNext());
        }

        db.close();
        return listEvent;
    }

    public ArrayList<Event> getLastFavorite() {
        ArrayList<Event> listEvent = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor c = db.rawQuery( "select * from " + TABLE_NAME + " WHERE date < DATE('now') ", null );

        if( c != null && c.moveToFirst()){
            do {
                Event e = new Event();

                e.setIdEvent(c.getString(0));
                e.setStrEvent(c.getString(1));
                e.setDateEvent(c.getString(2));
                e.setStrTime(c.getString(3));
                e.setStrStatus(c.getString(4));
                e.setStrThumb(c.getString(5));

                listEvent.add(e);
            } while (c.moveToNext());
        }

        db.close();
        return listEvent;
    }

    public ArrayList<Event> getNextFavorite() {
        ArrayList<Event> listEvent = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor c = db.rawQuery( " select * from " + TABLE_NAME + " WHERE date >= DATE('now') ", null );

        if( c != null && c.moveToFirst()){
            do {
                Event e = new Event();

                e.setIdEvent(c.getString(0));
                e.setStrEvent(c.getString(1));
                e.setDateEvent(c.getString(2));
                e.setStrTime(c.getString(3));
                e.setStrStatus(c.getString(4));
                e.setStrThumb(c.getString(5));

                listEvent.add(e);
            } while (c.moveToNext());
        }

        db.close();
        return listEvent;
    }

    public void addFavorite(Event eve){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_TITLE, eve.getStrEvent());
        values.put(COLUMN_DATE, eve.getDateEvent());
        values.put(COLUMN_TIME, eve.getStrTime());
        values.put(COLUMN_STATUS, eve.getStrStatus());
        values.put(COLUMN_IMAGE, eve.getStrThumb());

        db.insert(TABLE_NAME, null, values);
        db.close();

    }

    public void deleteFavorite(String id){
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_NAME, "id = " + id, null);
        db.close();
    }
}
