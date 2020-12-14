package com.android.androidproject.presentation.articledisplay.search.adapter;

public interface ArticleActionInterface {

    void onInfoClicked(String articleTitle, String articleAuthor,
                       String articleDate, String articleDescription,
                       String articleUrlImage, String articleUrl);

    void onFav(String articleTitle, String articleAuthor,
               String articleDate, String articleDescription,
               String urlImage, String url);

}
