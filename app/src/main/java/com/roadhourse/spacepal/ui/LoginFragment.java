package com.roadhourse.spacepal.ui;

import android.os.Bundle;
import android.view.View;

import com.roadhourse.spacepal.BaseFragment;
import com.roadhourse.spacepal.R;

/**
 * Created by sidhu on 4/24/2018.
 */

public class LoginFragment extends BaseFragment {

    public static LoginFragment newInstance() {
        Bundle args = new Bundle();
        LoginFragment fragment = new LoginFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getID() {
    return R.layout.fragment_login;
    }


    @Override
    public void initUI(View view) {

    }


}
