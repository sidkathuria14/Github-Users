package com.example.sidkathuria14.githubapi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.sidkathuria14.githubapi.Models.Main_Model;
import com.example.sidkathuria14.githubapi.api.MainApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
EditText etInput;String username;
    public static final String TAG = "github";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
etInput = (EditText)findViewById(R.id.etInput);


        ((Button)findViewById(R.id.btn)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username = etInput.getText().toString();
Call(username);
            }
        });



    }

    public void Call(String username){
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://api.github.com").
                addConverterFactory(GsonConverterFactory.create()).build();

        MainApi mainApi = retrofit.create(MainApi.class);

        Callback<Main_Model> callback = new Callback<Main_Model>() {
            @Override
            public void onResponse(Call<Main_Model> call, Response<Main_Model> response) {
                Log.d(TAG, "onResponse: " + Integer.parseInt(String.valueOf(response.body().getFollowers())));
            }

            @Override
            public void onFailure(Call<Main_Model> call, Throwable t) {

            }
        } ;
        mainApi.get_repos(username).enqueue(callback);

    }


}
