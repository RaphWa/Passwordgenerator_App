package com.example.flykey;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * This class is the ViewHolder for the RecyclerView.
 */
public class MyViewHolder extends RecyclerView.ViewHolder{

    TextView textViewI;

    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        textViewI = itemView.findViewById(R.id.textViewItem);
    }
}
