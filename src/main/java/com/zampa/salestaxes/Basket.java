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
