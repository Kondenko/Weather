package com.aleksandersh.weather.features.city.presentation;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.aleksandersh.weather.App;
import com.aleksandersh.weather.R;
import com.aleksandersh.weather.features.city.data.model.storable.City;
import com.jakewharton.rxbinding2.widget.RxTextView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;


/**
 * Фрагмент с поиском города, погоду для которого нужно отображать.
 */
public class CityChooserFragment extends Fragment implements CityView {

    public static final String TAG = "cityChooserFragment";

    private OnCitySelectedListener onCitySelectedListener;

    private Unbinder mUnbinder;

    @Inject
    public CityPresenter presenter;

    @BindView(R.id.citychooser_fab)
    FloatingActionButton fab;

    @BindView(R.id.citychooser_header_textview_title)
    TextView textViewTitle;

    @BindView(R.id.citychooser_textview_current_city)
    TextView textViewCurrentCity;

    @BindView(R.id.citychooser_textview_search_city)
    EditText editTextSearchCity;

    @BindView(R.id.citychooser_recyclerview_cities)
    RecyclerView recyclerViewCities;

    private CompositeDisposable compositeDisposable;

    private ItemTouchHelper itemTouchHelper;
    private SavedCitiesAdapter savedCitiesAdapter = null;
    private CitiesAutosuggestAdapter citiesAutosuggestAdapter = null;

    public CityChooserFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_citychooser, container, false);
        mUnbinder = ButterKnife.bind(this, rootView);
        App.getAppComponent().inject(this);
        compositeDisposable = new CompositeDisposable();

        recyclerViewCities.setLayoutManager(new LinearLayoutManager(this.getContext()));
        itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder viewHolder1) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int i) {
                int position = viewHolder.getAdapterPosition();
                presenter.onSavedCityDeleted(savedCitiesAdapter.getItem(position));
                savedCitiesAdapter.remove(position);
            }
        });

        citiesAutosuggestAdapter = new CitiesAutosuggestAdapter(this.getContext());
        addDisp(citiesAutosuggestAdapter.clicks()
                .subscribe(presenter::onSuggestionClick));

        savedCitiesAdapter = new SavedCitiesAdapter(this.getContext());
        addDisp(savedCitiesAdapter.clicks()
                .subscribe(presenter::onSavedCityClick));

        addDisp(RxTextView.textChanges(editTextSearchCity)
                .filter(charSequence -> charSequence.length() > 0)
                .debounce(200, TimeUnit.MILLISECONDS)
                .map(CharSequence::toString)
                .map(String::trim)
                .subscribe(presenter::onSearchQueryUpdated));

        presenter.attach(this);
        presenter.onInit();
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.detach();
        mUnbinder.unbind();
        compositeDisposable.dispose();
    }

    @Override
    public void showSuggestions(List<City> cities) {
        citiesAutosuggestAdapter.setData(new ArrayList<>(cities));
    }

    @Override
    public void addSavedCityToList(City city) {
        savedCitiesAdapter.addItem(city);
    }

    @Override
    public void showCurrentCity(City city) {
        if (onCitySelectedListener != null) onCitySelectedListener.onSelected();
        textViewCurrentCity.setText(city.getName());
    }

    @Override
    public void showSearchState(boolean showSearch) {
        fab.setOnClickListener(v -> showSearchState(!showSearch));
        fab.setImageResource(showSearch ? R.drawable.ic_all_close_black_24dp : R.drawable.ic_all_add_black_24dp);
        textViewTitle.setText(showSearch ? R.string.citychooser_header_add_city : R.string.citychooser_header_current_city);
        textViewCurrentCity.setVisibility(showSearch ? View.GONE : View.VISIBLE);
        editTextSearchCity.setVisibility(showSearch ? View.VISIBLE : View.GONE);
        editTextSearchCity.setText("");
        recyclerViewCities.setAdapter(showSearch ? citiesAutosuggestAdapter : savedCitiesAdapter);
        itemTouchHelper.attachToRecyclerView(showSearch ? null : recyclerViewCities);
        if (showSearch) citiesAutosuggestAdapter.clear();
    }

    public void setOnCitySelectedListener(OnCitySelectedListener listener) {
        onCitySelectedListener = listener;
    }

    private void addDisp(Disposable disposable) {
        compositeDisposable.add(disposable);
    }

    @Override
    public void showLoading(boolean show) {

    }

    @Override
    public void showError(Throwable throwable) {
        Log.e(TAG, throwable.getMessage(), throwable);
    }

    public interface OnCitySelectedListener {

        void onSelected();
    }

}
