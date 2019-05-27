package com.dev.contactmanagement.database;

public interface ContactsTable {
    String TABLE_NAME = "contact"; //Table Name

    String COLUMN_ID = "_id";
    String COLUMN_CONTACT_ID = "contactId";
    String COLUMN_STAGING_ID = "stagingId";

    // Create table SQL query
    String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY,"
                    + COLUMN_CONTACT_ID + " TEXT,"
                    + COLUMN_STAGING_ID + " TEXT"
                    + ")";

}