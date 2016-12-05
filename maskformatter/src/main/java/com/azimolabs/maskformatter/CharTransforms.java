package com.azimolabs.maskformatter;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * Created by maciek on 16.03.2016.
 */
public class CharTransforms {

    private static Map<Character, TransformPattern> transformMap = new HashMap<>();

    private static class TransformPattern {

        private Pattern pattern;
        private boolean upperCase;
        private boolean lowerCase;

        public TransformPattern(String pattern, boolean upperCase, boolean lowerCase) {
            this.pattern = Pattern.compile(pattern);
            this.upperCase = upperCase;
            this.lowerCase = lowerCase;
        }

        public char transformChar(char stringChar) throws InvalidTextException {
            char modified;
            if (upperCase) {
                modified = Character.toUpperCase(stringChar);
            } else if (lowerCase) {
                modified = Character.toLowerCase(stringChar);
            } else {
                modified = stringChar;
            }

            if (!pattern.matcher(modified + "").matches()) {
                throw new InvalidTextException();
            }
            return modified;
        }

    }

    static {
        transformMap.put('9', new TransformPattern("[0-9]", false, false));
        transformMap.put('8', new TransformPattern("[0-8]", false, false));
        transformMap.put('7', new TransformPattern("[0-7]", false, false));
        transformMap.put('6', new TransformPattern("[0-6]", false, false));
        transformMap.put('5', new TransformPattern("[0-5]", false, false));
        transformMap.put('4', new TransformPattern("[0-4]", false, false));
        transformMap.put('3', new TransformPattern("[0-3]", false, false));
        transformMap.put('2', new TransformPattern("[0-2]", false, false));
        transformMap.put('1', new TransformPattern("[0-1]", false, false));
        transformMap.put('0', new TransformPattern("[0]", false, false));

        transformMap.put('(', new TransformPattern("[9]", false, false));
        transformMap.put('*', new TransformPattern("[8]", false, false));
        transformMap.put('&', new TransformPattern("[7]", false, false));
        transformMap.put('^', new TransformPattern("[6]", false, false));
        transformMap.put('%', new TransformPattern("[5]", false, false));
        transformMap.put('$', new TransformPattern("[4]", false, false));
        transformMap.put('#', new TransformPattern("[3]", false, false));
        transformMap.put('@', new TransformPattern("[2]", false, false));
        transformMap.put('!', new TransformPattern("[1]", false, false));
        transformMap.put(')', new TransformPattern("[0]", false, false));

        transformMap.put(':', new TransformPattern("[1-2]", false, false));


    }

    public static char transformChar(char stringChar, char maskChar) throws InvalidTextException {
        TransformPattern transform = transformMap.get(maskChar);
        if (transform == null) {
            return stringChar;
        }

        return transform.transformChar(stringChar);
    }

}
