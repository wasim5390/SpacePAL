package com.roadhourse.spacepal;

import android.app.Application;
import android.content.Intent;
import android.util.Log;

import com.roadhourse.spacepal.event.LoginFailEvent;
import com.roadhourse.spacepal.model.realm.RealmController;
import com.roadhourse.spacepal.ui.login._LoginActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by sidhu on 4/24/2018.
 */

public class SpacePalApplication extends Application implements AppLifecycleHandler.LifeCycleDelegate {
    private static SpacePalApplication instance;
    private AppLifecycleHandler lifeCycleHandler;
    private boolean isForeground=true;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        EventBus.getDefault().register(this);
        Realm.init(this);
        lifeCycleHandler = new AppLifecycleHandler(this);
        registerLifecycleHandler(lifeCycleHandler);
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder()
                .name("Realm.SpacePal")
                .schemaVersion(0)
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(realmConfiguration);
    }

    public static SpacePalApplication getInstance() {
        return instance==null?new SpacePalApplication():instance;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(LoginFailEvent event) {
        if (RealmController.with(this).getInstance().getUser().isSignIn()) {
            logout();
        }
    }



    public void logout() {

        RealmController.with(this).getUser().deleteFromRealm();
        Intent intent = new Intent(this, _LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    public void onAppBackgrounded() {
        isForeground=false;
        Log.d("SpacePalApplication"," App in background");
    }

    @Override
    public void onAppForegrounded() {
        isForeground = true;
        Log.d("SpacePalApplication"," App in foreground");
    }

    public boolean isForeground() {
        return isForeground;
    }

    private void registerLifecycleHandler(AppLifecycleHandler lifeCycleHandler) {
        registerActivityLifecycleCallbacks(lifeCycleHandler);
        registerComponentCallbacks(lifeCycleHandler);
    }
}
