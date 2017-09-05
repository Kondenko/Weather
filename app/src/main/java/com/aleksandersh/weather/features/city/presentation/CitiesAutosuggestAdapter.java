package com.aleksandersh.weather.features.city.presentation;


import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aleksandersh.weather.features.city.data.model.storable.City;
import com.aleksandersh.weather.utils.BaseRxAdapter;

import butterknife.BindView;


/**
 * A custom adapter to use with the RecyclerView widget.
 */
public class CitiesAutosuggestAdapter extends BaseRxAdapter<City, CitiesAutosuggestAdapter.ViewHolder> {

    public CitiesAutosuggestAdapter(Context context) {
        super(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = inflater.inflate(android.R.layout.two_line_list_item, viewGroup, false);
        return new ViewHolder(view);
    }

    static class ViewHolder extends BaseRxAdapter.BaseViewHolder<City> {

        @BindView(android.R.id.text1)
        TextView textViewCity;

        @BindView(android.R.id.text2)
        TextView textViewCountry;

        public ViewHolder(View view) {
            super(view);
        }

        public void bindItem(City city) {
            textViewCity.setText(city.getName());
            textViewCountry.setText(city.getCountryName());
        }

    }
}
