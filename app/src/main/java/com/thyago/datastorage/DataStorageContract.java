package com.thyago.datastorage;

import android.provider.BaseColumns;

/**
 * Created by thyago on 6/3/16.
 */
public class DataStorageContract {
    public static final long ID_NOT_INSERTED = -1;
    public static final String WHERE_ID = "id=?";

    private DataStorageContract() {}

    public static class DataEntry implements BaseColumns {
        public static final String TABLE_NAME = "sample_data";
        public static final String TITLE = "title";
        public static final String TEXT = "text";
        public static final String AUTHOR_ID = "authorid";
    }

    public static class AuthorEntry implements BaseColumns {
        public static final String TABLE_NAME = "author";
        public static final String NAME = "name";
    }
}
