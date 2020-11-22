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
    private ArrayList<NicePlace> m_nicePlace = new ArrayList<>();
    private Context m_context;
    private View m_view;

    public RecyclerViewAdapter(ArrayList<NicePlace> nicePlace, Context context) {
        this.m_nicePlace = nicePlace;
        this.m_context = context;
    }

    @NonNull
    @Override
    public ViewHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        this.m_view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listitem, parent, false);
        ViewHoler viewHoler = new ViewHoler(m_view);
        return viewHoler;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoler holder, int position) {
        Glide.with(this.m_context)
                .asBitmap()
                .load(m_nicePlace.get(position).getImageUrl())
                .into(holder.m_circleImageView);
        holder.m_textView.setText(m_nicePlace.get(position).getTitle());

        holder.m_relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "OnClick: clicked on : " + m_nicePlace.get(position).getTitle());
                Toast.makeText(m_context, m_nicePlace.get(position).getTitle(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.m_nicePlace.size();
    }

    public class ViewHoler extends RecyclerView.ViewHolder {
        CircleImageView m_circleImageView;
        TextView m_textView;
        RelativeLayout m_relativeLayout;

        public ViewHoler(@NonNull View itemView) {
            super(itemView);
            m_circleImageView = itemView.findViewById(R.id.image);
            m_textView = itemView.findViewById(R.id.image_name);
            m_relativeLayout = itemView.findViewById(R.id.parent_layout);
        }


    }
}
