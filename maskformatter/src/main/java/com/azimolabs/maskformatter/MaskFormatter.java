package com.azimolabs.maskformatter;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

/**
 * Created by maciek on 16.03.2016.
 */
public class MaskFormatter implements TextWatcher {

    private static final char SPACE = ' ';

    private String mask;

    private EditText maskedField;

    private boolean editTextChange;
    private int newIndex;
    private String textBefore;
    private int selectionBefore;

    public MaskFormatter(String mask, EditText maskedField) {
        this.mask = mask;
        this.maskedField = maskedField;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int index, int toBeReplacedCount, int addedCount) {
        textBefore = s.toString();
        selectionBefore = maskedField.getSelectionEnd();
    }

    @Override
    public void onTextChanged(CharSequence s, int index, int replacedCount, int addedCount) {
        if (editTextChange) {
            maskedField.setSelection(newIndex);
            editTextChange = false;
            return;
        }

        try {
            String appliedMaskString = applyMask(s.toString());
            if (!appliedMaskString.equals(s.toString())) {
                editTextChange = true;
                newIndex = countNewIndex(addedCount, appliedMaskString);
                maskedField.setText(appliedMaskString);
            }
        } catch (InvalidTextException ie) {
            editTextChange = true;
            newIndex = selectionBefore;
            maskedField.setText(textBefore);
        }
    }

    private int countNewIndex(int addedCount, String appliedMaskString) {
        if (appliedMaskString.length() == 0) {
            return 0;
        }

        if (addedCount < 1) {
            return newIndexForRemovingCharacters(appliedMaskString);
        }

        return newIndexForAddingCharacters(appliedMaskString);
    }

    private int newIndexForRemovingCharacters(String appliedMaskString) {
        if (selectionBefore > appliedMaskString.length()) {
            selectionBefore = appliedMaskString.length();
        } else {
            selectionBefore = selectionBefore - 1;
        }

        if (selectionBefore < 0) {
            return 0;
        }

        if (selectionBefore - 1 >= 0
            && appliedMaskString.charAt(selectionBefore - 1) == SPACE) {
            return selectionBefore - 1;
        }

        return selectionBefore;
    }

    private int newIndexForAddingCharacters(String appliedMaskString) {
        if (selectionBefore >= appliedMaskString.length()) {
            return appliedMaskString.length();
        }

        if (appliedMaskString.charAt(selectionBefore) == SPACE) {
            return selectionBefore + 2;
        }

        return selectionBefore + 1;
    }

    @Override
    public void afterTextChanged(Editable s) {
        if (editTextChange) {
            maskedField.setSelection(newIndex);
        }
    }

    private String applyMask(String newValue) throws InvalidTextException {
        String newValueWithoutSpaces = newValue.replaceAll("\\s", "");
        StringBuilder sb = new StringBuilder();
        int index = 0;
        for (char c : newValueWithoutSpaces.toCharArray()) {
            if (index >= mask.length()) {
                throw new InvalidTextException();
            }
            while (mask.charAt(index) == SPACE) {
                sb.append(SPACE);
                index++;
            }
            sb.append(applyMaskToChar(c, index));
            index++;
        }

        return sb.toString();
    }

    private char applyMaskToChar(char c, int maskIndex) throws InvalidTextException {
        return CharTransforms.transformChar(c, mask.charAt(maskIndex));
    }

}