package com.azimolabs.maskformatter;

import android.text.InputType;

/**
 * Created by maciek on 21.03.2016.
 */
public class CharInputType {

    public static int getKeyboardTypeForNextChar(char nextChar) {
        if (Character.isDigit(nextChar)) {
            return InputType.TYPE_CLASS_NUMBER;
        }
        return InputType.TYPE_CLASS_NUMBER;
    }

}