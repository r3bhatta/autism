package com.db;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DataSource {

  // Database fields
  private SQLiteDatabase database;
  private ExampleDBHelper dbHelper;
  
  private String[] allColumns = {
    ExampleDBHelper.COLUMN_ID,
    ExampleDBHelper.ADDITIONAL_COL
  };

  public DataSource(Context context) {
    dbHelper = new ExampleDBHelper(context);
  }

  public void openWritableDB() throws SQLException {
    database = dbHelper.getWritableDatabase();
  }

  public void close() {
    dbHelper.close();
  }

  public Data createData(String data) {
    
    // this is just a key value pair
    // key is the column name
    ContentValues values = new ContentValues();
    values.put(ExampleDBHelper.ADDITIONAL_COL, data);
    
    long insertId = database.insert(
      /* tableName */ ExampleDBHelper.TABLE_NAME,
      null,
      values
    );
    
    // the code after this point will return you back a Data object 
    // of what you just created

    Cursor cursor = database.query(
      /* tableName */ ExampleDBHelper.TABLE_NAME,
      /* columnNames */ allColumns,
      /* where */ ExampleDBHelper.COLUMN_ID + " = " + insertId,
      /* selectionArgs */ null,
      /* groupBy */ null,
      /* having */ null,
      /* orderBy */ null
    );
    
    cursor.moveToFirst();
    Data newData = cursorToData(cursor);
    
    // If you ever use cursor don't forget to close it Important! memory leaks
    cursor.close();
    return newData;
  }

  public void deleteData(Data data) {
    long id = data.getId();
    database.delete(
      /* tableName */ ExampleDBHelper.TABLE_NAME,
      /* where */ ExampleDBHelper.COLUMN_ID + " = " + id,
      null
    );
  }

  //This seems like it will be useful
  public List<Data> getAllData() {
    List<Data> allData = new ArrayList<Data>();

    // gets everything from table into cursor
    Cursor cursor = database.query(
      /* tableName */ ExampleDBHelper.TABLE_NAME,
      /* columnNames */ allColumns,
      /* where */ null,
      /* selectionArgs */ null,
      /* groupBy */ null,
      /* having */ null,
      /* orderBy */ null
    );

    cursor.moveToFirst();
    while (!cursor.isAfterLast()) {
      Data data = cursorToData(cursor);
      allData.add(data);
      cursor.moveToNext();
    }
    
    // If you ever use cursor don't forget to close it Important! memory leaks
    cursor.close();
    return allData;
  }

  // gets you Data object pertaining to the row at which the cursor is at
  private Data cursorToData(Cursor cursor) {
    Data data = new Data();
    // get value at first column which is _id and set that as the id
    data.setId(cursor.getLong(0));
    // get value at second col and set that as the data
    data.setData(cursor.getString(1));
    return data;
  }
}