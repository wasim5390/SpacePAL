package com.roadhourse.spacepal.source;

import android.support.annotation.NonNull;

import com.roadhourse.spacepal.model.User;
import com.roadhourse.spacepal.model.response.TokenResponse;

/**
 * Created by sidhu on 4/11/2018.
 */

public class Repository implements DataSource{

    private static Repository instance;
    private final RemoteDataSource mRemoteDataSource;

    private Repository(@NonNull RemoteDataSource remoteDataSource) {
        mRemoteDataSource =  remoteDataSource;
    }

    public static Repository getInstance(RemoteDataSource remoteDataSource) {

        if (instance==null) {
            instance = new Repository(remoteDataSource);
        }
        return instance;
    }



    @Override
    public void getToken(String username, String password, String clientId, String clientSecret, String grantType, GetDataCallback<TokenResponse> callback) {
        mRemoteDataSource.getToken(username,password,clientId,clientSecret,grantType,new GetDataCallback<TokenResponse>() {
            @Override
            public void onDataReceived(TokenResponse data) {
                callback.onDataReceived(data);
            }

            @Override
            public void onFailed(int code, String message) {
                callback.onFailed(code, message);
            }
        });
    }

    @Override
    public void updateAccount(User user, final GetResponseCallback<User> callback) {
        mRemoteDataSource.updateAccount(user, new GetResponseCallback<User>() {
            @Override
            public void onSuccess(User response) {
                callback.onSuccess(response);
            }

            @Override
            public void onFailed(int code, String message) {
                callback.onFailed(code, message);
            }
        });
    }

    @Override
    public void getAccount(final GetDataCallback<User> callback) {
        mRemoteDataSource.getAccount(new GetDataCallback<User>() {
            @Override
            public void onDataReceived(User data) {
                callback.onDataReceived(data);
            }

            @Override
            public void onFailed(int code, String message) {
                callback.onFailed(code, message);
            }
        });
    }


}
