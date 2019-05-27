package com.dev.contactmanagement.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.content.Context;

import com.dev.contactmanagement.beans.ContactModel;
import com.dev.contactmanagement.model.ContactDataModel;

public class ContactViewModel extends ViewModel {

    private ContactDataModel dataModel;

    public ContactViewModel() {
        dataModel = new ContactDataModel();
    }

    /**
     * @param mContext
     * @return
     */
    public String[] getContactIdList(Context mContext) {
        return dataModel.getContactIdList(mContext);
    }

    public ContactModel getContactDetailModel(Context mContext, int contactId) {
        return dataModel.getContactDetailModel(mContext, contactId);
    }

    public void insertDefaultData(Context mContext) {
        dataModel.insertDefaultData(mContext);
    }


}