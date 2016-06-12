package com.thyago.datastorage.author;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.thyago.datastorage.DataStorageContract;
import com.thyago.datastorage.DataStorageDbHelper;
import com.thyago.datastorage.OperationFinishedListener;

import java.util.ArrayList;
import java.util.List;

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

                try {
                    ContentValues values = new ContentValues();
                    values.put(DataStorageContract.AuthorEntry.NAME, entity.getName());

                    if (entity.getId() == DataStorageContract.ID_NOT_INSERTED) {
                        return db.insert(DataStorageContract.AuthorEntry.TABLE_NAME, null, values);
                    } else {
                        values.put(DataStorageContract.AuthorEntry._ID, entity.getId());
                        db.update(DataStorageContract.AuthorEntry.TABLE_NAME, values, DataStorageContract.WHERE_ID, new String[]{String.valueOf(entity.getId())});
                        return entity.getId();
                    }
                } finally {
                    db.close();
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

    public void findAll(@NonNull final OperationFinishedListener<List<AuthorEntity>> finishedListener) {
        new AsyncTask<Void, Void, List<AuthorEntity>>() {
            @Override
            protected List<AuthorEntity> doInBackground(Void... params) {
                DataStorageDbHelper helper = new DataStorageDbHelper(mContext);
                SQLiteDatabase db = helper.getReadableDatabase();

                Cursor cursor = db.query(DataStorageContract.AuthorEntry.TABLE_NAME,
                        new String[]{DataStorageContract.AuthorEntry._ID, DataStorageContract.AuthorEntry.NAME}, null, null, null, null, null);

                try {
                    ArrayList<AuthorEntity> result = new ArrayList<>(cursor.getCount());
                    while (cursor.moveToNext()) {
                        AuthorEntity record = new AuthorEntity();
                        record.setId(cursor.getLong(cursor.getColumnIndex(DataStorageContract.AuthorEntry._ID)));
                        record.setName(cursor.getString(cursor.getColumnIndex(DataStorageContract.AuthorEntry.NAME)));

                        result.add(record);
                    }
                    return result;
                } finally {
                    cursor.close();
                }
            }

            @Override
            protected void onPostExecute(List<AuthorEntity> result) {
                finishedListener.onFinish(result);
            }
        }.execute();
    }
}
