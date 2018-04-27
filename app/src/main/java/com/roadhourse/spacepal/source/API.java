package com.roadhourse.spacepal.source;

import com.roadhourse.spacepal.model.Role;
import com.roadhourse.spacepal.model.User;
import com.roadhourse.spacepal.model.response.Order;
import com.roadhourse.spacepal.model.response.TokenResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by sidhu on 4/11/2018.
 */

public interface API {

    @POST("/connect/token")
    @FormUrlEncoded
    Call<TokenResponse> getToken(@Field("username") String username, @Field("password") String password,
                                 @Field("client_id") String clientId,
                                 @Field("client_secret") String clientSecret,
                                 @Field("grant_type") String grantType);

    /*******************Roles*****************/

    @GET("/v1/Role")
    Call<List<Role>> getRoles();

    /*************************************/

    /********************* Account ************/
    @POST("/v1/Users")
    Call<User> createAccount(@Body User user);

    @POST("/v1/Users")
    Call<User> updateAccount(@Body User user);

    @GET("/v1/Users/Me")
    Call<User> getAccount();

    /****************************************/

    @GET("/v1/Order/Dash")
    Call<List<Order>> getOrders(@Query("role") String role);
    // TODO we will use below one for paginated query
    /*@GET("/v3/post/delivery-contractors/direct-mail/jobs")
    Call<GetAllJobsListResponse> getAllJobsLists(@Query("page") int page, @Query("limit") int limit);*/



}

