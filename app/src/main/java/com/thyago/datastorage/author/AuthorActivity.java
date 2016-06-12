package com.thyago.datastorage.author;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.thyago.datastorage.R;

public class AuthorActivity extends AppCompatActivity {

    public static final int INSERT_AUTHOR = 0xd1;
    public static final int UPDATE_AUTHOR = 0xd2;

    private static final String LOG_TAG = AuthorActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_author);
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
        Log.d(LOG_TAG, "Saving");
    }
}
