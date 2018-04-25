package com.roadhourse.spacepal.source;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.roadhourse.spacepal.AnnotationExclusionStrategy;
import com.roadhourse.spacepal.SpacePalApplication;
import com.roadhourse.spacepal.event.LoginFailEvent;
import com.roadhourse.spacepal.model.response.TokenResponse;
import com.roadhourse.spacepal.util.PreferenceUtil;
import com.roadhourse.spacepal.util.Util;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;

import javax.annotation.Nullable;

import okhttp3.Authenticator;
import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.Route;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by sidhu on 4/24/2018.
 */

public class RetrofitHelper {

    public static final String BASE_URL = "https://api-spa.stage.roadhouse.com.au";
    public static final String TOKEN_URL = "https://identity-spa.stage.roadhouse.com.au";

    private static RetrofitHelper instance;
    public Retrofit retrofit;
    API service;

    private static final String TAG = "RetrofitHelper";
    private RetrofitHelper (String baseUrl) {

        TokenResponse tokenResponse = PreferenceUtil.getInstance(SpacePalApplication.getInstance()).getTokenObj();

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();

        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .addInterceptor(new AuthorizationInterceptor(tokenResponse))
                .authenticator(new SpacePalAuthenticator(tokenResponse));

        Gson builder = new GsonBuilder().setExclusionStrategies(new AnnotationExclusionStrategy()).create();

        retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create(builder))
                .client(okHttpClientBuilder.build())
                .build();

        service = retrofit.create(API.class);
    }

    public static RetrofitHelper getInstanceForToken() {
        instance=null;
        instance = new RetrofitHelper(TOKEN_URL);
        return instance;
    }

    public static RetrofitHelper getInstance() {
        instance = null;
        instance = new RetrofitHelper(BASE_URL);
        return instance;
    }

    public API getApi() {
        return service;
    }



    public class AuthorizationInterceptor implements Interceptor {
        TokenResponse tokenObj;
        public AuthorizationInterceptor(TokenResponse tokenObj) {
            this.tokenObj = tokenObj;
        }

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request original = chain.request();
            // header will be access_token
            String header = Util.getAuthorizationHeader(tokenObj);
            if (header!=null) {
                Request request = original.newBuilder()
                        .header("Authorization", "Bearer " + header).build();


                Log.d(TAG, "intercept: add header "+header);
                Response response = chain.proceed(request);
                if (response.code()==401) {
                    EventBus.getDefault().post(new LoginFailEvent());
                }

                return response;
            } else {
                Log.d(TAG, "intercept: add no header");
                Response response=chain.proceed(original);
                return response;
            }

        }
    }

    public class SpacePalAuthenticator implements Authenticator {

        private TokenResponse tokenObj;
        private OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build();

        Gson gson = new Gson();

        public SpacePalAuthenticator(TokenResponse tokenObj) {
            this.tokenObj = tokenObj;
        }

        @Nullable
        @Override
        public Request authenticate(Route route, Response response) throws IOException {
            Request request = null;
            try {
                Request refreshTokenRequest = new Request.Builder()
                        .url(TOKEN_URL+"/connect/token") // uncomment: either place base_url in string as here or make your own url
                        .post(new FormBody.Builder()
                                .add("grant_type", "refresh_token")
                                .add("refresh_token", tokenObj.getRefreshToken()) // uncomment: add refreshtoken here
                                .add("client_id", "spacePAL.admin")
                                .add("client_secret", "8cef9b1d-973b-4d70-bd73-623e4d5782b2")
                                .build())
                        .build();
                Response result = client.newCall(refreshTokenRequest).execute();
                ResponseBody resultBody = result.body();

                if (result.isSuccessful() && resultBody != null) {
                    TokenResponse authResult = gson.fromJson(resultBody.string(),TokenResponse.class);
                    PreferenceUtil.getInstance(SpacePalApplication.getInstance()).saveTokenObject(authResult);


                    request = response.request().newBuilder()
                            .removeHeader("Authorization")
                            .addHeader("Authorization",  authResult.getTokenType()+ " " + authResult.getAccessToken())
                            .build();


                }

            } catch (Exception e){
//            Timber.w(e) { "Failed to refresh auth token" }
                Log.e("something bad happened", e.toString());
            }

            if (request == null) {
                //While all credentials as we no longer have a valid user
                PreferenceUtil.getInstance(SpacePalApplication.getInstance()).getTokenObj().setAccessToken("");
                PreferenceUtil.getInstance(SpacePalApplication.getInstance()).getTokenObj().setRefreshToken("");
            }

            return request;
        }
    }
}
