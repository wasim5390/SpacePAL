package com.roadhourse.spacepal.model;

import com.google.gson.annotations.Expose;
import com.roadhourse.spacepal.model.response.Address;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by sidhu on 4/24/2018.
 */

public class User extends RealmObject {

    private String id;
    private String firstName;
    private String lastName;
    private String email;

    @Expose(serialize = false)
    private String password;

    @Expose(serialize = false)
    private boolean isSignIn;
    @Expose(serialize = false)
    private String token;
    private boolean isDisabled;
    private RealmList<String> roles;

    private String phoneNumber;
    private boolean isArchived;
    private String imageUrl;
    private String customerType;

    private RealmList<String> notes;

    private RealmList<Address> addresses;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isDisabled() {
        return isDisabled;
    }

    public void setDisabled(boolean disabled) {
        isDisabled = disabled;
    }

    public RealmList<String> getRoles() {
        return roles;
    }

    public void setRoles(RealmList<String> roles) {
        this.roles = roles;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean isArchived() {
        return isArchived;
    }

    public void setArchived(boolean archived) {
        isArchived = archived;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getCustomerType() {
        return customerType;
    }

    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }

    public RealmList<String> getNotes() {
        return notes;
    }

    public void setNotes(RealmList<String> notes) {
        this.notes = notes;
    }

    public RealmList<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(RealmList<Address> addresses) {
        this.addresses = addresses;
    }

    public boolean isSignIn() {
        return isSignIn;
    }

    public void setSignIn(boolean signIn) {
        isSignIn = signIn;
    }
}
