package com.example.mymovieapp;

import android.app.AppComponentFactory;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

public class DetailsActivity  extends AppCompatActivity {

    private ImageView imageView;
    private TextView titleView;
    private TextView releaseDateView;
    private TextView ratingView;
    private TextView overviewView;

    @Override
public void onCreate(Bundle savedInsanceState)
{
    super.onCreate(savedInsanceState);
    setContentView(R.layout.details);
   // Toolbar toolbar=(Toolbar) findViewById(R.id.)

    imageView=(ImageView)findViewById(R.id.image);
    titleView=(TextView)findViewById(R.id.movietitle);
   releaseDateView=(TextView)findViewById(R.id.releaseDate);
    ratingView=(TextView)findViewById(R.id.rate);
    overviewView=(TextView)findViewById(R.id.overview);

    Intent intent1=getIntent();
    if(intent1.hasExtra("Title"))
    {
        String image=getIntent().getExtras().getString("Image");
        String title=getIntent().getExtras().getString("Title");
        String overview=getIntent().getExtras().getString("Overview");
        String rating=getIntent().getExtras().getString("AverageVote");
        String releaseDate=getIntent().getExtras().getString("RelaseDate");


        Picasso.with(getBaseContext())
                .load(image)
                .into(imageView);

titleView.setText(title);
overviewView.setText(overview);
ratingView.setText(rating);
releaseDateView.setText(releaseDate);

    }
    else
        Toast.makeText(this,"No Data Avilable",Toast.LENGTH_SHORT).show();


}







}
