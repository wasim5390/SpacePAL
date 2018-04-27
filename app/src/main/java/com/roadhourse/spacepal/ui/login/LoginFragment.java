package com.roadhourse.spacepal.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.roadhourse.spacepal.BaseFragment;
import com.roadhourse.spacepal.R;
import com.roadhourse.spacepal.model.User;
import com.roadhourse.spacepal.model.response.TokenResponse;
import com.roadhourse.spacepal.ui.dashboard.DashboardActivity;
import com.roadhourse.spacepal.util.PreferenceUtil;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by sidhu on 4/24/2018.
 */

public class LoginFragment extends BaseFragment implements LoginContract.View{

    @BindView(R.id.textInputLayoutEmail)
    TextInputLayout tilEmail;
    @BindView(R.id.textInputLayoutPassword)
    TextInputLayout tilPassword;
    @BindView(R.id.editTextEmail)
    TextInputEditText etEmail;
    @BindView(R.id.editTextPassword)
    TextInputEditText etPassword;
    @BindView(R.id.btnLogin)
    Button btnLogin;



    LoginContract.Presenter presenter;

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

    @Override
    public void setPresenter(LoginContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void signInIsSuccessful(User user) {

        boolean accessible=false;
        for(String role:user.getRoles()) {
            if (role.toUpperCase().equals(ROLE_PICKER) || role.toUpperCase().equals(ROLE_DRIVER)){
                accessible = true;
                break;
            }
        }
        if(accessible)
        {
            PreferenceUtil.getInstance(getActivity()).saveAccount(user);
            Intent intent = new Intent(getContext(), DashboardActivity.class);
            startActivity(intent);
        }
        else
            Toast.makeText(getActivity(),"Don't have privilege to access this app",Toast.LENGTH_SHORT).show();

    }

    @Override
    public void tokenRetrieved(TokenResponse tokenResponse) {
        PreferenceUtil.getInstance(getActivity()).saveTokenObject(tokenResponse);
        presenter.signIn();
    }



    @Override
    public void showMessage(String text, boolean alert) {
        Toast.makeText(mBaseActivity, text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showErrorOnEmail(String error) {
        tilEmail.setErrorEnabled(true);
        tilEmail.setError(error);
    }

    @Override
    public void showErrorOnPassword(String error) {
    tilPassword.setError(error);
    tilPassword.setErrorEnabled(true);
    }

    @Override
    public void showProgressDialog(boolean isInProgress) {
        if(isInProgress)
            showProgress();
        else
            hideProgress();

    }

    @OnClick(R.id.btnLogin)
    public void onLoginClick(){

        //presenter.getToken("admin@roadhouse.com.au","123123");
        presenter.getToken(etEmail.getText().toString(),etPassword.getText().toString());
    }

}
