package com.fdm.androidcountrypicker.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fdm.androidcountrypicker.R;

import java.util.ArrayList;

/**
 * Created by foxdarkmaster on 04-05-2017.
 */

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.ViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(String countrySelected);
    }

    private Context context;
    private ArrayList<String> countries;
    private final int defaultSelectedCountry;
    private final OnItemClickListener mListener;
    private int selected_position = 0;
    private boolean firstTime = true;

    public CountryAdapter(
            Context context,
            ArrayList<String> countries,
            int defaultSelected,
            OnItemClickListener listener) {
        this.context = context;
        this.countries = countries;
        this.defaultSelectedCountry = defaultSelected;
        this.mListener = listener;
    }

    @Override
    public CountryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_country, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CountryAdapter.ViewHolder holder, final int position) {
        final String country = countries.get(position);

        if (firstTime && defaultSelectedCountry != -1) {
            if (country.equals(countries.get(defaultSelectedCountry))) {
                firstTime = false;
                selected_position = position;
                holder.itemView.setBackgroundColor(
                        ContextCompat.getColor(context, R.color.colorAccent));

            }
        } else {
            if (selected_position == position) {
                holder.itemView.setBackgroundColor(
                        ContextCompat.getColor(context, R.color.colorAccent));
            } else {
                holder.itemView.setBackgroundColor(Color.TRANSPARENT);
            }
        }

        holder.mCountry.setText(country);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notifyItemChanged(selected_position);
                selected_position = position;
                notifyItemChanged(selected_position);

                mListener.onItemClick(country);
            }
        });
    }

    @Override
    public int getItemCount() {
        return countries.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        protected TextView mCountry;

        public ViewHolder(View itemView) {
            super(itemView);

            mCountry = (TextView) itemView.findViewById(R.id.name_tv);
        }
    }
}
