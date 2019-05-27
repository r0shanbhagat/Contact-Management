package com.dev.contactmanagement.database;

public interface AccountsTable {
    String TABLE_NAME = "account"; //Table Name

    String COLUMN_ID = "id";
    String COLUMN_STATUS = "status";
    String COLUMN_USER_ID = "userID";
    String COLUMN_CONTEXT = "context";

    // Create table SQL query
    String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_STATUS + " INTEGER,"
                    + COLUMN_USER_ID + " TEXT,"
                    + COLUMN_CONTEXT + " TEXT,"
                    + " FOREIGN KEY (" + COLUMN_CONTEXT + ") REFERENCES "
                    + ExtensionsTable.TABLE_NAME + "(" + ExtensionsTable.COLUMN_CONTEXT + ")"

                    + ")";

}