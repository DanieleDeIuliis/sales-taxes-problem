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
package com.salestaxes.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Data
@AllArgsConstructor
public class OrderItem {

    private Item item;
    private int quantity;

    /**
     * Computes the total cost for an ordered item based on its quantity
     * @return total cost of the ordered item
     */
    public double computeTotalCost(){
        double totalCostBeforeRounding = computeUnitCost() * quantity;
        double totalCostAfterRounding = Math.round(totalCostBeforeRounding * 20) / 20.0;
        log.debug("Total cost for item {}: {}", item.getName(), totalCostAfterRounding);
        return totalCostAfterRounding;
    }

    /**
     * Computes the cost of a single ordered item based on its taxes amount
     * @return the unit cost + computed taxes amount
     */
    private double computeUnitCost(){
        double taxAmountOverPrice = item.getPrice() * (item.getTaxAmount() / 100d);
        double unitCost = item.getPrice() + taxAmountOverPrice;
        log.debug("Unit cost for item {}: {}", item.getName(), unitCost);
        return unitCost;
    }
}
