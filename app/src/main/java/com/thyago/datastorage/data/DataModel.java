package com.thyago.datastorage.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.support.annotation.Nullable;

import com.thyago.datastorage.DataStorageContract;
import com.thyago.datastorage.DataStorageDbHelper;
import com.thyago.datastorage.OperationFinishedListener;
import com.thyago.datastorage.data.DataEntity;

import java.util.List;

/**
 * Created by thyago on 6/3/16.
 */
public class DataModel {

    private static final long NOT_SAVED = -1;

    private final Context mContext;

    public DataModel(Context mContext) {
        this.mContext = mContext;
    }

    public void persist(final DataEntity data, @Nullable final OperationFinishedListener<Boolean> finishedListener) {
        new AsyncTask<Void, Void, Long>() {
            @Override
            protected Long doInBackground(Void... params) {
                SQLiteDatabase db = null;
                long result = NOT_SAVED;
                try {
                    ContentValues content = new ContentValues();
                    content.put(DataStorageContract.DataEntry.TITLE, data.getTitle());
                    content.put(DataStorageContract.DataEntry.TEXT, data.getText());

                    DataStorageDbHelper dbHelper = new DataStorageDbHelper(mContext);
                    db = dbHelper.getWritableDatabase();

                    result = db.insert(DataStorageContract.DataEntry.TABLE_NAME, null, content);
                } finally {
                    if (db != null)
                        db.close();
                }

                return result;
            }

            @Override
            protected void onPostExecute(Long result) {
                if (finishedListener != null)
                    finishedListener.onFinish(result != NOT_SAVED);
            }
        }.execute();
    }

    public void findAll(final OperationFinishedListener<List<DataEntity>> finishedListener) {
        new AsyncTask<Void, Void, List<DataEntity>>() {
            @Override
            protected List<DataEntity> doInBackground(Void... params) {
                return null;
            }
        }.execute();
    }
}
