package control;

import com.example.flykey.Item;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

/**
 * This class contains the implementation of the interface MyControl.
 * @author Raphael Walger
 */
public class MyControlImplementation implements MyControl{

    /**
     * Creates an random alphanumeric String of given length.
     * @param l length of generated String
     * @return random alphanumeric String
     * @throws NoSuchAlgorithmException This exception is thrown when a particular cryptographic algorithm is requested but is not available in the environment.
     */
    private String generatePasswordOfGivenLength(int l) throws NoSuchAlgorithmException {

        // chose a Character random from this String
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz";

        // create StringBuffer size of AlphaNumericString
        StringBuilder sb = new StringBuilder(l);

        for (int i = 0; i < l; i++) {
            // generate a random number between 0 to AlphaNumericString variable length
            int index = (int)(AlphaNumericString.length() * Math.random());
            // add Character one by one in end of sb
            sb.append(AlphaNumericString.charAt(index));
        }

        return sb.toString();
    }

    @Override
    public boolean isStringAInteger(String s){
        boolean result = true;
        try{
            Integer.parseInt(s);
        } catch (NumberFormatException nFE){
            result = false;
        }
        return result;
    }

    @Override
    public boolean isIntegerTooLow(int x){
        if(x<MyControl.MINIMUM_LENGTH_OF_PASSWORD){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean isIntegerTooHigh(int x){
        if(x>MyControl.MAXIMUM_LENGTH_OF_PASSWORD){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public String generatePasswordDueToShakingOfPhone(float sensorData, int lengthOfPassword) throws NoSuchAlgorithmException, IllegalArgumentException {
        if(lengthOfPassword < 0) throw new IllegalArgumentException("lengthOfPassword is negativ");
        if(lengthOfPassword == 0) throw new IllegalArgumentException("lengthOfPassword is 0");

        String password = null;
        if(sensorData >= MyControl.SENSITIVITY_OF_GENERATOR || sensorData <= -MyControl.SENSITIVITY_OF_GENERATOR){
            password = generatePasswordOfGivenLength(lengthOfPassword);
        }
        return password;
    }

    @Override
    public ArrayList<Item> convertStringArrayIntoArrayListWithItem(String[] arrS) throws IllegalArgumentException{
        if(arrS==null) throw new IllegalArgumentException("arrS is null");

        ArrayList<Item> itemArrayList = new ArrayList<>();
        for(String s : arrS){
            itemArrayList.add(new Item(s));
        }
        return itemArrayList;
    }

}
