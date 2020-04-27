package com.zampa.salestaxes;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Good {

    private int quantity;
    private String name;
    private float price;
    private boolean imported;

    final static String REGEX = "(\\d+) ([a-zA-Z ]+) at (\\d+\\.?\\d+)";
    final static Pattern PATTERN = Pattern.compile(REGEX);


    private Good(int quantity, String name, float price, boolean imported) {
        this.quantity = quantity;
        this.name = name;
        this.price = price;
        this.imported = imported;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getName() {
        return name;
    }

    public float getPrice() {
        return price;
    }

    public boolean isImported() {
        return imported;
    }

    @Override
    public String toString() {
        return String.format("%s %s: %s", quantity, name, price);
    }

    /*****************************************
     * Static constructors with inputs check *
     *****************************************/

    public static Good build(int quantity, String name, float price) throws IllegalArgumentException {

        // check non-negative quantities
        if (quantity < 1) {
            throw new IllegalArgumentException(String.format("Quantity < 1: %s", quantity));
        }

        // check non empty identifier
        if (name.isEmpty()) {
            throw new IllegalArgumentException(String.format("Empty name: %s", name));
        }

        // check non negative price
        if (price < 0) {
            throw new IllegalArgumentException(String.format("Price < 0: %s", price));
        }

        boolean imported = name.startsWith("imported");

        return new Good(quantity, name, price, imported);
    }

    public static Good build(String inputString) throws IllegalArgumentException {

        final Matcher matcher = PATTERN.matcher(inputString);

        if (matcher.find()) {
            int quantity = Integer.parseInt(matcher.group(1));
            String name = matcher.group(2);
            float price = Float.parseFloat(matcher.group(3));

            return build(quantity, name, price);
        }
        else {
            throw new IllegalArgumentException(String.format("Invalid input: %s", inputString));
        }
    }

}
