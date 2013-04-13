package com.db;

import java.sql.Date;

import android.content.ContentValues;
import android.database.Cursor;

public class ReminderData implements Data {
	public static final String TABLE_NAME = "reminders";
	public static final String FK = "task_id";
	public static final String DATE = "date";
	public static final String INTERVAL = "interval";
	public static final String LOCATION = "location";
	public static final String CREATE = "create table if not exists "
			+ TABLE_NAME + "(" + DBHelper.PK_ID_COL_NAME
			+ " integer primary key autoincrement, " + FK
			+ " integer not null, " + DATE + " integer not null, " + INTERVAL
			+ " integer not null, " + LOCATION + " text not null" + ");";

	public static final String[] allColumns = { DBHelper.PK_ID_COL_NAME, FK,
			DATE, INTERVAL, LOCATION };

	private long id;
	private long fk_id;
	private long dateMs;
	private long interval;
	private String location;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Override
	public String getTableName() {
		return TABLE_NAME;
	}

	public long getFkId() {
		return fk_id;
	}

	public void setFkId(long fk_id) {
		this.fk_id = fk_id;
	}

	public long getDate() {
		return dateMs;
	}

	public void setDate(long dateMs) {
		this.dateMs = dateMs;
	}

	public long getInterval() {
		return interval;
	}

	public void setInterval(long interval) {
		this.interval = interval;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	@Override
	public String toString() {
		return "";
	}

}