package com.android.androidproject.data.api.model;

public class NicePlace {
    private String m_title;
    private String m_imageUrl;

    public NicePlace(String title, String imageUrl) {
        this.m_title = title;
        this.m_imageUrl = imageUrl;
    }

    public String getTitle() {
        return this.m_title;
    }

    public void setTitle(String title) {
        this.m_title = title;
    }

    public String getImageUrl() {
        return this.m_imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.m_imageUrl = imageUrl;
    }
}
