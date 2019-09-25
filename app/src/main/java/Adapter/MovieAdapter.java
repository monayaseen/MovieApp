package Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.mymovieapp.DetailsActivity;
import com.example.mymovieapp.MainActivity;
import com.example.mymovieapp.R;
import com.squareup.picasso.Picasso;

import java.util.Collections;
import java.util.List;

import Model.Movie;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MyViewHolder> {
   // final private MovieItemClickListener onClickListener;
//    private LayoutInflater inflater;
    List<Movie> movieList ;
    private Context context;

    public MovieAdapter(Context context,List<Movie> movieList)
    {
        this.context=context;
        this.movieList=movieList;
    }

    @Override
    public MovieAdapter.MyViewHolder onCreateViewHolder(ViewGroup viewGroup,int i)
    {
        View view =LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.card,viewGroup,false);

        return new MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(final MovieAdapter.MyViewHolder viewHolder,int i)
    {

        viewHolder.title.setText(movieList.get(i).getTitle());
        String vote =Double.toString(movieList.get(i).getVoteAverage());
        viewHolder.rating.setText(vote);


        //Picasso.get()
          //      .load(movieList.get(i).getPosterPath());
                //.placeholder(R.drawable.load);
        Picasso.with(context)
                //.load(posterURL.toString())
                .load(movieList.get(i).getPosterPath())
                .resize(185,277).centerCrop()
                .into(viewHolder.image);

    }


    @Override
    public int getItemCount()
    {
        return movieList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView title , rating;
        public ImageView  image;
        public  MyViewHolder(View view)
        {
            super(view);
            title=(TextView) view.findViewById(R.id.movietitle);
            rating =(TextView) view.findViewById(R.id.movieRate);
            image =(ImageView) view.findViewById(R.id.image);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position=getAdapterPosition();
                    if(position!=RecyclerView.NO_POSITION)
                    {
                        Movie clickedDataItem = movieList.get(position);
                        Intent intent = new Intent(context, DetailsActivity.class);
                        intent.putExtra("Title",movieList.get(position).getTitle());
                        intent.putExtra("Image",movieList.get(position).getPosterPath());
                        intent.putExtra("OverView",movieList.get(position).getOverview());
                        intent.putExtra("AverageVote",Double.toString(movieList.get(position).getVoteAverage()));
                        intent.putExtra("RelaseDate",movieList.get(position).getReleaseDate());
                        intent.addFlags((Intent.FLAG_ACTIVITY_NEW_TASK));
                        context.startActivity(intent);
//                        Toast.makeText(v.getContext(),"YouClicked")

                    }
                }
            });
        }
    }





}