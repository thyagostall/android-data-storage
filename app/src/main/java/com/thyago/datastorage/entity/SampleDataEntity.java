package com.thyago.datastorage.entity;

/**
 * Created by thyago on 6/3/16.
 */
public class SampleDataEntity extends BaseEntity {
    private String title;
    private String text;

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
}
