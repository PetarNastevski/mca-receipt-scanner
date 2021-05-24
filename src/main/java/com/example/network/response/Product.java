package com.example.network.response;


public class Product {

    private String name;
    private boolean domestic;
    private double price;
    private int weight;
    private String description;


    public String getName() {
        return name;
    }

    public boolean isDomestic() {
        return domestic;
    }

    public double getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        if (this.weight == 0.0)
            return "... " + this.name + "\n" + "    Price: " + "$" + this.price + "\n" + "    " + this.description + "\n    Weight: N/A";
        else
            return "... " + this.name + "\n" + "    Price: " + "$" + this.price + "\n" + "    " + this.description + "\n    Weight: " + this.weight + "g";
    }

}
