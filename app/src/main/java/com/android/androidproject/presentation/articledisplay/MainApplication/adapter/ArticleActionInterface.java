package com.android.androidproject.presentation.articledisplay.MainApplication.adapter;

public interface ArticleActionInterface {

    void onInfoClicked(String articleTitle, String articleAuthor,
                       String articleDate, String articleDescription,
                       String articleUrlImage);

    void onFav(String articleTitle, String articleAuthor,
               String articleDate, String articleDescription);
}
