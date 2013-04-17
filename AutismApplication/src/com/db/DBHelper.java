
package com.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Should one per table
 */
public class DBHelper extends SQLiteOpenHelper {

    // this is our global database config values
    private static final String DATABASE_NAME = "Autism.db";
    private static final int DATABASE_VERSION = 1;

    // this is used by all tables
    public static final String PK_ID_COL_NAME = "_id";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        // create all the tables in the db
        database.execSQL(TaskData.CREATE);
        database.execSQL(ReminderData.CREATE);
        database.execSQL(PhotoData.CREATE);
        database.execSQL(NoteData.CREATE);
        database.execSQL(ContactData.CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

}
