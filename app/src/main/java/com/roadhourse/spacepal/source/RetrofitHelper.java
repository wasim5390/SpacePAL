package com.roadhourse.spacepal.source;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.roadhourse.spacepal.AnnotationExclusionStrategy;
import com.roadhourse.spacepal.SpacePalApplication;
import com.roadhourse.spacepal.event.LoginFailEvent;
import com.roadhourse.spacepal.util.Util;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by sidhu on 4/24/2018.
 */

public class RetrofitHelper {

    public static final String BASE_URL = "https://api-spa.stage.roadhouse.com.au/v1/";
    public static final String TOKEN_URL = "";
    private static RetrofitHelper instance;
    public Retrofit retrofit;
    API service;

    private static final String TAG = "RetrofitHelper";
    private RetrofitHelper () {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .addInterceptor(new AuthorizationInterceptor());
        Gson builder = new GsonBuilder().setExclusionStrategies(new AnnotationExclusionStrategy()).create();
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(builder))
                .client(okHttpClientBuilder.build())
                .build();

        service = retrofit.create(API.class);
    }

    public static RetrofitHelper getInstance() {
        if (instance==null) {
            instance = new RetrofitHelper();
        }
        return instance;
    }


    public API getApi() {
        return service;
    }


    public class AuthorizationInterceptor implements Interceptor {

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request original = chain.request();

            String header = Util.getAuthorizationHeader(SpacePalApplication.getInstance());
            if (header!=null) {
                Request request = original.newBuilder()
                        .header("Authorization", "Basic " + header).build();
                Log.d(TAG, "intercept: add header");
                Response response = chain.proceed(request);
                if (response.code()==401) {
                    EventBus.getDefault().post(new LoginFailEvent());
                }

                return response;
            } else {
                Log.d(TAG, "intercept: do not add header");
                return chain.proceed(original);
            }



        }
    }
}
