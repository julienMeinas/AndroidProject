package com.android.androidproject.presentation.articledisplay.search.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.androidproject.R;
import com.android.androidproject.presentation.articledisplay.search.adapter.ArticleActionInterface;
import com.android.androidproject.presentation.articledisplay.search.adapter.ArticleViewItem;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

/**
 * Recycler view for Search fragment when we use LinearLayoutManager
 */
public class RecyclerViewListAdapter extends RecyclerView.Adapter<RecyclerViewListAdapter.ViewHoler > {
    private static final String TAG = "RecyclerViewAdapter";
    private ArrayList<ArticleViewItem> m_articles = new ArrayList<>();
    private ArticleActionInterface m_articleActionInterface;

    public RecyclerViewListAdapter(ArticleActionInterface articleActionInterface) {
        this.m_articleActionInterface = articleActionInterface;
    }

    public void bindViewModels(List<ArticleViewItem> bookViewItemList) {
        Log.d(TAG, "bindViewModels call");
        this.m_articles.clear();
        this.m_articles.addAll(bookViewItemList);
        notifyDataSetChanged();
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHoler onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_item_list, parent, false);
        ViewHoler articleViewHolder = new ViewHoler(v, m_articleActionInterface);
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
        private Button buttonFav;
        private ArticleViewItem articleViewItem;
        private View m_view;
        private ArticleActionInterface m_articleActionInterface;
        private final String m_msgAddFav = "Ajout aux favoris";
        private final String m_msgAlreadyAdd = "Déja ajouté";

        public ViewHoler(@NonNull View itemView, ArticleActionInterface articleActionInterface) {
            super(itemView);
            this.m_articleActionInterface = articleActionInterface;
            m_image = itemView.findViewById(R.id.image);
            m_title = itemView.findViewById(R.id.titreArticle);
            m_author = itemView.findViewById(R.id.author);
            m_date = itemView.findViewById(R.id.date);
            m_view = itemView;
            buttonFav = itemView.findViewById(R.id.button_fav);
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
                                                           articleViewItem.getUrlToImage(), articleViewItem.getUrl());
                }
            });

            this.m_view.findViewById(R.id.button_fav).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(articleViewItem.getIsFavorite()) {
                        Snackbar.make(m_view, m_msgAlreadyAdd, Snackbar.LENGTH_LONG).show();
                        return;
                    }
                    Snackbar.make(m_view, m_msgAddFav, Snackbar.LENGTH_LONG).show();
                    m_articleActionInterface.onFav(articleViewItem.getTitle(), articleViewItem.getAuthor(),
                                                   articleViewItem.getPublishedAt(), articleViewItem.getDescription(),
                                                   articleViewItem.getUrlToImage(), articleViewItem.getUrl());
                }
            });

        }



    }
}
