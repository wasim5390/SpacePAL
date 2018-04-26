package com.roadhourse.spacepal.ui.login;


import com.roadhourse.spacepal.Constant;
import com.roadhourse.spacepal.model.Role;
import com.roadhourse.spacepal.model.User;
import com.roadhourse.spacepal.model.response.APIError;
import com.roadhourse.spacepal.model.response.TokenResponse;
import com.roadhourse.spacepal.source.RetrofitHelper;
import com.roadhourse.spacepal.util.PreferenceUtil;
import com.roadhourse.spacepal.util.Util;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created on 25/10/2017.
 */

public class LoginPresenter implements LoginContract.Presenter,Constant{

    private LoginContract.View loginView;
    private PreferenceUtil preferenceUtil;

    public LoginPresenter(LoginContract.View view, PreferenceUtil preferenceUtil) {

        this.loginView = view;
        this.loginView.setPresenter(this);
        this.preferenceUtil = preferenceUtil;

    }

    @Override
    public void start() {

    }

    @Override
    public void signIn() {
        loginView.showProgressDialog(true);

        Call<User> call = RetrofitHelper.getInstance().getApi().getAccount();
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                loginView.showProgressDialog(false);
                if (response.code()==200) {
                    loginView.signInIsSuccessful(response.body());

                } else {
                    APIError error = Util.parseError(response);
                    loginView.showMessage(error.getResponseMsg(),true);

                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                loginView.showProgressDialog(false);
                loginView.showMessage("Fail...",true);
                t.printStackTrace();
            }
        });


    }

    @Override
    public void getToken(String username, String password) {
        preferenceUtil.saveTokenObject(null);
        if (username==null||username.trim().isEmpty()) {
            loginView.showErrorOnEmail("Please enter email");
            return;
        }

        if (password==null||password.trim().isEmpty()) {
            loginView.showErrorOnPassword("Please enter password");
            return;
        }

        loginView.showProgressDialog(true);

        Call<TokenResponse> call = RetrofitHelper.getInstanceForToken().getApi().getToken(username,password,CLIENT_ID,CLIENT_SECRET,GRANT_TYPE);
        call.enqueue(new Callback<TokenResponse>() {
            @Override
            public void onResponse(Call<TokenResponse> call, Response<TokenResponse> response) {
                loginView.showProgressDialog(false);
                if (response.code()==200) {
                    loginView.tokenRetrieved(response.body());

                } else {
                    APIError error = Util.parseError(response);
                    loginView.showMessage(error.getResponseMsg(),true);
                }
            }

            @Override
            public void onFailure(Call<TokenResponse> call, Throwable t) {
                loginView.showProgressDialog(false);
                loginView.showMessage("Fail...",true);
                t.printStackTrace();
            }
        });
    }

    @Override
    public void getRoles() {

        loginView.showProgressDialog(true);

        Call<List<Role>> call = RetrofitHelper.getInstance().getApi().getRoles();
        call.enqueue(new Callback<List<Role>>() {
            @Override
            public void onResponse(Call<List<Role>> call, Response<List<Role>> response) {
                loginView.showProgressDialog(false);
                if (response.code()==200) {
                    loginView.rolesRetrieved(response.body());

                } else {
                    APIError error = Util.parseError(response);
                    loginView.showMessage(error.getResponseMsg(),true);
                }
            }

            @Override
            public void onFailure(Call<List<Role>> call, Throwable t) {
                loginView.showProgressDialog(false);
                loginView.showMessage("Fail...",true);
                t.printStackTrace();
            }
        });
    }
}
