package com.thyago.datastorage;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by thyago on 6/3/16.
 */
public class DataStorageDbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_FILENAME = "datastorage.db";

    private static final String TEXT_TYPE = " TEXT";
    private static final String INTEGER_TYPE = " INTEGER";

    private static final String CREATE_TABLE = "CREATE TABLE %s (%s);";
    private static final String DELETE_TABLE = "DELETE TABLE IF EXISTS %s";

    private static final String PRIMARY_KEY = " INTEGER PRIMARY KEY";
    private static final String FOREIGN_KEY = " FOREIGN KEY(%s) REFERENCES %s(%s)";
    private static final String COMMA_SEP = ", ";

    private static final String CREATE_SAMPLE_DATA =
            String.format(CREATE_TABLE, DataStorageContract.SampleDataEntry.TABLE_NAME,
                    DataStorageContract.SampleDataEntry._ID + PRIMARY_KEY + COMMA_SEP +
                    DataStorageContract.SampleDataEntry.TITLE + TEXT_TYPE + COMMA_SEP +
                    DataStorageContract.SampleDataEntry.TEXT + TEXT_TYPE + COMMA_SEP +
                    DataStorageContract.SampleDataEntry.AUTHOR_ID + INTEGER_TYPE + COMMA_SEP +
                    String.format(FOREIGN_KEY,
                            DataStorageContract.SampleDataEntry.AUTHOR_ID,
                            DataStorageContract.SampleDataAuthorEntry.TABLE_NAME,
                            DataStorageContract.SampleDataAuthorEntry._ID
            ));

    private static final String CREATE_SAMPLE_DATA_AUTHOR =
            String.format(CREATE_TABLE, DataStorageContract.SampleDataAuthorEntry.TABLE_NAME,
                    DataStorageContract.SampleDataAuthorEntry._ID + PRIMARY_KEY + COMMA_SEP +
                    DataStorageContract.SampleDataAuthorEntry.NAME + TEXT_TYPE
            );

    private static final String DELETE_SAMPLE_DATA =
            String.format(DELETE_TABLE, DataStorageContract.SampleDataEntry.TABLE_NAME);

    private static final String DELETE_SAMPLE_DATA_AUTHOR =
            String.format(DELETE_TABLE, DataStorageContract.SampleDataAuthorEntry.TABLE_NAME);

    public DataStorageDbHelper(Context context) {
        super(context, DATABASE_FILENAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_SAMPLE_DATA_AUTHOR);
        db.execSQL(CREATE_SAMPLE_DATA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DELETE_SAMPLE_DATA);
        db.execSQL(DELETE_SAMPLE_DATA_AUTHOR);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DELETE_SAMPLE_DATA);
        db.execSQL(DELETE_SAMPLE_DATA_AUTHOR);
        onCreate(db);
    }
}
