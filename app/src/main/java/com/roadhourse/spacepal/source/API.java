package com.roadhourse.spacepal.source;

import com.roadhourse.spacepal.model.User;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by sidhu on 4/11/2018.
 */

public interface API {

    @POST("/Users")
    Call<User> createAccount(@Body HashMap<String, Object> params);


    // TODO we will use below one for paginated query
    /*@GET("/v3/post/delivery-contractors/direct-mail/jobs")
    Call<GetAllJobsListResponse> getAllJobsLists(@Query("page") int page, @Query("limit") int limit);*/



}

