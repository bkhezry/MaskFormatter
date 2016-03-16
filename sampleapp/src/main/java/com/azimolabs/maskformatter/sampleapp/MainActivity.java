package com.azimolabs.maskformatter.sampleapp;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;

import com.azimolabs.maskformatter.MaskFormatter;

public class MainActivity extends Activity {

    private static final String IBAN_MASK = "AA 9999 AAAA wwww wwww wwww";

    private static final String NUMBERS_MASK = "dd DD 1234 5678 90";

    private static final String CHARS_MASK = "AAZZ aazz @@ww ##%%";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText ibanField = (EditText) findViewById(R.id.etIban);
        MaskFormatter ibanFormatter = new MaskFormatter(IBAN_MASK, ibanField);
        ibanField.addTextChangedListener(ibanFormatter);

        EditText numbersField = (EditText) findViewById(R.id.etNumbers);
        MaskFormatter numbersFormatter = new MaskFormatter(NUMBERS_MASK, numbersField);
        numbersField.addTextChangedListener(numbersFormatter);

        EditText charsField = (EditText) findViewById(R.id.etChars);
        MaskFormatter charsFormatter = new MaskFormatter(CHARS_MASK, charsField);
        charsField.addTextChangedListener(charsFormatter);
    }

}