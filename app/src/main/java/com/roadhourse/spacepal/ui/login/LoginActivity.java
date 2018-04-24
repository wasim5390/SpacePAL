package com.roadhourse.spacepal.ui.login;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;

import com.roadhourse.spacepal.BaseActivity;
import com.roadhourse.spacepal.R;
import com.roadhourse.spacepal.ui.LoginFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by sidhu on 4/24/2018.
 */

public class LoginActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    public int getID() {
        return R.layout.activity_login;
    }

    @Override
    public void created(Bundle savedInstanceState) {
        ButterKnife.bind(this);
       // setToolBar(toolbar,getText(R.string.login),true);
        LoginFragment loginFragment = LoginFragment.newInstance();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.frameLayout, loginFragment);
        transaction.commit();
    }
}
