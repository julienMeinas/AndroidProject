package com.android.androidproject.data.api.model;

/**
 * object for representing source in api newsAPI
 */
public class ArticleSource {
    private String name;
    private String id;

    public ArticleSource(String m_id, String m_name) {
        this.id = m_id;
        this.name = m_name;
    }

    public String getM_id() {
        return id;
    }

    public String getM_name() {
        return name;
    }

    public void setM_id(String m_id) {
        this.id = m_id;
    }

    public void setM_name(String m_name) {
        this.name = m_name;
    }
}
