package com.example.flykey.fragments;

import static android.content.Context.SENSOR_SERVICE;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;

import java.security.NoSuchAlgorithmException;

import com.example.flykey.R;

/**
 * This class is the third fragment which will be shown to the User.
 * It contains a SensorEventListener for the x value of the accelerometer of the device running this app.
 * @author Raphael Walger
 */
public class SecondFragment extends Fragment {

    private TextView textViewF2 = null;
    private control.MyControl myControl = null;

    private SensorManager sensorManager = null;
    private int lengthOfPassword = 0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment2, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        sensorManager = (SensorManager) this.getActivity().getSystemService(SENSOR_SERVICE);

        Button buttonF2_1 = view.findViewById(R.id.buttonF2_1); //generate Button
        Button buttonF2_2 = view.findViewById(R.id.buttonF2_2); //copy button
        EditText input = view.findViewById(R.id.editTextF2_1); //input of text
        textViewF2 = view.findViewById(R.id.textViewF2_1); //output of text

        buttonF2_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                myControl = control.MyControlFactory.produceControl();
                String inputForLengthOfPassword = input.getText().toString();

                if(!myControl.isStringAInteger(inputForLengthOfPassword)) {
                    textViewF2.setText(getString(R.string.generatedPasswordTextWrongInputNotANumber));
                }else{
                    lengthOfPassword = Integer.parseInt(inputForLengthOfPassword);

                    if(myControl.isIntegerTooLow(lengthOfPassword)){
                        textViewF2.setText(getString(R.string.generatedPasswordTextWrongInputWrongNumberTooLow));
                    }else if(myControl.isIntegerTooHigh(lengthOfPassword)){
                        textViewF2.setText(getString(R.string.generatedPasswordTextWrongInputWrongNumberTooHigh));
                    }else{
                        textViewF2.setText(getString(R.string.generatedPasswordTextStartGeneratingByShakingPhone));
                        onResumeSensorListener();
                    }

                }


            }
        });

        buttonF2_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                copyTextIntoCache("generated password", textViewF2.getText());
                createAndShowSnackbar(getString(R.string.textPasswordSuccessfullyCopied));
            }
        });
    }

    /**
     * Used for receiving notifications from the SensorManager when there is new sensor data.
     */
    private SensorEventListener sensorEventListener = new SensorEventListener(){
        public void onAccuracyChanged(Sensor sensor, int accuracy) {}
        public void onSensorChanged(SensorEvent event) {
            float sensorDataAccelerometerX = event.values[0];
            generatePassword(sensorDataAccelerometerX, lengthOfPassword);
        }
    };

    /**
     * Registers an Listener for the Sensor Accelerometer.
     */
    private void onResumeSensorListener(){
        sensorManager.registerListener(sensorEventListener, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), sensorManager.SENSOR_DELAY_GAME);

    }

    /**
     * Unregisters an Listener for the Sensor Accelerometer.
     */
    private void onStopSensorListener(){
        sensorManager.unregisterListener(sensorEventListener, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER));
    }

    /**
     * Generates an random alphanumeric String of given length if sensorData reached specific value.
     * And shows it on the screen.
     * @param sensorData specific value which needs to be reached
     * @param length length of the alphanumeric String which needs to be generated
     */
    private void generatePassword(float sensorData, int length){
        String password= null;

        try {
            password = myControl.generatePasswordDueToShakingOfPhone(sensorData, length);
        } catch (NoSuchAlgorithmException nSA) {
            createAndShowSnackbar(nSA.getMessage());
        } catch (IllegalArgumentException iAE){
            createAndShowSnackbar(iAE.getMessage());
        }

        if(password!=null){
            textViewF2.setText(password);
            onStopSensorListener();
        }
    }

    /**
     * Copies the given CharSequence copyThisText into the cache of the smartphone.
     * @param label CharSequence, label of the copyThisText
     * @param copyThisText CharSequence, text which needs to be copied into the cache
     */
    private void copyTextIntoCache(CharSequence label, CharSequence copyThisText){
        ClipboardManager clipboard = (ClipboardManager) this.getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText(label, copyThisText);
        clipboard.setPrimaryClip(clip);
    }

    /**
     * Creates and shows a snackbar with given String.
     * @param message String which will be displayed in a snackbar
     */
    private void createAndShowSnackbar(String message){
        Snackbar snackbar = Snackbar.make(this.getActivity().findViewById(android.R.id.content).getRootView(), message, Snackbar.LENGTH_LONG);
        snackbar.show();
    }
}
