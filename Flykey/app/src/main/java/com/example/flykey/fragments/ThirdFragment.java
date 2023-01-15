package com.example.flykey.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.flykey.R;
import com.example.flykey.Item;
import com.example.flykey.MyAdapter;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

/**
 * This class is the fourth fragment which will be shown to the User.
 * @author Raphael Walger
 */
public class ThirdFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment3, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        control.MyControl myControl = control.MyControlFactory.produceControl();

        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewF3_1);

        String [] arrayWithTips = getResources().getStringArray(R.array.arrayWithTips);

        try {
            List<Item> itemList = myControl.convertStringArrayIntoArrayListWithItem(arrayWithTips);

            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            recyclerView.setAdapter(new MyAdapter(getActivity().getApplicationContext(), itemList));
        }catch (IllegalArgumentException iAE){
            this.createAndShowSnackbar(iAE.getMessage());
        }
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
