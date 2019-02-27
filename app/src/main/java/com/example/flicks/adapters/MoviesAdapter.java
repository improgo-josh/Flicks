package com.example.flicks.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.flicks.R;
import com.example.flicks.models.DetailActivity;
import com.example.flicks.models.Movie;

import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.ViewHolder> {

    Context context;
    List<Movie> movies;
    public MoviesAdapter(Context context, List<Movie> movies) {
        this.context = context;
        this.movies = movies;

    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d("apples", "onCreateViewHolder");
        View view = LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d("apples", "onBindViewHolder" + position);
        Movie movie = movies.get(position);
        //Bind the movie data into the view holder
        holder.bind(movie);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_title;
        TextView tv_overview;
        ImageView iv_poster;
        RelativeLayout container;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_title = itemView.findViewById(R.id.tv_title);
            tv_overview = itemView.findViewById(R.id.tv_overview);
            iv_poster = itemView.findViewById(R.id.iv_poster);
            container = itemView.findViewById(R.id.container);
        }

        public void bind(final Movie movie) {
            tv_title.setText(movie.getTitle());
            tv_overview.setText(movie.getOverview());
            // Reference the backdrop path if phone is in landscape
            String imageUrl = movie.getPosterPath();

            if (context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                Log.d("apples", "IS IN LANDSCAPE!");
                imageUrl = movie.getBackdropPath();
            }
            Glide.with(context)
                    .load(imageUrl)
                    /*.apply(new RequestOptions()
                    .placeholder(R.drawable.ic_x_mark)
                    .error(R.drawable.ic_imgnotfound))*/
                    .into(iv_poster);

            /*container.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d("apples", "CLICKED" + tv_title.getText());
                }*/
            /*        Intent i = new Intent(context, DetailActivity.class);
                    i.putExtra("movie", Parcels.wrap(movie));
                    context.startActivity(i);
                }*/
//            });
        }
    }
}
