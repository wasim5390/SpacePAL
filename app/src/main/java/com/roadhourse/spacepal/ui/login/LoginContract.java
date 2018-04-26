package com.roadhourse.spacepal.ui.login;


import com.roadhourse.spacepal.BasePresenter;
import com.roadhourse.spacepal.BaseView;
import com.roadhourse.spacepal.model.Role;
import com.roadhourse.spacepal.model.User;
import com.roadhourse.spacepal.model.response.TokenResponse;

import java.util.List;

/**
 * Created on 25/10/2017.
 */

public class LoginContract {

    interface View extends BaseView<Presenter> {
        void signInIsSuccessful(User user);
        void tokenRetrieved(TokenResponse response);
        void rolesRetrieved(List<Role> roles);
        void showMessage(String text, boolean alert);
        void showErrorOnEmail(String error);
        void showErrorOnPassword(String error);
        void showProgressDialog(boolean isInProgress);

    }


    interface Presenter extends BasePresenter {
        void signIn();
        void getToken(String username,String password);
        void getRoles();
    }
}
