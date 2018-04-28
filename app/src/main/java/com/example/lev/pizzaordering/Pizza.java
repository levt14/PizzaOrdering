package com.example.lev.pizzaordering;

import android.widget.CheckBox;
import android.widget.RadioButton;
import java.util.ArrayList;

/**
 * Created by LEV on 23/12/2017.
 */

public class Pizza {

    private String size;
    private ArrayList<String> extras;
    private double priceOfSize;
    private double priceOfExtra;
    private double totalPrice;

    public Pizza() {
        this.size = "unknown";
        this.extras = new ArrayList<String>();
        this.priceOfSize = 0;
        this.priceOfExtra = 0;
        this.totalPrice = 0;
    }

    public Pizza(String size, ArrayList<String> extras, double priceOfSize, double priceOfExtra, double totalPrice) {
        this.size = size;
        this.extras = extras;
        this.priceOfSize = priceOfSize;
        this.priceOfExtra = priceOfExtra;
        this.totalPrice = totalPrice;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public ArrayList<String> getExtras() {
        return extras;
    }

    public void setExtras(ArrayList<String> extras) {
        this.extras = extras;
    }

    public double getPriceOfSize() {
        return priceOfSize;
    }

    public void setPriceOfSize(double priceOfSize) {
        this.priceOfSize = priceOfSize;
    }

    public double getPriceOfExtra() {
        return priceOfExtra;
    }

    public void setPriceOfExtra(double priceOfExtra) {
        this.priceOfExtra = priceOfExtra;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void addExtra(String extra) {
        this.extras.add(extra);
    }
    public void removeExtra(CheckBox extra) {
        int i = 0;
        for (String ext: this.extras) {
            if(ext == extra.getText().toString()) {
                this.extras.remove(i);
                break;
            }
            i++;
        }

    }

    public void clearExtras() {
        this.extras = new ArrayList<String>();
    }

    public void priceSizeCalculation(int price, RadioButton pizzaSize) {

        if(pizzaSize.isChecked()) {
                this.priceOfSize = price;
                setSize(pizzaSize.getText().toString());
        }
    }

    public void priceExtraCalculation(int price, CheckBox extra) {

        if (extra.isChecked()) {
            this.priceOfExtra += price;
            addExtra(extra.getText().toString());
        } else {
            this.priceOfExtra -= price;
            removeExtra(extra);
        }
    }

    public double sizeAndExtrasCalculation() {
        this.totalPrice = this.priceOfSize + this.priceOfExtra;
        return this.totalPrice;
    }

}
