package com.example.nammakarnataka.news;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nammakarnataka.R;
import com.kwabenaberko.newsapilib.models.Article;
import com.squareup.picasso.Picasso;

import java.util.List;

public class NewsRecyclerAdapter extends RecyclerView.Adapter<NewsRecyclerAdapter.newsViewHolder>{
    List<Article> articleList;
    public NewsRecyclerAdapter(List<Article> articleList) {
        this.articleList=articleList;
    }

    @NonNull
    @Override
    public newsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_news,parent,false);
        return new newsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull newsViewHolder holder, int position) {
    Article article =articleList.get(position);
    holder.title_tv.setText(article.getTitle());
    holder.source_tv.setText(article.getSource().getName());
        Picasso.get().load(article
                        .getUrlToImage())
                .error(R.drawable.broken)
                .placeholder(R.drawable.broken)
                .into(holder.imageView);
        holder.itemView.setOnClickListener((v->{
            Intent intent = new Intent(v.getContext(), News_full_activity.class);
            intent.putExtra("url",article.getUrl());
            v.getContext().startActivity(intent);
        }));
    }

    void UpdateData(List<Article> data){
        articleList.clear();
        articleList.addAll(data);
    }

    @Override
    public int getItemCount() {
        return articleList.size();
    }

    class newsViewHolder extends RecyclerView.ViewHolder{
        TextView title_tv,source_tv;
        ImageView imageView;

        public newsViewHolder(@NonNull View itemView) {
            super(itemView);
            title_tv=itemView.findViewById(R.id.article_title);
            source_tv=itemView.findViewById(R.id.article_source);
            imageView=itemView.findViewById(R.id.artice_image);


        }
    }
}
