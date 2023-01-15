package com.example.flykey;

/**
 * This class represents one item in the RecyclerView.
 * It contains one String.
 */
public class Item {
    private String tip;

    public Item(String tip) {
        this.tip = tip;
    }

    public String getTip() {
        return tip;
    }
}
