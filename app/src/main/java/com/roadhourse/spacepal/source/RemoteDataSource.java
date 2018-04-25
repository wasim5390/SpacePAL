package com.roadhourse.spacepal.source;


import com.roadhourse.spacepal.model.User;
import com.roadhourse.spacepal.model.response.APIError;
import com.roadhourse.spacepal.model.response.TokenResponse;
import com.roadhourse.spacepal.util.Util;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by sidhu on 4/11/2018.
 */

public class RemoteDataSource implements DataSource {

    private static RemoteDataSource instance;
    private static final String ERROR_MESSAGE = "Fail";

    private RemoteDataSource() {

    }

    public static RemoteDataSource getInstance() {
        if (instance==null) {
            instance = new RemoteDataSource();
        }
        return instance;
    }


    @Override
    public void getToken(String username, String password, String clientId, String clientSecret, String grantType, GetDataCallback<TokenResponse> callback) {
        Call<TokenResponse> call = RetrofitHelper.getInstanceForToken().getApi().getToken(username,password,clientId,clientSecret,grantType);
        call.enqueue(new Callback<TokenResponse>() {
            @Override
            public void onResponse(Call<TokenResponse> call, Response<TokenResponse> response) {
                if (response.isSuccessful()) {
                    callback.onDataReceived(response.body());
                } else {
                    APIError error = Util.parseError(response);
                    callback.onFailed(error.getHttpCode(), error.getResponseMsg());
                }
            }

            @Override
            public void onFailure(Call<TokenResponse> call, Throwable t) {
                callback.onFailed(0, ERROR_MESSAGE);
            }
        });
    }

    @Override
    public void updateAccount(User user, final GetResponseCallback<User> callback) {
        Call<User> call = RetrofitHelper.getInstance().getApi().updateAccount(user);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body());
                } else {
                    APIError error = Util.parseError(response);
                    callback.onFailed(error.getHttpCode(), error.getResponseMsg());
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                callback.onFailed(0, ERROR_MESSAGE);
            }
        });
    }

    @Override
    public void getAccount(GetDataCallback<User> callback) {
        Call<User> call = RetrofitHelper.getInstance().getApi().getAccount();
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    callback.onDataReceived(response.body());
                } else {
                    APIError error = Util.parseError(response);
                    callback.onFailed(error.getHttpCode(), error.getResponseMsg());
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                callback.onFailed(0, ERROR_MESSAGE);
            }
        });
    }


}
