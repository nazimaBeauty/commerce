package com.example.jaryia;

import android.content.Context;
import android.text.util.Linkify;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.jaryia.model.News;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Arrays;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {
    Context context;
    ArrayList<News> news;
    String[] words;

    public RecyclerAdapter(Context context, ArrayList<News> news) {
        this.context = context;
        this.news = news;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView info, date, socialMedia;
        //        ImageView photo;
        ImageSlider imageSlider;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
//            photo = itemView.findViewById(R.id.imageView3);
            imageSlider = itemView.findViewById(R.id.imageSlider);
            info = itemView.findViewById(R.id.textView5);
            date = itemView.findViewById(R.id.textView4);
            socialMedia = itemView.findViewById(R.id.textView6);
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.list_recycler, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        News newsArraylist = news.get(position);
        ArrayList<SlideModel> slideModels = new ArrayList<>();
        if (newsArraylist.getImage() != null) {
            words = newsArraylist.getImage().split(" ");
        }
        for (String word : words) {
            if (!word.equals(""))
                slideModels.add(new SlideModel(word, ScaleTypes.CENTER_INSIDE));
            else
                holder.imageSlider.setVisibility(View.GONE);
        }

        holder.imageSlider.setImageList(slideModels, ScaleTypes.CENTER_INSIDE);
        Arrays.fill(words, "");
//        Picasso.get().load(newsArraylist.getImage()).into(holder.photo);

        holder.info.setText(newsArraylist.getInfo());
        holder.date.setText(newsArraylist.getDate());
        holder.socialMedia.setText(newsArraylist.getSocialMedia());

        Linkify.addLinks(holder.socialMedia, Linkify.ALL);
    }

    @Override
    public int getItemCount() {
        return news.size();
    }

}
