package com.example.sidkathuria14.githubapi.api;

import com.example.sidkathuria14.githubapi.Models.SearchUser;
import com.example.sidkathuria14.githubapi.Models.User;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

import static android.R.attr.value;

/**
 * Created by sidkathuria14 on 25/12/17.
 */

public interface NameApi {
    @GET("search/users")
    Call<SearchUser> getSearchUser(@Query(value = "q",encoded = true)String name);
//    Call<SearchUser> getSearchUser(@Path("name")String name);
}
