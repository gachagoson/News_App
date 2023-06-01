package com.example.bluenews.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bluenews.R;
import com.example.bluenews.models.News;
import com.squareup.picasso.Picasso;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {

    private List<News> newsList;

    public NewsAdapter(List<News> newsList) {
        this.newsList = newsList;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.show_news, parent, false);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        News news = newsList.get(position);
        holder.bind(news);
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    public class NewsViewHolder extends RecyclerView.ViewHolder {
        private TextView headlineTextView;
        private TextView contentTextView;
        private TextView dateTextView;
        private TextView sourceTextView;
        private ImageView imageView;

        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);
            headlineTextView = itemView.findViewById(R.id.text_headline);
            contentTextView = itemView.findViewById(R.id.text_content);
            dateTextView = itemView.findViewById(R.id.text_date);
            sourceTextView = itemView.findViewById(R.id.text_source);
            imageView = itemView.findViewById(R.id.image_news);
        }

        public void bind(News news) {
            headlineTextView.setText(news.getHeadline());
            contentTextView.setText(news.getContent());
            dateTextView.setText(news.getDate());
            sourceTextView.setText(news.getSource());

            // Load image using Picasso
            if (news.getImageUrl() != null) {
                Picasso.get().invalidate(news.getImageUrl());
                Picasso.get().load(news.getImageUrl()).into(imageView);
            } else {
                // If the image URL is null, you can set a placeholder image
                Picasso.get().load(R.drawable.placeholder_image).into(imageView);
            }
        }

    }
}
