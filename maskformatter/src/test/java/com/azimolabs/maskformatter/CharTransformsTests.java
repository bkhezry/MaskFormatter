package com.azimolabs.maskformatter;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by maciek on 16.03.2016.
 */
public class CharTransformsTests {

    @Test
    public void testCharTransforms() throws InvalidTextException {
        char transformed = CharTransforms.transformChar('a', 'A');
        assertEquals(transformed, 'A');

        transformed = CharTransforms.transformChar('A', 'a');
        assertEquals(transformed, 'a');

        transformed = CharTransforms.transformChar('A', 'z');
        assertEquals(transformed, 'a');

        transformed = CharTransforms.transformChar('a', 'Z');
        assertEquals(transformed, 'A');

        transformed = CharTransforms.transformChar('a', '%');
        assertEquals(transformed, 'A');

        transformed = CharTransforms.transformChar('a', '@');
        assertEquals(transformed, 'a');
    }

    @Test
    public void testCharWordValidation() {
        try {
            CharTransforms.transformChar('à', 'A');
            Assert.fail();
        } catch (InvalidTextException ite) {

        }

        try {
            CharTransforms.transformChar('à', 'a');
            Assert.fail();
        } catch (InvalidTextException ite) {

        }

        try {
            CharTransforms.transformChar('!', 'w');
            Assert.fail();
        } catch (InvalidTextException ite) {

        }

        try {
            CharTransforms.transformChar('à', 'Z');
            CharTransforms.transformChar('à', 'z');
            CharTransforms.transformChar('_', 'w');
            CharTransforms.transformChar('2', 'w');
            CharTransforms.transformChar('c', 'w');
            CharTransforms.transformChar('!', 'W');
        } catch (InvalidTextException ite) {
            Assert.fail();
        }

    }

    @Test
    public void testWhitecharsValidation() {
        try {
            CharTransforms.transformChar('_', 's');
            Assert.fail();
        } catch (InvalidTextException ite) {

        }

        try {
            CharTransforms.transformChar('\t', 'S');
            Assert.fail();
        } catch (InvalidTextException ite) {

        }

        try {
            CharTransforms.transformChar('\t', 's');
            CharTransforms.transformChar('_', 'S');
        } catch (InvalidTextException ite) {
            Assert.fail();
        }
    }

    @Test
    public void testCharNumberValidation() {
        try {
            CharTransforms.transformChar('8', '7');
            Assert.fail();
        } catch (InvalidTextException ite) {

        }

        try {
            CharTransforms.transformChar('8', 'D');
            Assert.fail();
        } catch (InvalidTextException ite) {

        }

        try {
            CharTransforms.transformChar('8', '8');
            CharTransforms.transformChar('8', '9');
            CharTransforms.transformChar('8', 'd');
            CharTransforms.transformChar('ć', 'D');
        } catch (InvalidTextException ite) {
            Assert.fail();
        }

    }

    @Test
    public void testShouldReturnSameCharWhenMaskCharIsUnrecognized() throws InvalidTextException {
        char transformed = CharTransforms.transformChar('ź', '?');
        assertEquals(transformed, 'ź');
    }

    @Test
    public void testShouldAnyCharBePassedByStarMaskElement() throws InvalidTextException {
        CharTransforms.transformChar('!', '*');
        CharTransforms.transformChar('A', '*');
        CharTransforms.transformChar('ą', '*');
        CharTransforms.transformChar('_', '*');
        CharTransforms.transformChar('\t', '*');
        CharTransforms.transformChar(' ', '*');
        CharTransforms.transformChar('5', '*');
        CharTransforms.transformChar(';', '*');
    }

}