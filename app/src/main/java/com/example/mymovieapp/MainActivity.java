package com.example.mymovieapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import Adapter.MovieAdapter;
import Api.Client;
import Api.Service;
import Model.Movie;
import Model.MovieResponce;
//import retrofit.Call;
//import retrofit.Callback;
//import retrofit.Response;
//import retrofit.Retrofit;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private MovieAdapter adapter;
    private List<Movie> movieList;

   // ProgressDialog pd;

    public static final String Tag= MovieAdapter.class.getName();
    private SwipeRefreshLayout SwipeContainer;

    // private SwipeRefreshLayout SwipeContainer;
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        InitView();

        //SwipeContainer =(SwipeRefreshLayout) findViewById(R.id.details);

    }


    public Activity getActivity()
    {
        Context context=this;
        while (context instanceof ContextWrapper){
            if (context instanceof Activity)
            {return (Activity) context;}
            context = ((ContextWrapper) context).getBaseContext();
        }
        return null;
    }
   private void InitView()
   {
//        pd =new ProgressDialog(this);
//        pd.setMessage("Waiting...");
//        pd.setCancelable(false);
//        pd.show();

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        movieList= new ArrayList<>();
        adapter = new MovieAdapter(this,movieList);

        if(getActivity().getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT)
        {
            mRecyclerView.setLayoutManager(new GridLayoutManager(this,2));
        }

        else
            mRecyclerView.setLayoutManager(new GridLayoutManager(this,4));

        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(adapter);
//        adapter.notiftDataSetChanged();
       adapter.notifyDataSetChanged();
        loadJSON();

    }


    private void loadJSON()
    {
    try {
        if (BuildConfig.THE_MOVIE_DB_API_TOKEN.isEmpty()) {
            Toast.makeText(getApplicationContext(), "No API key ", Toast.LENGTH_SHORT).show();
            //pd.dismiss();
            return;
        }
        Client client=new Client();
        Service apiService=client.getClient().create(Service.class);
        Call<MovieResponce> call=  apiService.getPopularMovie(BuildConfig.THE_MOVIE_DB_API_TOKEN);
        call.enqueue(new Callback<MovieResponce>() {
            @Override
            public void onResponse(Call<MovieResponce> call, Response<MovieResponce> response) {
               try {
                   if (response.body() != null){
                       List<Movie>  movies =response.body().getResults();
                   mRecyclerView.setAdapter(new MovieAdapter(getApplicationContext(),movies));
                   mRecyclerView.smoothScrollToPosition(0);
                   }
               } catch (Exception e) {
                   e.printStackTrace();
                   Toast.makeText(MainActivity.this, "exeption error",Toast.LENGTH_SHORT).show();

               }


            }

            @Override
            public void onFailure(Call<MovieResponce> call, Throwable t) {
                Log.d("Erroooor",t.getMessage());
                Toast.makeText(MainActivity.this, "Error fetchingdata",Toast.LENGTH_SHORT).show();
            }
        });


    }
    catch (Exception e)
    {
        Log.d("Errroor", e.getMessage());
        Toast.makeText(this,e.toString(),Toast.LENGTH_SHORT).show();
    }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {


            case R.id.action_sortMP:
            return true;
            case R.id.action_sortTR:
                return false;
            default:
                return super.onOptionsItemSelected(item);
        }
        }

}


