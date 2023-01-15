package control;

import com.example.flykey.Item;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

/**
 * This interface contains the control part (MVC pattern) of this app.
 * @author Raphael Walger
 */
public interface MyControl {
    final static int MINIMUM_LENGTH_OF_PASSWORD =6;
    final static int MAXIMUM_LENGTH_OF_PASSWORD =16;
    final static byte SENSITIVITY_OF_GENERATOR =10;

    /**
     * Checks if the given String is an Integer. If so, true is returned, otherwise false.
     * @param s String which needs to be checked
     * @return true if given String is an Integer, false otherwise
     */
    abstract boolean isStringAInteger(String s);

    /**
     * Checks if the given int is smaller than set Minimum.
     * @param x int which needs to be checked
     * @return true if x < MyControl.MINIMUM_LENGTH_OF_PASSWORD, otherwise false
     */
    abstract boolean isIntegerTooLow(int x);

    /**
     * Checks if the given int is bigger than set Maximum.
     * @param x int which needs to be checked
     * @return true if x > MyControl.MAXIMUM_LENGTH_OF_PASSWORD, otherwise false
     */
    abstract boolean isIntegerTooHigh(int x);

    /**
     * Returns a random alphanumeric String of given length if sensorData reached a specific value, otherwise null is returned.
     * @param sensorData value which determines if String will be generated
     * @param lengthOfPassword length of String which needs to be generated
     * @return random alphanumeric String if sensorData reached the value of MyControl.SENSITIVITY_OF_GENERATOR, otherwise null
     * @throws NoSuchAlgorithmException This exception is thrown when a particular cryptographic algorithm is requested but is not available in the environment.
     * @throws IllegalArgumentException This exception is thrown when lengthOfPassword is smaller or the same as the value 0.
     */
    abstract String generatePasswordDueToShakingOfPhone(float sensorData, int lengthOfPassword) throws NoSuchAlgorithmException, IllegalArgumentException;

    /**
     * Converts the Array with Strings into an ArrayList with Objects of type Item.
     * @param arrS String array which needs to be converted
     * @return ArrayList with class Item which was build with arrS
     * @throws IllegalArgumentException if arrS is null
     */
    abstract ArrayList<Item> convertStringArrayIntoArrayListWithItem(String[] arrS) throws IllegalArgumentException;

}
