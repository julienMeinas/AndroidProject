package com.android.androidproject.presentation.articledisplay.home.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.androidproject.R;
import com.android.androidproject.data.api.model.NicePlace;
import com.android.androidproject.presentation.articledisplay.home.fragment.HomeFragment;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

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
                .inflate(R.layout.layout_listitem, parent, false);
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
        CircleImageView m_circleImageView;
        TextView m_textView;
        RelativeLayout m_relativeLayout;
        private ArticleViewItem articleViewItem;

        public ViewHoler(@NonNull View itemView) {
            super(itemView);
            m_circleImageView = itemView.findViewById(R.id.image);
            m_textView = itemView.findViewById(R.id.image_name);
            m_relativeLayout = itemView.findViewById(R.id.parent_layout);
        }

        void bind(ArticleViewItem bookViewItem) {
            this.articleViewItem = bookViewItem;
            m_textView.setText(bookViewItem.getTitle());
        }

    }
}
