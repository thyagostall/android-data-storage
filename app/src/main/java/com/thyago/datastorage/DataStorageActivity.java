package com.thyago.datastorage;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.thyago.datastorage.entity.SampleDataEntity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DataStorageActivity extends AppCompatActivity {

    private static final String LOG_TAG = DataStorageActivity.class.getSimpleName();

    @BindView(R.id.list_view)
    ListView mListView;

    @BindView(R.id.fab)
    FloatingActionButton mFab;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @OnClick(R.id.fab)
    public void fabClick(final View view) {
        SampleDataEntity data = new SampleDataEntity();
        data.setTitle("Some title");
        data.setText("Some text");

        SampleDataModel model = new SampleDataModel(this);
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

        setSupportActionBar(mToolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_data_storage, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
