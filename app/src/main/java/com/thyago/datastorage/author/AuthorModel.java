package com.thyago.datastorage.author;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.support.annotation.Nullable;

import com.thyago.datastorage.DataStorageContract;
import com.thyago.datastorage.DataStorageDbHelper;
import com.thyago.datastorage.OperationFinishedListener;

/**
 * Created by thyago on 6/11/16.
 */
public class AuthorModel {

    private final Context mContext;

    public AuthorModel(Context context) {
        mContext = context;
    }

    public void persist(final AuthorEntity entity, @Nullable final OperationFinishedListener<Long> finishedListener) {
        new AsyncTask<Void, Void, Long>() {

            @Override
            protected Long doInBackground(Void... params) {
                DataStorageDbHelper helper = new DataStorageDbHelper(mContext);
                SQLiteDatabase db = helper.getWritableDatabase();

                ContentValues values = new ContentValues();
                values.put(DataStorageContract.AuthorEntry.NAME, entity.getName());

                if (entity.getId() == DataStorageContract.ID_NOT_INSERTED) {
                    return db.insert(DataStorageContract.AuthorEntry.TABLE_NAME, null, values);
                } else {
                    values.put(DataStorageContract.AuthorEntry._ID, entity.getId());
                    db.update(DataStorageContract.AuthorEntry.TABLE_NAME, values, DataStorageContract.WHERE_ID, new String[]{String.valueOf(entity.getId())});
                    return entity.getId();
                }
            }

            @Override
            protected void onPostExecute(Long result) {
                if (finishedListener != null) {
                    finishedListener.onFinish(result);
                }
            }
        }.execute();
    }
}
