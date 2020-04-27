package com.zampa.salestaxes;

import org.junit.Assert;

public class Test {

    @org.junit.Test
    public void testGoodParse() {
        String input = "2 chocolate cornet at 1.99";

        Good good = Good.build(input);
        int quantity = good.getQuantity();
        String name = good.getName();
        float price = good.getPrice();

        Assert.assertEquals("Failed to parse quantity.", 2, quantity);
        Assert.assertEquals("Failed to parse name.", "chocolate cornet", name);
        Assert.assertEquals("Failed to parse price.", 1.99F, price, 0);
        Assert.assertEquals("Failed to parse toString()", "2 chocolate cornet: 3.98", good.toString());
    }

    @org.junit.Test
    public void testImported() {
        String input1 = "5 chocolate cornet at 1.99";
        String input2 = "5 imported chocolate cornet at 1.99";

        boolean firstGoodImported = Good.build(input1).isImported();
        boolean secondGoodImported = Good.build(input2).isImported();

        Assert.assertFalse("Wrongly recognized first good as imported.", firstGoodImported);
        Assert.assertTrue("Failed to recognize second good as imported.", secondGoodImported);
    }

    @org.junit.Test
    public void testBasketParse() {
        String input = "5 chocolate cornet at 1.99\n"
                + "9 imported tangerine at 0.09\n";

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
                9.95F,
                basket.getGoods().get(0).getTotalAfterTaxes(),
                0);

        Assert.assertEquals("Failed to parse quantity for second good.",
                9,
                basket.getGoods().get(1).getQuantity());
        Assert.assertEquals("Failed to parse name for second good.",
                "imported tangerine",
                basket.getGoods().get(1).getName());
        Assert.assertEquals("Failed to parse price for second good.",
                1.26F,
                basket.getGoods().get(1).getTotalAfterTaxes(),
                0);
    }

    // Testing inputs from examples

    @org.junit.Test
    public void testInput1() {
        String input = "2 book at 12.49\n" +
                "1 music CD at 14.99\n" +
                "1 chocolate bar at 0.85";
        String output = "2 book: 24.98\n" +
                "1 music CD: 16.49\n" +
                "1 chocolate bar: 0.85\n" +
                "Sales Taxes: 1.50\n" +
                "Total: 42.32";

        Basket basket = Basket.build(input);

        Assert.assertEquals("Wrong number of goods in the basket.", 3, basket.getGoods().size());

        Assert.assertEquals("Wrong price for first item.",
                24.98F,
                basket.getGoods().get(0).getTotalAfterTaxes(),
                0);
        Assert.assertEquals("Wrong price for second item.",
                16.49F,
                basket.getGoods().get(1).getTotalAfterTaxes(),
                0);
        Assert.assertEquals("Wrong price for third item.",
                0.85F,
                basket.getGoods().get(2).getTotalAfterTaxes(),
                0);

        Assert.assertEquals("", output, basket.toString());
    }

    @org.junit.Test
    public void testInput2() {
        String input = "1 imported box of chocolates at 10.00\n" +
                "1 imported bottle of perfume at 47.50";
        String output = "1 imported box of chocolates: 10.50\n" +
                "1 imported bottle of perfume: 54.65\n" +
                "Sales Taxes: 7.65\n" +
                "Total: 65.15";

        Basket basket = Basket.build(input);

        Assert.assertEquals("Wrong number of goods in the basket.", 2, basket.getGoods().size());

        Assert.assertEquals("Wrong price for first item.",
                10.50F,
                basket.getGoods().get(0).getTotalAfterTaxes(),
                0);
        Assert.assertEquals("Wrong price for second item.",
                54.65F,
                basket.getGoods().get(1).getTotalAfterTaxes(),
                0);

        Assert.assertEquals("", output, basket.toString());
    }

    @org.junit.Test
    public void testInput3() {
        String input = "1 imported bottle of perfume at 27.99\n" +
                "1 bottle of perfume at 18.99\n" +
                "1 packet of headache pills at 9.75\n" +
                "3 box of imported chocolates at 11.25";
        String output = "1 imported bottle of perfume: 32.19\n" +
                "1 bottle of perfume: 20.89\n" +
                "1 packet of headache pills: 9.75\n" +
                "3 imported box of chocolates: 35.55\n" +
                "Sales Taxes: 7.90\n" +
                "Total: 98.38";

        Basket basket = Basket.build(input);

        Assert.assertEquals("Wrong number of goods in the basket.", 4, basket.getGoods().size());

        Assert.assertEquals("Wrong price for first item.",
                32.19F,
                basket.getGoods().get(0).getTotalAfterTaxes(),
                0);
        Assert.assertEquals("Wrong price for second item.",
                20.89F,
                basket.getGoods().get(1).getTotalAfterTaxes(),
                0);
        Assert.assertEquals("Wrong price for third item.",
                9.75F,
                basket.getGoods().get(2).getTotalAfterTaxes(),
                0);
        Assert.assertEquals("Wrong price for fourth item.",
                35.55F,
                basket.getGoods().get(3).getTotalAfterTaxes(),
                0);

        Assert.assertEquals("", output, basket.toString());
    }
}
