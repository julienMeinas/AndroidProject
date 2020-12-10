package com.android.androidproject.presentation.articledisplay.favorite.adapter;

public interface ArticleActionInterface {
    public void removeFavorite(String title);
    public void onInfoClicked(String articleTitle, String articleAuthor,
                       String articleDate, String articleDescription,
                       String articleUrlImage);
}
