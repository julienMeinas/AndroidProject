package com.android.androidproject.presentation.InfoActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.androidproject.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class InfoActivity extends AppCompatActivity {
    private static final String TAG = "InfoActivity";
    public static final String TITLE_MESSAGE = "_TITLE";
    public static final String AUTHOR_MESSAGE = "_AUTHOR";
    public static final String DATE_MESSAGE = "_DATE";
    public static final String DESCRIPTION_MESSAGE = "_DESCRIPTION";
    public static final String URL_IMAGE_MESSAGE = "_URL_IMAGE";
    public static final String URL_MESSAGE = "_URL";
    private TextView m_title;
    private ImageView m_image;
    private TextView m_author;
    private TextView m_date;
    private TextView m_description;
    private TextView m_url;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_activity);

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        String title = intent.getStringExtra(TITLE_MESSAGE);
        String author = intent.getStringExtra(AUTHOR_MESSAGE);
        String date = intent.getStringExtra(DATE_MESSAGE);
        String desciption = intent.getStringExtra(DESCRIPTION_MESSAGE);
        String urlImage = intent.getStringExtra(URL_IMAGE_MESSAGE);
        String url = intent.getStringExtra(URL_MESSAGE);
        initComposent();
        setLayout(title, author, date, desciption, urlImage, url);

        findViewById(R.id.button_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void setLayout(String title, String author, String date, String description, String urlImage, String url) {
        this.m_title.setText(title);
        this.m_author.setText(author);
        this.m_date.setText(date);
        Glide.with(this)
                .load(urlImage)
                .centerCrop()
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(m_image);
        this.m_description.setText(description);
        this.m_url.setText(url);
    }

    private void initComposent() {
        this.m_title = findViewById(R.id.titreArticle);
        this.m_image = findViewById(R.id.image);
        this.m_author = findViewById(R.id.author);
        this.m_date = findViewById(R.id.date);
        this.m_description = findViewById(R.id.description);
        this.m_url = findViewById(R.id.lienArticle);
    }
}
