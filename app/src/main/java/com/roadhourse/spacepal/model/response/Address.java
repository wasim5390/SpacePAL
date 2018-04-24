package com.roadhourse.spacepal.model.response;

import io.realm.RealmObject;

/**
 * Created by sidhu on 4/24/2018.
 */

public class Address extends RealmObject{
    private boolean isPrimary;
    private String    googleAddress;
    private String   lat;
    private String  lng;
    public Address() {
    }

    public Address(boolean isPrimary, String googleAddress, String lat, String lng) {
        this.isPrimary = isPrimary;
        this.googleAddress = googleAddress;
        this.lat = lat;
        this.lng = lng;
    }
    public boolean isPrimary() {
        return isPrimary;
    }

    public void setPrimary(boolean primary) {
        isPrimary = primary;
    }

    public String getGoogleAddress() {
        return googleAddress;
    }

    public void setGoogleAddress(String googleAddress) {
        this.googleAddress = googleAddress;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }


}
