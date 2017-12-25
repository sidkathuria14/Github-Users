package com.example.sidkathuria14.githubapi.api;

import com.example.sidkathuria14.githubapi.Models.User;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by sidkathuria14 on 25/12/17.
 */

public interface UsernameApi {
    @GET("/users/{username}")
    Call<User> get_repos(@Path("username")String username);
}
