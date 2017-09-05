package com.aleksandersh.weather.features.city.presentation;


import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aleksandersh.weather.R;
import com.aleksandersh.weather.features.city.data.model.storable.City;
import com.aleksandersh.weather.utils.BaseRxAdapter;

import butterknife.BindView;


/**
 * Created by Vladimir Kondenko on 09.08.17.
 */

public class SavedCitiesAdapter extends BaseRxAdapter<City, SavedCitiesAdapter.ViewHolder> {

    public SavedCitiesAdapter(Context context) {
        super(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = inflater.inflate(R.layout.item_citychooser_selected_city, viewGroup, false);
        return new SavedCitiesAdapter.ViewHolder(view);
    }

    static class ViewHolder extends BaseRxAdapter.BaseViewHolder<City> {

        @BindView(R.id.chitychooser_savedcities_textview_city)
        TextView name;

        public ViewHolder(View view) {
            super(view);
        }

        @Override
        public void bindItem(City city) {
            super.bindItem(city);
            name.setText(city.getName());
        }
    }

}
