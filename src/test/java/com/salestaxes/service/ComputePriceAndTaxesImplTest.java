/*
MIT License

        Copyright (c) 2020 DanieleDeIuliis

        Permission is hereby granted, free of charge, to any person obtaining a copy
        of this software and associated documentation files (the "Software"), to deal
        in the Software without restriction, including without limitation the rights
        to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
        copies of the Software, and to permit persons to whom the Software is
        furnished to do so, subject to the following conditions:

        The above copyright notice and this permission notice shall be included in all
        copies or substantial portions of the Software.

        THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
        IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
        FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
        AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
        LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
        OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
        SOFTWARE.
*/
package com.salestaxes.service;

import com.salestaxes.model.Item;
import com.salestaxes.model.OrderItem;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class ComputePriceAndTaxesImplTest {
    private static ComputePriceAndTaxes priceAndTaxesService = new ComputePriceAndTaxesImpl();

    @Test
    public void testComputeTotalPriceWithTaxesNoImported(){
        List<OrderItem> orderedItems = new ArrayList<>(List.of(
                new OrderItem(new Item("book", 12.49, false),2),
                new OrderItem(new Item("music CD", 14.99, false),1),
                new OrderItem(new Item("chocolate bar", 0.85, false),1)
        ));
        String expectedOutput = "2 book: 24.98\n" +
                "1 music CD: 16.49\n" +
                "1 chocolate bar: 0.85\n" +
                "Sales Taxes: 1.50\n" +
                "Total: 42.32\n";
        Assertions.assertEquals(expectedOutput,
                priceAndTaxesService.computeTotalPriceWithTaxes(orderedItems));
    }
    @Test
    public void testComputeTotalPriceWithTaxesAllImported(){
        List<OrderItem> orderedItems = new ArrayList<>(List.of(
                new OrderItem(new Item("box of chocolates", 10.0, true),1),
                new OrderItem(new Item("bottle of perfume", 47.50, true),1)
        ));
        String expectedOutput = "1 imported box of chocolates: 10.50\n" +
                "1 imported bottle of perfume: 54.65\n" +
                "Sales Taxes: 7.65\n" +
                "Total: 65.15\n";
        Assertions.assertEquals(expectedOutput,
                priceAndTaxesService.computeTotalPriceWithTaxes(orderedItems));
    }
    @Test
    public void testComputeTotalPriceWithTaxesMixed(){
        List<OrderItem> orderedItems = new ArrayList<>(List.of(
                new OrderItem(new Item("bottle of perfume", 27.99, true),1),
                new OrderItem(new Item("bottle of perfume", 18.99, false),1),
                new OrderItem(new Item("packet of headache pills", 9.75, false),1),
                new OrderItem(new Item("box of chocolates", 11.25, true),3)
        ));
        String expectedOutput = "1 imported bottle of perfume: 32.19\n" +
                "1 bottle of perfume: 20.89\n" +
                "1 packet of headache pills: 9.75\n" +
                "3 imported box of chocolates: 35.55\n" +
                "Sales Taxes: 7.90\n" +
                "Total: 98.38\n";
        Assertions.assertEquals(expectedOutput,
                priceAndTaxesService.computeTotalPriceWithTaxes(orderedItems));
    }
}
