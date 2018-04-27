package com.roadhourse.spacepal.model.response;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

/**
 * Created by sidhu on 4/27/2018.
 */

public class Order extends RealmObject{
    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
    @SerializedName("orderId")
    String orderId;
    @SerializedName("isCompleted")
    boolean  isCompleted;
    @SerializedName("priority")
    int      priority;
}
