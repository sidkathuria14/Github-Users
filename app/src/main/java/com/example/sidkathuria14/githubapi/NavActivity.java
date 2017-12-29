package com.example.sidkathuria14.githubapi;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import com.example.sidkathuria14.githubapi.Models.SearchUser;
import com.example.sidkathuria14.githubapi.Models.User;
import com.example.sidkathuria14.githubapi.adapter.UserRecyclerAdapter;
import com.example.sidkathuria14.githubapi.api.NameApi;
import com.example.sidkathuria14.githubapi.api.UsernameApi;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NavActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    EditText etInput;String username;
    public static final String TAG = "github";
    DatabaseHandler db;
    int cnt = 0;
    ProgressDialog progress;

Retrofit retrofit;
    RecyclerView recyclerView;
    UserRecyclerAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav);

recyclerView = (RecyclerView)findViewById(R.id.rv);
        adapter = new UserRecyclerAdapter(this,new ArrayList<SearchUser>());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);


        retrofit = new Retrofit.Builder().baseUrl("https://api.github.com/").
                addConverterFactory(GsonConverterFactory.create()).build();

        db = new DatabaseHandler(this);
        etInput = (EditText) findViewById(R.id.etInput);
        ((Button)findViewById(R.id.btn)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                etInput.setText("");
                progress.show();
                username = etInput.getText().toString();
                CallSearchName(username);
//CallUsername(username);

            }
        });


        Log.d(TAG, "onCreate: size of db = " + String.valueOf(db.getCount()) );
        progress = new ProgressDialog(this);
        progress.setMessage("Searching!");
        progress.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progress.setIndeterminate(true);
        progress.setProgress(0);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.nav, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();




        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

public void CallSearchName(String name){
    NameApi nameApi = retrofit.create(NameApi.class);
    Callback<SearchUser> callback = new Callback<SearchUser>() {
        @Override
        public void onResponse(Call<SearchUser> call, Response<SearchUser> response) {
            progress.dismiss();
            Log.d(TAG, "onResponse: " + response.isSuccessful());

            while(response.isSuccessful()){
                Log.d(TAG, "onResponse: callsearchname" + response.body().getIncomplete_results());
                cnt +=1;
            }
            Log.d(TAG, "onResponse: cnt = " + cnt);
            if (response.isSuccessful()) {
                Log.d(TAG, "onResponse: searchname" + " " + response.body().getItems()[0].getScore());
            }
        }

        @Override
        public void onFailure(Call<SearchUser> call, Throwable t) {
            Log.d(TAG, "onFailure: searchname");
            progress.dismiss();
        }
    };
    nameApi.getSearchUser(name).enqueue(callback);
    Log.d(TAG, "CallSearchName: " + nameApi.getSearchUser(name).request());
}
//
//    public void CallUsername(final String username){
//
//        UsernameApi usernameApi = retrofit.create(UsernameApi.class);
//        Callback<User> callback = new Callback<User>() {
//            @Override
//            public void onResponse(Call<User> call, Response<User> response) {
//                Log.d(TAG, "onResponse: username" + response.isSuccessful());
//                if(response.isSuccessful() == false){
//                    Log.d(TAG, "onResponse: if condition false");
//                    CallSearchName(username);
//                }
////                Log.d(TAG, "onResponse: username" + Integer.parseInt(String.valueOf(response.body().getFollowers())));
//               else {
//                    db.addUser(new User(Integer.parseInt(String.valueOf(response.body().getId())), response.body().getName(),
//                            Integer.parseInt(String.valueOf(response.body().getPublic_repos())), Integer.parseInt(String.valueOf(response.body().getPrivate_repos())),
//                            Integer.parseInt(String.valueOf(response.body().getFollowers())), Integer.parseInt(String.valueOf(response.body().getFollowing())),
//                            Integer.parseInt(String.valueOf(response.body().getPublic_gists())),
//                            String.valueOf(response.body().getType()), String.valueOf(response.body().getBio())
//                    ));
//                    Log.d(TAG, "onResponse: username" + db.getCount());
//                    progress.dismiss();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<User> call, Throwable t) {
//                progress.dismiss();Log.d(TAG, "onFailure: ");
////                CallSearchName(username);
//            }
//
//        } ;
//        usernameApi.get_repos(username).enqueue(callback);
//
//    }


}
