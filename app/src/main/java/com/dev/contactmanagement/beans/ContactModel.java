package com.dev.contactmanagement.beans;


import android.databinding.BaseObservable;

public class ContactModel extends BaseObservable {

    private String stagingId;
    private String context;
    private int status;
    private String userId;

    public String getStagingId() {
        return stagingId;
    }

    public void setStagingId(String stagingId) {
        this.stagingId = stagingId;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
