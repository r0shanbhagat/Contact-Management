package com.dev.contactmanagement.model;


import android.content.Context;

import com.dev.contactmanagement.beans.ContactModel;
import com.dev.contactmanagement.database.DatabaseHelper;

public class ContactDataModel {

    public static final String TAG = ContactDataModel.class.getSimpleName();

    /**
     * @param mContext
     * @return
     */
    public String[] getContactIdList(Context mContext) {
        return DatabaseHelper.getInstance(mContext).getContactIdList();
    }

    public ContactModel getContactDetailModel(Context mContext, int contactId) {
        return DatabaseHelper.getInstance(mContext).getContactDetailModel(contactId);
    }

    public void insertDefaultData(Context mContext) {
        DatabaseHelper.getInstance(mContext).insertDefaultValue();
    }


}