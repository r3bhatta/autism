package com.db;

public class PhotoData implements Data {
	public static final String TABLE_NAME = "photos";
	public static final String FK = "task_id";
	public static final String PATH = "path";
	public static final String CREATE = "create table if not exists "
			+ TABLE_NAME + "(" + DBHelper.PK_ID_COL_NAME
			+ " integer primary key autoincrement, " + FK
			+ " integer not null," + PATH + " text not null" + ");";

	public static final String[] allColumns = { DBHelper.PK_ID_COL_NAME, FK,
			PATH };

	private long id;
	private long fk_id;
	private String path;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTableName() {
		return TABLE_NAME;
	}

	public long getFkId() {
		return fk_id;
	}

	public void setFkId(long fk_id) {
		this.fk_id = fk_id;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	@Override
	public String toString() {
		return path;
	}

}
