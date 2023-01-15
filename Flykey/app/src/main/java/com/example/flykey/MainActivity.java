package com.example.flykey;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.example.flykey.fragments.*;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * This class contains the main activity.
 * It contains three listeners for three buttons in order to replace the fragments in the frame.
 * @author Raphael Walger
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentTransaction tr = getSupportFragmentManager().beginTransaction();
        ZeroFragment fragment0 = new ZeroFragment();
        tr.replace(R.id.frame1, fragment0);
        tr.commit();

        Button button1 = findViewById(R.id.button1);
        Button button2 = findViewById(R.id.button2);
        Button button3 = findViewById(R.id.button3);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                FirstFragment fragment1 = new FirstFragment();
                transaction.replace(R.id.frame1, fragment1);
                transaction.commit();
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                SecondFragment fragment2 = new SecondFragment();
                transaction.replace(R.id.frame1, fragment2);
                transaction.commit();
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                ThirdFragment fragment3 = new ThirdFragment();
                transaction.replace(R.id.frame1, fragment3);
                transaction.commit();
            }
        });

    }

}