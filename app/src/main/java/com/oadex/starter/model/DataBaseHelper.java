package com.oadex.starter.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Malik on 01-Nov-17.
 */

public class DataBaseHelper extends SQLiteOpenHelper
{

    private static final String databaseName = "WhistleBlowing";
    private static final String tableName = "Info";
    private static final String title = "Title";
    private static final String description = "Description";
    private static final String picture = "Picture";
    private static final String video = "Video";
    private static final String time = "Time";
    private static final String requestCode = "_id";
    private static final int databaseVersion = 1;


    public DataBaseHelper(Context context)
    {
        super(context, databaseName, null, databaseVersion);
        Log.i("DataBaseHelper", "initialise constructor");
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL("CREATE TABLE " + tableName + " (" + requestCode + " INTEGER PRIMARY KEY AUTOINCREMENT, " + title + " TEXT, " + description + " TEXT, " + time + " TEXT, "+ picture + " BLOB, " + video + " BLOB )");
        Log.i("DataBaseHelper", "on create database");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS " + tableName);
        onCreate(db);
        Log.i("DataBaseHelper", "upgrade database");
    }


    public boolean insert(Starter starter)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DataBaseHelper.title, starter.getTitle());
        contentValues.put(DataBaseHelper.description,starter.getDescription());
        contentValues.put(DataBaseHelper.time,starter.getTime());
        contentValues.put(DataBaseHelper.picture,starter.getPicture());
        contentValues.put(DataBaseHelper.video,starter.getVideo());

        getWritableDatabase().insert(tableName,null,contentValues);

        Log.i("DataBaseHelper", "inserting into database");
        return true;
    }

    public void update(String courseCode, String courseTitle,String venue, String day, String time,String alert,String updatedDay)
    {
        ContentValues contentValues = new ContentValues();

        getWritableDatabase().update(tableName,contentValues,"CourseCode = ? AND Day = ?",new String[]{courseCode,updatedDay});
        Log.i("DataBaseHelper", "updating data");
    }

    public boolean deletAll()
    {
        getReadableDatabase().delete(tableName, null, null);
        Log.i("DataBaseHelper", "deleting data ");
        return true;
    }

    public List<Starter> getAll() {
        List<Starter> list = new ArrayList<Starter>();
        Log.i("DataBaseHelper", "getAll");
        Cursor cursor = getReadableDatabase().query(tableName, new String[]{requestCode, title, description, time, picture, video}, null, null, null, null, requestCode);
        if (cursor.moveToFirst())
        {
            do
                {
                Starter starter = new Starter(cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5));
                    Log.i("DATA", "hello" + starter.getPicture());
                list.add(starter);

            }
            while (cursor.moveToNext());

        }

        return list;
    }

    public int count()
    {
        Log.i("DataBaseHelper", "counting data ");
        String countQuery = "SELECT * FROM " + tableName;
        Cursor cursor = getReadableDatabase().rawQuery(countQuery, null);
        int count = cursor.getCount();
        cursor.close();
        return count;
    }
}
