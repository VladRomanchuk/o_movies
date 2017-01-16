package com.vlad_romanchuk.o_movies.films;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.miguelcatalan.materialsearchview.MaterialSearchView;
import com.vlad_romanchuk.o_movies.R;

public class MainActivity extends AppCompatActivity {

    Presenter presenter;
    MaterialSearchView setMenuItem;
    private TextView toolbarText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        setMenuItem = (MaterialSearchView)findViewById(R.id.search_view);
        toolbarText = (TextView) findViewById(R.id.toolbar_title);


        setMenuItem.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                presenter.searchByNameMovie(newText);

                return false;
            }
        });

        MovieListFragment fragment = new MovieListFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.container_movie, fragment, "tag").commit();
        presenter = new Presenter(fragment);
        presenter.run();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_dashboard, menu);
        MenuItem item = menu.findItem(R.id.action_settings);

        setMenuItem.setMenuItem(item);

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
