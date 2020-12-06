package com.android.androidproject.presentation.articledisplay.favorite.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.androidproject.R;
import com.android.androidproject.presentation.articledisplay.MainApplication.adapter.ArticleViewItem;
import com.android.androidproject.presentation.articledisplay.MainApplication.adapter.list.RecyclerViewListAdapter;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;

import java.util.ArrayList;
import java.util.List;

public class ArticleDetailAdapter extends RecyclerView.Adapter<ArticleDetailAdapter.ViewHoler > {
    private static final String TAG = "ArticleDetailAdapter";
    private List<ArticleViewItem> m_articles;
    private ArticleDetailActionInterface articleDetailActionInterface;

    public ArticleDetailAdapter(ArticleDetailActionInterface articleDetailActionInterface) {
        this.articleDetailActionInterface = articleDetailActionInterface;
        this.m_articles = new ArrayList<>();
    }

    public void bindViewModels(List<ArticleViewItem> bookViewItemList) {
        this.m_articles.clear();
        this.m_articles.addAll(bookViewItemList);
        notifyDataSetChanged();
    }

    public ViewHoler onCreateViewHolder(ViewGroup parent,
                                                                int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_favorite_item_list, parent, false);
        ViewHoler articleViewHolder = new ViewHoler(v, articleDetailActionInterface);
        return articleViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoler holder, int position) {
        holder.bind(m_articles.get(position));
    }


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
        private ArticleDetailActionInterface m_articleActionInterface;

        public ViewHoler(@NonNull View itemView, ArticleDetailActionInterface articleActionInterface) {
            super(itemView);
            this.m_articleActionInterface = articleActionInterface;
            m_image = itemView.findViewById(R.id.image);
            m_title = itemView.findViewById(R.id.titreArticle);
            m_author = itemView.findViewById(R.id.author);
            m_date = itemView.findViewById(R.id.date);
            m_view = itemView;
        }

        public void bind(ArticleViewItem articleViewItem) {
            this.articleViewItem = articleViewItem;
            m_title.setText(articleViewItem.getTitle());
            m_date.setText(articleViewItem.getPublishedAt());
            m_author.setText(articleViewItem.getAuthor());
            Glide.with(m_view)
                    .load(articleViewItem.getUrlToImage())
                    .centerCrop()
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(m_image);

            this.m_view.findViewById(R.id.button_info).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    m_articleActionInterface.onInfoClicked(articleViewItem.getTitle(), articleViewItem.getAuthor(),
                            articleViewItem.getPublishedAt(), articleViewItem.getDescription(),
                            articleViewItem.getUrlToImage());
                }
            });

            this.m_view.findViewById(R.id.button_remove).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    m_articleActionInterface.removeFavorite(articleViewItem.getTitle());
                }
            });
        }



    }

}
