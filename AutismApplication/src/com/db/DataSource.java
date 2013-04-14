package com.db;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/**
 * We don't have to worry about concurrency since only one activity will be
 * using db at any point in time :)
 * 
 * Usage Example:
 * 
 * Call this from your activity, param 'this' should be a context (i.e. Activity
 * object) DataSource dataSource = new DataSource(this);
 * 
 * Then open a writable version of db dataSource.openWritableDB();
 * 
 * Then for example to create a task call dataSource.createTaskData(String
 * name);
 * 
 * You can also call deleteData(Data data); where the data param is one of
 * TaskData, ReminderData, NoteData, PhotoData, ContactData this will delete
 * only a single row that corresponds to the passed in data object A data object
 * corresponds to a single row
 * 
 * You can also call to get all data from a particular table List<TaskData> list
 * = getAllTaskData(); you now have a list you can go through
 * 
 * When you are finally done with the db you can call dataSource.close();
 * 
 */

public class DataSource {

	// Database fields
	private final Object dataBaseSync = new Object();
	private SQLiteDatabase database;
	private DBHelper dbHelper;

	public DataSource(Context context) {
		dbHelper = new DBHelper(context);
	}

	public void openWritableDB() throws SQLException {
		database = dbHelper.getWritableDatabase();
	}

	public void close() {
		dbHelper.close();
	}

	private void runRunnable(Runnable r) {
		new Thread(r).start();
	}

	/**
	 * Creating ***************************************************
	 */

	public void createTaskData(String name) {
		// this is just a key value pair
		// key is the column name
		final ContentValues values = new ContentValues();
		values.put(TaskData.NAME, name);

		runRunnable(new Runnable() {
			@Override
			public void run() {
				synchronized (dataBaseSync) {
					database.insert(TaskData.TABLE_NAME, null, values);
				}
			}
		});
	}

	public void createContactData(long task_id, String uri) {
		final ContentValues values = new ContentValues();
		values.put(ContactData.FK, task_id);
		values.put(ContactData.URI, uri);

		runRunnable(new Runnable() {
			@Override
			public void run() {
				synchronized (dataBaseSync) {
					database.insert(ContactData.TABLE_NAME, null, values);
				}
			}
		});
	}

	public void createReminderData(long task_id, Date date, long interval,
			String location) {

		final ContentValues values = new ContentValues();
		values.put(ReminderData.FK, task_id);

		long time = date.getTime() / 1000;
		values.put(ReminderData.DATE, time);
		
		values.put(ReminderData.INTERVAL, interval);
		values.put(ReminderData.LOCATION, location);

		runRunnable(new Runnable() {
			@Override
			public void run() {
				synchronized (dataBaseSync) {
					database.insert(ReminderData.TABLE_NAME, null, values);
				}
			}
		});
	}

	public void createPhotoData(long task_id, String path) {
		final ContentValues values = new ContentValues();
		values.put(PhotoData.FK, task_id);
		values.put(PhotoData.PATH, path);

		runRunnable(new Runnable() {
			@Override
			public void run() {
				synchronized (dataBaseSync) {
					database.insert(PhotoData.TABLE_NAME, null, values);
				}
			}
		});
	}

	public void createNoteData(long task_id, String name, String desc) {
		final ContentValues values = new ContentValues();
		values.put(NoteData.FK, task_id);
		values.put(NoteData.NAME, name);
		values.put(NoteData.DESC, desc);

		runRunnable(new Runnable() {
			@Override
			public void run() {
				synchronized (dataBaseSync) {
					database.insert(NoteData.TABLE_NAME, null, values);
				}
			}
		});
	}

	/**
	 * Deleting ***************************************************
	 */

	public void deleteData(final Data data) {
		final long id = data.getId();

		runRunnable(new Runnable() {
			@Override
			public void run() {
				synchronized (dataBaseSync) {
					database.delete(
					/* tableName */data.getTableName(),
					/* where */DBHelper.PK_ID_COL_NAME + " = " + id, null);
				}
			}
		});
	}

	/**
	 * Get all data ***********************************************
	 */

	public List<TaskData> getAllTaskData() {
		List<TaskData> allData = new ArrayList<TaskData>();

		// gets everything from table into cursor
		Cursor cursor = database.query(
		/* tableName */TaskData.TABLE_NAME,
		/* columnNames */TaskData.allColumns,
		/* where */null,
		/* selectionArgs */null,
		/* groupBy */null,
		/* having */null,
		/* orderBy */null);

		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			TaskData data = cursorToTaskData(cursor);
			allData.add(data);
			cursor.moveToNext();
		}

		cursor.close();
		return allData;
	}

	public List<ContactData> getAllContactData() {
		List<ContactData> allData = new ArrayList<ContactData>();

		// gets everything from table into cursor
		Cursor cursor = database.query(
		/* tableName */ContactData.TABLE_NAME,
		/* columnNames */ContactData.allColumns,
		/* where */null,
		/* selectionArgs */null,
		/* groupBy */null,
		/* having */null,
		/* orderBy */null);

		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			ContactData data = cursorToContactData(cursor);
			allData.add(data);
			cursor.moveToNext();
		}

		cursor.close();
		return allData;
	}

	public List<ReminderData> getAllReminderData() {
		List<ReminderData> allData = new ArrayList<ReminderData>();

		// gets everything from table into cursor
		Cursor cursor = database.query(
		/* tableName */ReminderData.TABLE_NAME,
		/* columnNames */ReminderData.allColumns,
		/* where */null,
		/* selectionArgs */null,
		/* groupBy */null,
		/* having */null,
		/* orderBy */null);

		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			ReminderData data = cursorToReminderData(cursor);
			allData.add(data);
			cursor.moveToNext();
		}

		cursor.close();
		return allData;
	}

	public List<PhotoData> getAllPhotoData() {
		List<PhotoData> allData = new ArrayList<PhotoData>();

		// gets everything from table into cursor
		Cursor cursor = database.query(
		/* tableName */PhotoData.TABLE_NAME,
		/* columnNames */PhotoData.allColumns,
		/* where */null,
		/* selectionArgs */null,
		/* groupBy */null,
		/* having */null,
		/* orderBy */null);

		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			PhotoData data = cursorToPhotoData(cursor);
			allData.add(data);
			cursor.moveToNext();
		}

		// If you ever use cursor don't forget to close it Important! memory
		// leaks
		cursor.close();
		return allData;
	}

	public List<NoteData> getAllNoteData() {
		List<NoteData> allData = new ArrayList<NoteData>();

		// gets everything from table into cursor
		Cursor cursor = database.query(
		/* tableName */NoteData.TABLE_NAME,
		/* columnNames */NoteData.allColumns,
		/* where */null,
		/* selectionArgs */null,
		/* groupBy */null,
		/* having */null,
		/* orderBy */null);

		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			NoteData data = cursorToNoteData(cursor);
			allData.add(data);
			cursor.moveToNext();
		}

		cursor.close();
		return allData;
	}

	// gets you Data object pertaining to the row at which the cursor is at
	private TaskData cursorToTaskData(Cursor cursor) {
		TaskData data = new TaskData();
		data.setId(cursor.getLong(0));
		data.setName(cursor.getString(1));
		return data;
	}

	// gets you Data object pertaining to the row at which the cursor is at
	private ContactData cursorToContactData(Cursor cursor) {
		ContactData data = new ContactData();
		data.setId(cursor.getLong(0));
		data.setFkId(cursor.getLong(1));
		data.setURI(cursor.getString(2));
		return data;
	}

	// gets you Data object pertaining to the row at which the cursor is at
	private ReminderData cursorToReminderData(Cursor cursor) {
		ReminderData data = new ReminderData();
		data.setId(cursor.getLong(0));
		data.setFkId(cursor.getLong(1));
		data.setDate(cursor.getLong(2));
		data.setInterval(cursor.getLong(3));
		data.setLocation(cursor.getString(4));
		return data;
	}

	// gets you Data object pertaining to the row at which the cursor is at
	private PhotoData cursorToPhotoData(Cursor cursor) {
		PhotoData data = new PhotoData();
		data.setId(cursor.getLong(0));
		data.setFkId(cursor.getLong(1));
		data.setPath(cursor.getString(2));
		return data;
	}

	// gets you Data object pertaining to the row at which the cursor is at
	private NoteData cursorToNoteData(Cursor cursor) {
		NoteData data = new NoteData();
		data.setId(cursor.getLong(0));
		data.setFkId(cursor.getLong(1));
		data.setName(cursor.getString(2));
		data.setDesc(cursor.getString(3));
		return data;
	}
	
	
	/** Query Table for row by ID */
	
	public List<TaskData> queryTaskDataByPk(long id) {
		List<TaskData> allData = new ArrayList<TaskData>();

		// gets everything from table into cursor
		Cursor cursor = database.query(
		/* tableName */TaskData.TABLE_NAME,
		/* columnNames */TaskData.allColumns,
		/* where */DBHelper.PK_ID_COL_NAME + " = " + id,
		/* selectionArgs */null,
		/* groupBy */null,
		/* having */null,
		/* orderBy */null);

		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			TaskData data = cursorToTaskData(cursor);
			allData.add(data);
			cursor.moveToNext();
		}

		cursor.close();
		return allData;
	}
	
	public List<ContactData> queryContactDataByFk(long fk) {
		List<ContactData> allData = new ArrayList<ContactData>();

		// gets everything from table into cursor
		Cursor cursor = database.query(
		/* tableName */ContactData.TABLE_NAME,
		/* columnNames */ContactData.allColumns,
		/* where */ContactData.FK + " = " + fk,
		/* selectionArgs */null,
		/* groupBy */null,
		/* having */null,
		/* orderBy */null);

		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			ContactData data = cursorToContactData(cursor);
			allData.add(data);
			cursor.moveToNext();
		}

		cursor.close();
		return allData;
	}

	public List<ReminderData> queryReminderDataByFk(long fk) {
		List<ReminderData> allData = new ArrayList<ReminderData>();

		// gets everything from table into cursor
		Cursor cursor = database.query(
		/* tableName */ReminderData.TABLE_NAME,
		/* columnNames */ReminderData.allColumns,
		/* where */ReminderData.FK + " = " + fk,
		/* selectionArgs */null,
		/* groupBy */null,
		/* having */null,
		/* orderBy */null);

		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			ReminderData data = cursorToReminderData(cursor);
			allData.add(data);
			cursor.moveToNext();
		}

		cursor.close();
		return allData;
	}

	public List<PhotoData> queryPhotoDataByFk(long fk) {
		List<PhotoData> allData = new ArrayList<PhotoData>();

		// gets everything from table into cursor
		Cursor cursor = database.query(
		/* tableName */PhotoData.TABLE_NAME,
		/* columnNames */PhotoData.allColumns,
		/* where */PhotoData.FK + " = " + fk,
		/* selectionArgs */null,
		/* groupBy */null,
		/* having */null,
		/* orderBy */null);

		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			PhotoData data = cursorToPhotoData(cursor);
			allData.add(data);
			cursor.moveToNext();
		}

		// If you ever use cursor don't forget to close it Important! memory
		// leaks
		cursor.close();
		return allData;
	}

	public List<NoteData> queryNoteDataByFk(long fk) {
		List<NoteData> allData = new ArrayList<NoteData>();

		// gets everything from table into cursor
		Cursor cursor = database.query(
		/* tableName */NoteData.TABLE_NAME,
		/* columnNames */NoteData.allColumns,
		/* where */NoteData.FK + " = " + fk,
		/* selectionArgs */null,
		/* groupBy */null,
		/* having */null,
		/* orderBy */null);

		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			NoteData data = cursorToNoteData(cursor);
			allData.add(data);
			cursor.moveToNext();
		}

		cursor.close();
		return allData;
	}
	
}
