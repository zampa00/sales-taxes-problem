package com.zampa.salestaxes;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Basket {

    List<Good> goods;

    public Basket(List<Good> goods) {
        this.goods = goods;
    }

    public List<Good> getGoods() {
        return goods;
    }

    public float getTaxes() {
        return (float) goods.stream().mapToDouble(good -> good.getQuantity() * good.getTaxes()).sum();
    }

    public float getTotal() {
        return (float) goods.stream().mapToDouble(good -> good.getTotalAfterTaxes()).sum();
    }

    @Override
    public String toString() {
        String goodsList = goods.stream().map(good -> good.toString()).collect(Collectors.joining("\n"));

        return String.format("%s\nSales Taxes: %.2f\nTotal: %.2f", goodsList, getTaxes(), getTotal())
                .replace(',', '.');
    }

    /**********************
     * Static constructor *
     **********************/

    public static Basket build(String inputString) {
        String[] entries = inputString.split("\n");

        List<Good> goods = Arrays.stream(entries)
                .filter(entry -> !entry.isEmpty())
                .map(entry -> Good.build(entry))
                .collect(Collectors.toList());

        return new Basket(goods);
    }

}
