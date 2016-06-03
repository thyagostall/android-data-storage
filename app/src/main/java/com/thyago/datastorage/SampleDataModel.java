package com.thyago.datastorage;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.support.annotation.Nullable;

import com.thyago.datastorage.entity.SampleDataEntity;

/**
 * Created by thyago on 6/3/16.
 */
public class SampleDataModel {

    private static final long NOT_SAVED = -1;

    private final Context mContext;

    public SampleDataModel(Context mContext) {
        this.mContext = mContext;
    }

    public void persist(final SampleDataEntity data, @Nullable final OperationFinishedListener<Boolean> finishedListener) {
        new AsyncTask<Void, Void, Long>() {
            @Override
            protected Long doInBackground(Void... params) {
                SQLiteDatabase db = null;
                long result = NOT_SAVED;
                try {
                    ContentValues content = new ContentValues();
                    content.put(DataStorageContract.SampleDataEntry.TITLE, data.getTitle());
                    content.put(DataStorageContract.SampleDataEntry.TEXT, data.getText());

                    DataStorageDbHelper dbHelper = new DataStorageDbHelper(mContext);
                    db = dbHelper.getWritableDatabase();

                    db.beginTransaction();
                    result = db.insert(DataStorageContract.SampleDataEntry.TABLE_NAME, null, content);
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
}
