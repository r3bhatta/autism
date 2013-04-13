package com.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Should one per table
 */
public class ExampleDBHelper extends SQLiteOpenHelper {

  public static final String TABLE_NAME = "example";
  public static final String COLUMN_ID = "_id";
  public static final String ADDITIONAL_COL = "test_col";

  private static final String DATABASE_NAME = "example.db";
  private static final int DATABASE_VERSION = 1;

  // Database creation sql statement
  private static final String DATABASE_CREATE =
    "create table "
      + TABLE_NAME 
      + "("
          + COLUMN_ID + " integer primary key autoincrement, "
          + ADDITIONAL_COL + " text not null"
      + ");";

  public ExampleDBHelper(Context context) {
    super(context, DATABASE_NAME, null, DATABASE_VERSION);
  }

  @Override
  public void onCreate(SQLiteDatabase database) {
    database.execSQL(DATABASE_CREATE);
  }

  @Override
  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    //Possible option for now
    Log.w(ExampleDBHelper.class.getName(),
        "Upgrading database from version " + oldVersion + " to "
            + newVersion + ", which will destroy all old data");
    //db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
    //onCreate(db);
  }

} 
