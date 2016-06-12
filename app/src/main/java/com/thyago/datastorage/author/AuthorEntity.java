package com.thyago.datastorage.author;

import com.thyago.datastorage.entity.BaseEntity;

/**
 * Created by thyago on 6/3/16.
 */
public class AuthorEntity extends BaseEntity {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
