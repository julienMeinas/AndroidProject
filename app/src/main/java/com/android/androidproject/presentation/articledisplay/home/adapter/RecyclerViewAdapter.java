package com.android.androidproject.presentation.articledisplay.home.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.androidproject.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHoler > {
    private static final String TAG = "RecyclerViewAdapter";
    private ArrayList<ArticleViewItem> m_articles = new ArrayList<>();

    public void bindViewModels(List<ArticleViewItem> bookViewItemList) {
        this.m_articles.clear();
        this.m_articles.addAll(bookViewItemList);
        notifyDataSetChanged();
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHoler onCreateViewHolder(ViewGroup parent,
                                             int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_item_list, parent, false);
        ViewHoler articleViewHolder = new ViewHoler(v);
        return articleViewHolder;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHoler holder, int position) {
        holder.bind(m_articles.get(position));
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return m_articles.size();
    }

    public class ViewHoler extends RecyclerView.ViewHolder {
        private TextView m_title;
        private ImageView m_image;
        private TextView m_author;
        private TextView m_date;
        private ArticleViewItem articleViewItem;
        private View m_view;

        public ViewHoler(@NonNull View itemView) {
            super(itemView);
            m_image = itemView.findViewById(R.id.image);
            m_title = itemView.findViewById(R.id.titreArticle);
            m_author = itemView.findViewById(R.id.author);
            m_date = itemView.findViewById(R.id.date);
            m_view = itemView;
        }

        void bind(ArticleViewItem articleViewItem) {
            this.articleViewItem = articleViewItem;
            m_title.setText(articleViewItem.getTitle());
            m_date.setText(articleViewItem.getPublishedAt());
            m_author.setText(articleViewItem.getAuthor());
            Glide.with(m_view)
                    .load(articleViewItem.getUrlToImage())
                    .centerCrop()
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(m_image);

        }

    }
}
