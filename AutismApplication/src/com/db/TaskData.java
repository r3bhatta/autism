
package com.db;

public class TaskData implements Data {
    public static final String TABLE_NAME = "tasks";
    public static final String NAME = "name";
    public static final String CREATE = "create table if not exists "
            + TABLE_NAME + "(" + DBHelper.PK_ID_COL_NAME
            + " integer primary key autoincrement, " + NAME + " text not null"
            + ");";

    public static final String[] allColumns = {
            DBHelper.PK_ID_COL_NAME, NAME
    };

    private long id;
    private String name;

    @Override
    public long getId() {
        return id;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

    public String getTableName() {
        return TABLE_NAME;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

}
