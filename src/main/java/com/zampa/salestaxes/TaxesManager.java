package com.zampa.salestaxes;

import java.util.Collection;
import java.util.HashSet;

public final class TaxesManager {

    public enum GoodType {
        OTHER(0.10F),
        BOOK(0F),
        FOOD(0F),
        MEDICAL_PRODUCT(0F);

        public final float tax;

        GoodType(float tax) {
            this.tax = tax;
        }
    }


    private static final float IMPORT_TAX = 0.05F;

    private static final Collection<String> bookKeywords = new HashSet<String>() {{
        add("book");
        add("volume");
        add("comic");
        add("novel");
        add("manual");
    }};

    private static final Collection<String> foodKeywords = new HashSet<String>() {{
        add("chocolate bar");
        add("box of chocolates");
        add("chocolates");
        add("tangerine");
        add("chocolate cornet");
    }};

    private static final Collection<String> medicalKeywords = new HashSet<String>() {{
        add("headache pills");
        add("painkiller");
    }};


    public static float getSaleTax(String itemName) {
        return getGoodType(itemName).tax;
    }

    public static float getImportTax() {
        return IMPORT_TAX;
    }

    public static GoodType getGoodType(String itemName) {
        if (bookKeywords.stream().anyMatch(good -> itemName.contains(good))) {
            return GoodType.BOOK;
        }
        if (foodKeywords.stream().anyMatch(good -> itemName.contains(good))) {
            return GoodType.FOOD;
        }
        if (medicalKeywords.stream().anyMatch(good -> itemName.contains(good))) {
            return GoodType.MEDICAL_PRODUCT;
        }

        return GoodType.OTHER;
    }

    public static float round(double number) {
        return (float) (Math.ceil(number * 20D) / 20D);
    }

}
