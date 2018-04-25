package com.roadhourse.spacepal.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.roadhourse.spacepal.model.User;
import com.roadhourse.spacepal.model.response.TokenResponse;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by sidhu on 4/24/2018.
 */

public class PreferenceUtil {


    public static final String KEY_IS_SIGN_IN = "is_sign_in";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_PASSWORD = "password";
    public static final String KEY_USER = "user";
    public static final String KEY_TOKEN = "token_object";
    private static final String PREFERENCE_NAME = "send_signal_preference";

    private static PreferenceUtil instance;

    private SharedPreferences sPref;
    private PreferenceUtil(Context context) {
        sPref = context.getSharedPreferences(PREFERENCE_NAME,MODE_PRIVATE);
    }
    public static PreferenceUtil getInstance(Context context) {
        if (instance == null) {
            instance = new PreferenceUtil(context);
        }
        return instance;
    }

    public boolean isSignIn() {
        return sPref.getBoolean(KEY_IS_SIGN_IN, false);
    }

    public void setSignIn(boolean value) {
        SharedPreferences.Editor editor = sPref.edit();
        editor.putBoolean(KEY_IS_SIGN_IN, value);
        editor.apply();
    }

    public String getUsername() {
        return sPref.getString(KEY_EMAIL, "");
    }

    public String getPassword() {
        return sPref.getString(KEY_PASSWORD, "");
    }

    public void saveEmail(String email) {
        SharedPreferences.Editor editor = sPref.edit();
        editor.putString(KEY_EMAIL, email);
        editor.apply();
    }

    public void savePassword(String password) {
        SharedPreferences.Editor editor = sPref.edit();
        editor.putString(KEY_PASSWORD, password);
        editor.apply();
    }

    public void saveAccount(User user) {
        Gson gson = new Gson();
        String str = gson.toJson(user);
        SharedPreferences.Editor editor = sPref.edit();
        editor.putString(KEY_USER, str);
        editor.apply();
    }

    public User getAccount() {
        Gson gson = new Gson();
        User user = gson.fromJson(sPref.getString(KEY_USER, ""), User.class);

        if (user==null) {
            user = new User();
        }
        return user;
    }

    public void saveTokenObject(TokenResponse tokenObj) {
        Gson gson = new Gson();
        String str = gson.toJson(tokenObj);
        SharedPreferences.Editor editor = sPref.edit();
        editor.putString(KEY_TOKEN, str);
        editor.apply();
    }

    public TokenResponse getTokenObj() {
        Gson gson = new Gson();
        TokenResponse tokenResponse = gson.fromJson(sPref.getString(KEY_TOKEN, ""), TokenResponse.class);

        if (tokenResponse==null) {
            tokenResponse = new TokenResponse();
        }
        return tokenResponse;
    }

    public void savePreference(String key, String value) {
        SharedPreferences.Editor editor = sPref.edit();
        editor.putString(key, value);
        editor.apply();
    }




    public String getPreference(String key) {
        return sPref.getString(key, "");
    }

    public void clearAllPreferences() {

        SharedPreferences.Editor editor = sPref.edit();
        editor.clear();
        editor.apply();

    }

}
