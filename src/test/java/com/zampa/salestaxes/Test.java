package com.zampa.salestaxes;

import org.junit.Assert;

public class Test {

    @org.junit.Test
    public void testParse() {
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
}
