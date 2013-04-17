
package com.db;

public class ContactData implements Data {
    public static final String TABLE_NAME = "contacts";
    public static final String FK = "task_id";
    public static final String URI = "uri";
    public static final String CREATE = "create table if not exists "
            + TABLE_NAME + "(" + DBHelper.PK_ID_COL_NAME
            + " integer primary key autoincrement, " + FK
            + " integer not null," + URI + " text not null" + ");";

    public static final String[] allColumns = {
            DBHelper.PK_ID_COL_NAME, FK,
            URI
    };

    private long id;
    private long fk_id;
    private String uri;

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

    public String getURI() {
        return uri;
    }

    public void setURI(String uri) {
        this.uri = uri;
    }

    @Override
    public String toString() {
        return uri;
    }

}
