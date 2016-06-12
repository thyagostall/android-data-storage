package com.thyago.datastorage.data;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;

import com.thyago.datastorage.OperationFinishedListener;
import com.thyago.datastorage.R;
import com.thyago.datastorage.author.AuthorAdapter;
import com.thyago.datastorage.author.AuthorEntity;
import com.thyago.datastorage.author.AuthorModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DataActivity extends AppCompatActivity {

    private static final String LOG_TAG = DataActivity.class.getSimpleName();

    @BindView(R.id.title)
    EditText mTitle;

    @BindView(R.id.text)
    EditText mText;

    @BindView(R.id.author)
    AutoCompleteTextView mAuthor;

    private AuthorModel mModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);

        ButterKnife.bind(this);

        mModel = new AuthorModel(this);
        mModel.findAll(new OperationFinishedListener<List<AuthorEntity>>() {
            @Override
            public void onFinish(List<AuthorEntity> result) {
                AuthorAdapter adapter = new AuthorAdapter(getLayoutInflater(), result);
                mAuthor.setAdapter(adapter);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_data, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_save:
                save();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void save() {
        Log.d(LOG_TAG, "saved");
    }
}
