package com.fdm.androidcountrypicker.Interfaces;

import android.view.View;

import java.util.HashMap;

/**
 * Created by foxdarkmaster on 05-05-2017.
 */

public interface IBaseHandler {
    void setupUI();
    void setupData();
    View getView();
    HashMap<String, String> getValues();
}
