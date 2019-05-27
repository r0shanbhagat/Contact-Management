package com.dev.contactmanagement.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.dev.contactmanagement.beans.ContactModel;

import java.util.ArrayList;
import java.util.List;


public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "contacts_db.db";
    private static DatabaseHelper mInstance;

    private DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * @param ctx
     * @return
     */
    public static DatabaseHelper getInstance(Context ctx) {
        if (mInstance == null) {
            mInstance = new DatabaseHelper(ctx.getApplicationContext());
        }
        return mInstance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(ContactsTable.CREATE_TABLE);
        db.execSQL(ExtensionsTable.CREATE_TABLE);
        db.execSQL(AccountsTable.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + ContactsTable.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + ExtensionsTable.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + AccountsTable.TABLE_NAME);
        onCreate(db);
    }

    // **** CRUD (Create, Read, Update, Delete) Operations ***** //

    public String[] getContactIdList() {
        SQLiteDatabase db = this.getWritableDatabase();
        List<String> contactList = new ArrayList<>();
        String query = "SELECT " + ContactsTable.COLUMN_ID + " FROM " + ContactsTable.TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        while (cursor.moveToNext()) {
            contactList.add(cursor.getString(cursor.getColumnIndex(ContactsTable.COLUMN_ID)));
        }
        String[] arrContactId = contactList.toArray(new String[contactList.size()]);
        return arrContactId;
    }

    /**
     * @param contactId
     * @return
     */
    public ContactModel getContactDetailModel(int contactId) {
        ContactModel contactModel = new ContactModel();
        SQLiteDatabase db = this.getWritableDatabase();
        String rawQuery = "SELECT " +
                ContactsTable.TABLE_NAME + "." + ContactsTable.COLUMN_STAGING_ID + "," +
                ExtensionsTable.TABLE_NAME + "." + ExtensionsTable.COLUMN_CONTEXT + "," +
                AccountsTable.COLUMN_STATUS + "," +
                AccountsTable.COLUMN_USER_ID
                + " FROM " + ContactsTable.TABLE_NAME
                + " INNER JOIN " + ExtensionsTable.TABLE_NAME + " ON " + ContactsTable.TABLE_NAME + "." + ContactsTable.COLUMN_ID + "=" + ExtensionsTable.TABLE_NAME + "." + ExtensionsTable.COLUMN_CONTACT_ID
                + " INNER JOIN " + AccountsTable.TABLE_NAME + " ON " + ExtensionsTable.TABLE_NAME + "." + ExtensionsTable.COLUMN_CONTEXT + "=" + AccountsTable.TABLE_NAME + "." + AccountsTable.COLUMN_CONTEXT
                + " WHERE " + ContactsTable.COLUMN_ID + "=" + contactId;

        Cursor cursor = db.rawQuery(rawQuery, null);
        while (cursor.moveToNext()) {
            contactModel.setStagingId(cursor.getString(cursor.getColumnIndex(ContactsTable.COLUMN_STAGING_ID)));
            contactModel.setContext(cursor.getString(cursor.getColumnIndex(ExtensionsTable.COLUMN_CONTEXT)));
            contactModel.setStatus(cursor.getInt(cursor.getColumnIndex(AccountsTable.COLUMN_STATUS)));
            contactModel.setUserId(cursor.getString(cursor.getColumnIndex(AccountsTable.COLUMN_USER_ID)));
        }
        db.close();
        return contactModel;
    }


    /**
     * Insert Dummy Data
     */
    public void insertDefaultValue() {
        SQLiteDatabase db = this.getWritableDatabase();

        String contactInsQuery = "INSERT INTO " + ContactsTable.TABLE_NAME +
                " VALUES(2, '48f3', '1196'),(3, '3e47', 'f1fe'),(4, '2cac', '036e')";

        String extensionsInsQuery = "INSERT INTO " + ExtensionsTable.TABLE_NAME +
                "(" + ExtensionsTable.COLUMN_CONTEXT + "," + ExtensionsTable.COLUMN_CONTACT_ID + ") " +
                " VALUES('Gmail' ,2),('Gmail', 3),('Gmail1', 4)";

        String accountsInsQuery = "INSERT INTO " + AccountsTable.TABLE_NAME +
                "(" + AccountsTable.COLUMN_STATUS + "," + AccountsTable.COLUMN_USER_ID + "," + AccountsTable.COLUMN_CONTEXT + ") " +
                " VALUES(1, 'test_one@gmail.com', 'Gmail'),(0, 'test_two@gmail.com', 'Gmail1')";

        db.delete(ContactsTable.TABLE_NAME, "", null);
        db.delete(ExtensionsTable.TABLE_NAME, "", null);
        db.delete(AccountsTable.TABLE_NAME, "", null);

        db.execSQL(contactInsQuery);
        db.execSQL(extensionsInsQuery);
        db.execSQL(accountsInsQuery);
        db.close();
    }

}