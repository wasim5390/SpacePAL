package com.roadhourse.spacepal.model.realm;

import android.app.Activity;
import android.app.Application;
import android.support.v4.app.Fragment;

import com.roadhourse.spacepal.model.Role;
import com.roadhourse.spacepal.model.response.Order;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmObject;



/**
 * Created by sidhu on 4/24/2018.
 */

public class RealmController {

    private static RealmController instance;
    private final Realm realm;

    public RealmController(Application application) {
        realm = Realm.getDefaultInstance();
    }

    public static RealmController with(Fragment fragment) {

        if (instance == null) {
            instance = new RealmController(fragment.getActivity().getApplication());
        }
        return instance;
    }

    public static RealmController with(Activity activity) {

        if (instance == null) {
            instance = new RealmController(activity.getApplication());
        }
        return instance;
    }

    public static RealmController with(Application application) {

        if (instance == null) {
            instance = new RealmController(application);
        }
        return instance;
    }

    public static RealmController getInstance() {

        return instance;
    }

    public Realm getRealm() {

        return realm;
    }

    /*********** Saving object must be RealmObject *******/
    public void save(Object mLocation){
        realm.beginTransaction();
        realm.copyToRealm((RealmObject)mLocation);
        realm.commitTransaction();
    }


    public void saveRoles(List<Role> roleList){
        realm.executeTransaction(realm -> {
            realm.where(Role.class).findAll().deleteAllFromRealm();
            realm.insert(roleList);
        });

    }

    public void saveOrders(List<Order> orderList){
        realm.executeTransaction(realm -> {
            realm.where(Order.class).findAll().deleteAllFromRealm();
            realm.copyToRealm(orderList);
        });

    }

    public List<Order> getOrders(){
        return realm.copyFromRealm(realm.where(Order.class).findAll());
    }

    public List<Role> getRoles(){
        return realm.copyFromRealm(realm.where(Role.class).findAll());
    }



    //Refresh the realm istance
    public void refresh() {

        realm.refresh();
    }



}