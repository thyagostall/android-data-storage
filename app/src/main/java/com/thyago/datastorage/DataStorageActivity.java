package com.thyago.datastorage;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.thyago.datastorage.author.AuthorActivity;
import com.thyago.datastorage.data.DataActivity;
import com.thyago.datastorage.data.DataEntity;
import com.thyago.datastorage.data.DataModel;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DataStorageActivity extends AppCompatActivity {

    private static final String LOG_TAG = DataStorageActivity.class.getSimpleName();

    @BindView(R.id.list_view)
    ListView mListView;

    public void fabClick(final View view) {
        DataEntity data = new DataEntity();
        data.setTitle("Some title");
        data.setText("Some text");

        DataModel model = new DataModel(this);
        model.persist(data, new OperationFinishedListener<Boolean>() {
            @Override
            public void onFinish(Boolean result) {
                int idMessage;
                if (result) {
                    idMessage = R.string.record_created;
                } else {
                    idMessage = R.string.error_on_create_record;
                }

                Snackbar.make(view, idMessage, Snackbar.LENGTH_LONG).show();
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_storage);
        ButterKnife.bind(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_data_storage, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_insert_data:
                insertData();
                return true;
            case R.id.action_authors:
                authors();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void insertData() {
        Intent intent = new Intent(this, DataActivity.class);
        startActivity(intent);
    }

    public void authors() {
        Intent intent = new Intent(this, AuthorActivity.class);
        startActivityForResult(intent, AuthorActivity.INSERT_AUTHOR);
    }
}
