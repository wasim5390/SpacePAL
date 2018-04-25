package com.roadhourse.spacepal.source;


import com.roadhourse.spacepal.model.User;
import com.roadhourse.spacepal.model.response.TokenResponse;

/**
 * Created by sidhu on 4/11/2018.
 */

public interface DataSource {

    void getToken(String username,String password,String clientId,String clientSecret,String grantType, GetDataCallback<TokenResponse> response);

    void updateAccount(User user, GetResponseCallback<User> callback);

    void getAccount(GetDataCallback<User> callback);
    //void forgotPassword(HashMap<String, Object> params, GetResponseCallback<GetVerifyMobileResponse> callback);


    interface GetDataCallback<M> {
        void onDataReceived(M data);
        void onFailed(int code, String message);
    }

    interface GetResponseCallback<M> {
        void onSuccess(M response);
        void onFailed(int code, String message);
    }
}
