package com.dev.contactmanagement.database;

public interface ExtensionsTable {
    String TABLE_NAME = "extensions"; //Table Name

    String COLUMN_ID = "id";
    String COLUMN_CONTEXT = "context";
    String COLUMN_CONTACT_ID = "phone_contact_id";

    // Create table SQL query
    String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + COLUMN_CONTEXT + " TEXT,"
                    + COLUMN_CONTACT_ID + " INTEGER, "
                    + " FOREIGN KEY (" + COLUMN_CONTACT_ID + ") REFERENCES "
                    + ContactsTable.TABLE_NAME + "(" + ContactsTable.COLUMN_ID + ")"
                    + ")";

}