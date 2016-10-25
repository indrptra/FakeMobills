package com.indrap.FakeMobills;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by mycomputer on 19/10/16.
 */

public interface UserApi {
    @GET("/users")
    Call<Users> getUsers();

    @GET("/users/auth")
    Call<User> getUser();

    @GET("users/{email}/{password}")
    Call<User> Login(@Query("email") String email, @Query("password") String password);

    @PUT("/users{id}")
    Call<User> updateUser(@Path("id") int user_id, @Body User user);


    @POST("/users")
    Call<User> saveUser(@Body User user);

    @DELETE("/users{id}")
    Call<User> deleteUser(@Path("id") String user_id);

}
