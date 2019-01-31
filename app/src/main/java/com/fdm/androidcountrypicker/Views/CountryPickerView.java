package com.fdm.androidcountrypicker.Views;

import android.content.Context;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.fdm.androidcountrypicker.Adapters.CountryAdapter;
import com.fdm.androidcountrypicker.Interfaces.IBaseHandler;
import com.fdm.androidcountrypicker.R;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Locale;


public class CountryPickerView extends LinearLayoutCompat implements
        IBaseHandler,
        CountryAdapter.OnItemClickListener {

    private View mView;
    private String resultCountry;
    private RecyclerView mCountryRecycler;
    private LinearLayoutManager mLayoutManager;
    private CountryAdapter mAdapter;

    private ArrayList<String> countryList;

    public static final String DIALOG_COUNTRY = "country";
    public static final String DEFAULT_COUNTRY = "Portugal";


    public CountryPickerView(Context context) {
        super(context);

        setupUI();
        setupData();
        setupListeners();
    }

    @Override
    public void setupUI() {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        mView = inflater.inflate(R.layout.layout_country_change_dialog, this, true);
        mCountryRecycler = mView.findViewById(R.id.country_change_rv);
    }

    @Override
    public void setupData() {
        resultCountry = DEFAULT_COUNTRY;
        countryList = getCountryList();

        int startPosition = findCountryPositionByName(resultCountry);

        mLayoutManager = new LinearLayoutManager(getContext());
        mCountryRecycler.setLayoutManager(mLayoutManager);
        mAdapter = new CountryAdapter(
                getContext(),
                getCountryList(),
                startPosition,
                this);

        if (startPosition != -1) {
            mCountryRecycler.scrollToPosition(startPosition);
        }

        mCountryRecycler.setAdapter(mAdapter);
    }

    public void setupListeners() {

    }

    private ArrayList<String> getCountryList() {
        Locale[] locales = Locale.getAvailableLocales();

        ArrayList<String> countries = new ArrayList<>();

        for (Locale locale : locales) {
            String country = locale.getDisplayCountry();

            if (country.trim().length() > 0 && !countries.contains(country)) {
                countries.add(country);
            }
        }

        Collections.sort(countries, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                o1 = Normalizer.normalize(o1, Normalizer.Form.NFD);
                o2 = Normalizer.normalize(o2, Normalizer.Form.NFD);
                return o1.compareTo(o2);
            }
        });

        return countries;
    }

    private int findCountryPositionByName(String country) {
        for (int i = 0; i < countryList.size(); i++) {
            String newCountry = Normalizer.normalize(countryList.get(i), Normalizer.Form.NFD);

            if (newCountry.equals(Normalizer.normalize(country, Normalizer.Form.NFD))) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public View getView() {
        return this;
    }

    @Override
    public void onItemClick(String countrySelected) {
        resultCountry = countrySelected;
    }

    @Override
    public HashMap<String, String> getValues() {
        HashMap<String, String> result = new HashMap<String, String>() {{
            put(DIALOG_COUNTRY, resultCountry);
        }};

        return result;
    }
}
