package com.zampa.salestaxes;

import org.junit.Assert;

public class Test {

    @org.junit.Test
    public void testGoodParse() {
        String input = "5 chocolate cornet at 1.99";

        Good good = Good.build(input);
        int quantity = good.getQuantity();
        String name = good.getName();
        float price = good.getPrice();

        Assert.assertEquals("Failed to parse quantity.", 5, quantity);
        Assert.assertEquals("Failed to parse name.", "chocolate cornet", name);
        Assert.assertEquals("Failed to parse price.", 1.99F, price, 0);
        Assert.assertEquals("Failed to parse toString()", "5 chocolate cornet: 1.99", good.toString());
    }

    @org.junit.Test
    public void testBasketParse() {
        String input = "5 chocolate cornet at 1.99\n"
                + "9 tangerine at 0.09\n";

        Basket basket = Basket.build(input);

        int numberOfGoodsInBasket = basket.getGoods().size();
        Assert.assertEquals("Wrong number of goods in the basket.", 2, numberOfGoodsInBasket);

        Assert.assertEquals("Failed to parse quantity for first good.",
                5,
                basket.getGoods().get(0).getQuantity());
        Assert.assertEquals("Failed to parse name for first good.",
                "chocolate cornet",
                basket.getGoods().get(0).getName());
        Assert.assertEquals("Failed to parse price for first good.",
                1.99F,
                basket.getGoods().get(0).getPrice(),
                0);

        Assert.assertEquals("Failed to parse quantity for second good.",
                9,
                basket.getGoods().get(1).getQuantity());
        Assert.assertEquals("Failed to parse name for second good.",
                "tangerine",
                basket.getGoods().get(1).getName());
        Assert.assertEquals("Failed to parse price for second good.",
                0.09F,
                basket.getGoods().get(1).getPrice(),
                0);

    }
}
