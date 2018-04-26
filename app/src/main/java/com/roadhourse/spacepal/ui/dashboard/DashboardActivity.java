package com.roadhourse.spacepal.ui.dashboard;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.roadhourse.spacepal.BaseActivity;
import com.roadhourse.spacepal.R;

import butterknife.ButterKnife;

/**
 * Created by sidhu on 4/25/2018.
 */

public class DashboardActivity extends BaseActivity{
    @Override
    public int getID() {
        return R.layout.activity_dashboard;
    }

    @Override
    public void created(Bundle savedInstanceState) {
        ButterKnife.bind(this);
        // setToolBar(toolbar,getText(R.string.login),true);
        DashboardFragment dashboardFragment = DashboardFragment.newInstance();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.frameLayout, dashboardFragment);
        transaction.commit();
    }
}
