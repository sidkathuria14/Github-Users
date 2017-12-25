package com.example.sidkathuria14.githubapi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.sidkathuria14.githubapi.Models.User;
import com.example.sidkathuria14.githubapi.api.MainApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
EditText etInput;String username;
    public static final String TAG = "github";
    DatabaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         db = new DatabaseHandler(this);

etInput = (EditText)findViewById(R.id.etInput);


        ((Button)findViewById(R.id.btn)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username = etInput.getText().toString();
Call(username);
            }
        });


        Log.d(TAG, "onCreate: " + String.valueOf(db.getCount()) );
    }

    public void Call(String username){
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://api.github.com").
                addConverterFactory(GsonConverterFactory.create()).build();

        MainApi mainApi = retrofit.create(MainApi.class);

        Callback<User> callback = new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                Log.d(TAG, "onResponse: " + Integer.parseInt(String.valueOf(response.body().getFollowers())));
                db.addUser(new User(Integer.parseInt(String.valueOf(response.body().getId())),response.body().getName(),
                        Integer.parseInt(String.valueOf(response.body().getPublic_repos())), Integer.parseInt(String.valueOf(response.body().getPrivate_repos())),
                        Integer.parseInt(String.valueOf(response.body().getFollowers())),Integer.parseInt(String.valueOf(response.body().getFollowing())),
                        Integer.parseInt(String.valueOf(response.body().getPublic_gists())),
                        String.valueOf(response.body().getType()), String.valueOf(response.body().getBio())
                        ));
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.d(TAG, "onFailure: ");
            }
        } ;
        mainApi.get_repos(username).enqueue(callback);

    }



}

