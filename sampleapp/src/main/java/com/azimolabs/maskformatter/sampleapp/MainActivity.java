package com.azimolabs.maskformatter.sampleapp;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;

import com.azimolabs.maskformatter.MaskFormatter;

public class MainActivity extends Activity {

    private static final String IBAN_MASK = "2 3 06 07 99 99 9999";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText ibanField = (EditText) findViewById(R.id.etIban);
        MaskFormatter ibanFormatter = new MaskFormatter(IBAN_MASK, ibanField);
        ibanField.addTextChangedListener(ibanFormatter);

    }

}