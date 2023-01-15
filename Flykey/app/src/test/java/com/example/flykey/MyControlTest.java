package com.example.flykey;

import control.*;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

/**
 * This class contains a few tests which test the interface MyControl with JUnit4.
 * @author Raphael Walger
 */
public class MyControlTest {

    MyControl control;

    @Before
    public void createNewObjectOfMyControl(){
        control = MyControlFactory.produceControl();
    }

    @Test
    public void isStringAInteger_NormalInputTrue_Test(){
        String normalIntegerPositive = "100";
        String normalIntegerNegative = "-543";

        assertTrue(control.isStringAInteger(normalIntegerPositive));
        assertTrue(control.isStringAInteger(normalIntegerNegative));
    }

    @Test
    public void isStringAInteger_NormalInputFalse_Test(){
        String notAIntegerOne = "2.5";
        String notAIntegerTwo = "Test";
        String notAIntegerThree = "This is a test.";
        String notAIntegerFour = "0.0002";
        String notAIntegerFive = "1000000000000000";

        assertFalse(control.isStringAInteger(notAIntegerOne));
        assertFalse(control.isStringAInteger(notAIntegerTwo));
        assertFalse(control.isStringAInteger(notAIntegerThree));
        assertFalse(control.isStringAInteger(notAIntegerFour));
        assertFalse(control.isStringAInteger(notAIntegerFive));
    }

    @Test
    public void isStringAInteger_EmptyInput_Test(){
        String emptyString = "";

        assertFalse(control.isStringAInteger(emptyString));
    }

    @Test
    public void isStringAInteger_NullInput_Test(){
        assertFalse(control.isStringAInteger(null));
    }

    @Test
    public void isStringAInteger_IntMaxAndIntMinInput_Test(){
        String intMax = String.valueOf(Integer.MAX_VALUE);
        String intMin = String.valueOf(Integer.MIN_VALUE);

        assertTrue(control.isStringAInteger(intMax));
        assertTrue(control.isStringAInteger(intMin));
    }

    @Test
    public void isIntegerTooLow_InputIsIndeedToLow_Test(){
        int minimum = MyControl.MINIMUM_LENGTH_OF_PASSWORD;

        assertTrue(control.isIntegerTooLow(minimum-1));
    }

    @Test
    public void isIntegerTooLow_InputIsNotToLowSameAsSetLimit_Test(){
        int minimum = MyControl.MINIMUM_LENGTH_OF_PASSWORD;

        assertFalse(control.isIntegerTooLow(minimum));
    }

    @Test
    public void isIntegerTooLow_InputIsNotToLow_Test(){
        int minimum = MyControl.MINIMUM_LENGTH_OF_PASSWORD;

        assertFalse(control.isIntegerTooLow(minimum+1));
    }

    @Test
    public void isIntegerTooHigh_InputIsIndeedToHigh_Test(){
        int maximum = MyControl.MAXIMUM_LENGTH_OF_PASSWORD;

        assertTrue(control.isIntegerTooHigh(maximum+1));
    }

    @Test
    public void isIntegerTooHigh_InputIsNotToHighSameAsSetLimit_Test(){
        int maximum = MyControl.MAXIMUM_LENGTH_OF_PASSWORD;

        assertFalse(control.isIntegerTooHigh(maximum));
    }

    @Test
    public void isIntegerTooHigh_InputIsNotToHigh_Test(){
        int maximum = MyControl.MAXIMUM_LENGTH_OF_PASSWORD;

        assertFalse(control.isIntegerTooHigh(maximum-1));
    }

    @Test
    public void generatePasswordDueToShakingOfPhone_SuccessfulInputWithFloatTheSameAsSetLimit_Test() throws NoSuchAlgorithmException {
        byte sensitivity = MyControl.SENSITIVITY_OF_GENERATOR;

        String resultOne = control.generatePasswordDueToShakingOfPhone(sensitivity, 10);
        assertTrue(resultOne.length()==10);
        String resultTwo = control.generatePasswordDueToShakingOfPhone(-sensitivity, 2);
        assertTrue(resultTwo.length()==2);
    }

    @Test
    public void generatePasswordDueToShakingOfPhone_SuccessfulInputWithFloatOverTheSetLimit_Test() throws NoSuchAlgorithmException {
        byte sensitivity = MyControl.SENSITIVITY_OF_GENERATOR;

        String resultOne = control.generatePasswordDueToShakingOfPhone(sensitivity+1, 10);
        assertTrue(resultOne.length()==10);
        String resultTwo = control.generatePasswordDueToShakingOfPhone(-sensitivity-1, 2);
        assertTrue(resultTwo.length()==2);
    }

    @Test
    public void generatePasswordDueToShakingOfPhone_NotSuccessfulInput_Test() throws NoSuchAlgorithmException {
        byte sensitivity = MyControl.SENSITIVITY_OF_GENERATOR;

        String noResultOne = control.generatePasswordDueToShakingOfPhone(sensitivity -1, 5);
        assertTrue(noResultOne==null);
        String noResultTwo = control.generatePasswordDueToShakingOfPhone(-sensitivity +1, 8);
        assertTrue(noResultTwo==null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void generatePasswordDueToShakingOfPhone_intIsZero_TEST() throws NoSuchAlgorithmException {
        byte sensitivity = MyControl.SENSITIVITY_OF_GENERATOR;

        control.generatePasswordDueToShakingOfPhone(sensitivity, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void generatePasswordDueToShakingOfPhone_intIsSmallerThanZero_TEST() throws NoSuchAlgorithmException {
        byte sensitivity = MyControl.SENSITIVITY_OF_GENERATOR;

        control.generatePasswordDueToShakingOfPhone(sensitivity, -1);
    }

    @Test
    public void convertStringArrayIntoArrayListWithItem_CheckIfOutputHasSameLengthAsInput_Test(){
        String[] arrayOfStrings = {"This", "is", "a", "test.", "Length should be 5."};
        String[] emptyArrayOfStrings = {};

        ArrayList<Item> resultListNormal = control.convertStringArrayIntoArrayListWithItem(arrayOfStrings);
        ArrayList<Item> resultListEmpty = control.convertStringArrayIntoArrayListWithItem(emptyArrayOfStrings);

        assertTrue(arrayOfStrings.length == resultListNormal.size());
        assertTrue(emptyArrayOfStrings.length == resultListEmpty.size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void convertStringArrayIntoArrayListWithItem_Exception_Test(){
        ArrayList<Item> result = control.convertStringArrayIntoArrayListWithItem(null);
    }
}
