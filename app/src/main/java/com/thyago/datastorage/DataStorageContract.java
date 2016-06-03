package com.thyago.datastorage;

import android.provider.BaseColumns;

/**
 * Created by thyago on 6/3/16.
 */
public class DataStorageContract {
    private DataStorageContract() {}

    public static class SampleDataEntry implements BaseColumns {
        public static final String TABLE_NAME = "sample_data";
        public static final String TITLE = "title";
        public static final String TEXT = "text";
        public static final String AUTHOR_ID = "authorid";
    }

    public static class SampleDataAuthorEntry implements BaseColumns {
        public static final String TABLE_NAME = "author";
        public static final String NAME = "name";
    }
}
