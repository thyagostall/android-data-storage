package com.thyago.datastorage.author;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.thyago.datastorage.OperationFinishedListener;
import com.thyago.datastorage.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AuthorActivity extends AppCompatActivity {

    public static final int INSERT_AUTHOR = 0xd1;
    public static final int UPDATE_AUTHOR = 0xd2;

    public static final String AUTHOR_NAME = "author_name";
    public static final String AUTHOR_ID = "author_id";

    private static final String LOG_TAG = AuthorActivity.class.getSimpleName();
    private AuthorEntity mEntity;

    @BindView(R.id.author_name)
    EditText mAuthorName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_author);

        ButterKnife.bind(this);

        long authorId = getIntent().getLongExtra(AUTHOR_ID, -1);
        String authorName = getIntent().getStringExtra(AUTHOR_NAME);

        mEntity = new AuthorEntity();
        mEntity.setName(authorName);
        mEntity.setId(authorId);

        mAuthorName.setText(mEntity.getName());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_author, menu);
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
        AuthorModel model = new AuthorModel(this);
        model.persist(mEntity, new OperationFinishedListener<Long>() {
            @Override
            public void onFinish(Long result) {
                mEntity.setId(result);

                Toast.makeText(AuthorActivity.this, R.string.data_saved, Toast.LENGTH_LONG).show();
                finish();
            }
        });
    }
}
