package com.cinema.cinema.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.cinema.cinema.Model.Movie;
import com.cinema.cinema.R;
import java.util.List;

public class HomePageFeaturedAdapter extends
        RecyclerView.Adapter<HomePageFeaturedAdapter.ViewHolder> {
  private List<Movie> moviesList;
  private Context context;
  private HomePageFeaturedAdapter.OnItemClickListener mOnItemClickListener;
  /**
     * <p>'p'</p>. OK
     */
  public HomePageFeaturedAdapter(Context context, List<Movie> moviesList,
                                   OnItemClickListener mOnItemClickListener) {
    this.moviesList = moviesList;
    this.context = context;
    this.mOnItemClickListener = mOnItemClickListener;
  }
  @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
    View listItem = layoutInflater.inflate(R.layout.home_page_featured_list_item, parent, false);
    ViewHolder viewHolder = new ViewHolder(listItem, mOnItemClickListener);
    return viewHolder;
  }

  @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
    final Movie movie = moviesList.get(position);
    holder.title.setText(movie.getTitle());
    holder.cataloge.setText(movie.getType());
    holder.retting.setText(movie.getRating());
    holder.date.setText("(" + movie.getYear() +")");

    Glide.with(context).load(movie.getUrlPoster()).into(holder.iv_image);

  }


  @Override
    public int getItemCount() {
    return moviesList.size();
  }

  public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView title;
    public TextView cataloge;
    public TextView retting;
    public TextView date;
    public ImageView iv_image;
    HomePageFeaturedAdapter.OnItemClickListener onItemClickListener;
    /**
         * <p>'p'</p>. OK
         */
    public ViewHolder(View itemView, OnItemClickListener onItemClickListener) {
      super(itemView);
      this.title =  itemView.findViewById(R.id.tv_Title);
      this.cataloge =  itemView.findViewById(R.id.tv_cataloge);
      this.retting =  itemView.findViewById(R.id.tv_ratting);
      this.date =  itemView.findViewById(R.id.tv_date);
      this.iv_image =  itemView.findViewById(R.id.filmIv);

      this.onItemClickListener = onItemClickListener;
      itemView.setOnClickListener(this);
    }

    @Override
        public void onClick(View view) {
      onItemClickListener.onItemClick(getAdapterPosition());

    }
  }
  public interface OnItemClickListener {
    void onItemClick(int position);

  }
}