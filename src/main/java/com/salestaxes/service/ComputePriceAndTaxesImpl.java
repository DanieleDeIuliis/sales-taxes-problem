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

import com.salestaxes.model.OrderItem;

import java.util.List;

/**
 * Implementation of the service interface to compute the total prices and taxes
 * for a list of items
 */
public class ComputePriceAndTaxesImpl implements ComputePriceAndTaxes{

    /**
     * Computes the total price and taxes amount for a list of items
     * @param orderedItems list of items with the corresponding quantity
     * @return a string representing the final bill
     */
    @Override
    public String computeTotalPriceWithTaxes(List<OrderItem> orderedItems) {
        StringBuilder outputBuilder = new StringBuilder();
        double totalTaxedAmount = 0;
        double totalCost = 0;
        for(OrderItem item : orderedItems){
            outputBuilder.append(String.format( "%d %s%s: %.2f\n",
                    item.getQuantity(),
                    item.getItem().isImported() ? "imported " : "",
                    item.getItem().getName(),
                    item.computeTotalCost()));
            totalTaxedAmount += item.computeTaxAmount() * item.getQuantity();
            totalCost += item.computeTotalCost();
        }
        outputBuilder.append(String.format("Sales Taxes: %.2f\n", totalTaxedAmount));
        outputBuilder.append(String.format("Total: %.2f\n", totalCost));
        return  outputBuilder.toString();
    }
}
