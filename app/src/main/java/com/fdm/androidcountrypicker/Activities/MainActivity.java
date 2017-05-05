package com.fdm.androidcountrypicker.Activities;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.fdm.androidcountrypicker.R;
import com.fdm.androidcountrypicker.Utilities.Utils;
import com.fdm.androidcountrypicker.Views.CountryPickerView;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mButtonChooseCountry;
    private TextView mTextChosenCountry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setupUI();
        setupListeners();
    }

    private void setupUI() {
        setContentView(R.layout.activity_main);

        mButtonChooseCountry = (Button) findViewById(R.id.choose_country_bt);
        mTextChosenCountry = (TextView) findViewById(R.id.chosen_country_tv);
    }

    private void setupListeners() {
        mButtonChooseCountry.setOnClickListener(this);
    }

    private void setupCountryDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        String dialogTitle = getString(R.string.activity_profile_dialog_country);
        final View dialogView = new CountryPickerView(this).getView();

        builder.setTitle(dialogTitle);
        builder.setView(dialogView);

        builder.setPositiveButton(R.string.interface_confirm,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        HashMap<String, String> resultValues = ((CountryPickerView) dialogView).getValues();
                        String resultValue = Utils.getResultValue(resultValues);

                        mTextChosenCountry.setText(resultValue);
                    }
                })
                .setNegativeButton(R.string.interface_cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });
        builder.create().show();
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == mButtonChooseCountry.getId()) {
            setupCountryDialog();
        }
    }
}
