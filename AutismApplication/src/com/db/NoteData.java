
package com.db;

public class NoteData implements Data {
    public static final String TABLE_NAME = "notes";
    public static final String FK = "task_id";
    public static final String NAME = "name";
    public static final String DESC = "desc";
    public static final String CREATE = "create table if not exists "
            + TABLE_NAME + "(" + DBHelper.PK_ID_COL_NAME
            + " integer primary key autoincrement, " + FK
            + " integer not null," + NAME + " text not null," + DESC
            + " text not null" + ");";

    public static final String[] allColumns = {
            DBHelper.PK_ID_COL_NAME, FK,
            NAME, DESC
    };

    private long id;
    private long fk_id;
    private String name;
    private String desc;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return name;
    }
}
