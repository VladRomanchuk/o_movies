package com.vlad_romanchuk.o_movies.films;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.vlad_romanchuk.o_movies.R;
import com.vlad_romanchuk.o_movies.description.DescriptionActivity;
import com.vlad_romanchuk.o_movies.model.Movie;

import java.util.ArrayList;
import java.util.List;

public class MovieListFragment extends Fragment implements Contract.View {

    private static final String TAG = "MovieListFragment";
    private RecyclerView recyclerView;
    private View emptyView;
    private LinearLayoutManager manager;
    private MovieAdapter adapter;
    private boolean loading = false;
    private boolean moreAvailable = true;
    private int pastVisiblesItems, visibleItemCount, totalItemCount;
    private Contract.Presenter presenter;
    private ProgressDialog progressDialog;
    View inflate;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        inflate = inflater.inflate(R.layout.fragment_movies, container, false);
        recyclerView = (RecyclerView) inflate.findViewById(R.id.list_film);
        emptyView = (View) inflate.findViewById(R.id.view_empty);

        progressDialog = new ProgressDialog(getContext(), ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.show();

        manager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(manager);
        adapter = new MovieAdapter(new ArrayList<Movie>(), presenter);
        recyclerView.setAdapter(adapter);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                if (dy > 0) {
                    visibleItemCount = manager.getChildCount();

                    totalItemCount = manager.getItemCount();
                    pastVisiblesItems = manager.findFirstVisibleItemPosition();
                    if (!loading && moreAvailable) {
                        if ((visibleItemCount + pastVisiblesItems) >= totalItemCount) {
                            loading = true;
                            presenter.loadMore();
                            progressDialog.show();
                        }
                    }
                }
            }
        });
        return inflate;
    }

    @Override
    public void setTitle(String title) {
        ((TextView) getActivity().findViewById(R.id.toolbar_title)).setText(title);
    }

    @Override
    public void showMovies(List<Movie> movies) {
        if (movies.size() == 0) {
            setEmpty();
            return;
        }
        adapter.movieList = movies;
        adapter.notifyDataSetChanged();
        progressDialog.cancel();
        resetVisibility();
    }

    @Override
    public void showMovies(Movie movie) {
        DescriptionActivity.showIntent(getContext(), movie);

    }

    @Override
    public void showMoreMovies(List<Movie> moreMovies) {
        if (moreMovies.size() == 0) {
            moreAvailable = false;
        }
        adapter.movieList.addAll(moreMovies);
        adapter.notifyDataSetChanged();
        loading = false;
        progressDialog.cancel();
    }

    @Override
    public void setEmpty() {
        inflate = LayoutInflater.from(inflate.getContext()).inflate(R.layout.fragment_empty, null);
    }

    @Override
    public void setError(String error) {
        Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
        progressDialog.cancel();
        moreAvailable = false;
    }

    private void resetVisibility() {
        inflate = LayoutInflater.from(inflate.getContext()).inflate(R.layout.fragment_movies, null);
    }

    @Override
    public void setPresenter(Contract.Presenter presenter) {
        this.presenter = presenter;
    }
}
