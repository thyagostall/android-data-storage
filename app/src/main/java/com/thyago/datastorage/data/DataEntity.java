package com.thyago.datastorage.data;

import com.thyago.datastorage.entity.BaseEntity;

/**
 * Created by thyago on 6/3/16.
 */
public class DataEntity extends BaseEntity {
    private String title;
    private String text;
    private int authorId;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }
}
