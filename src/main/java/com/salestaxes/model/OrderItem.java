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

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 * Class to model an item and its quantity inside an order request
 */
@Slf4j
@Data
public class OrderItem {

    private Item item;
    private int quantity;
    private DecimalFormat taxAndCostFormat;

    public OrderItem(Item item, int quantity) {
        this.item = item;
        this.quantity = quantity;
        this.taxAndCostFormat = new DecimalFormat("#.##");
        this.taxAndCostFormat.setRoundingMode(RoundingMode.HALF_UP);
    }

    /**
     * Computes the total cost for an ordered item based on its quantity
     * @return total cost of the ordered item
     */
    public double computeTotalCost(){
        double totalCost = Double.valueOf(taxAndCostFormat.format(computeUnitCost() * quantity));
        log.debug("Total cost for item {}: {}", item.getName(), totalCost);
        return totalCost;
    }

    /**
     * Computes the taxed amount to be added to the price
     * @return taxed amount to add
     */
    public double computeTaxAmount(){
        double taxedAmountBeforeRounding = item.getPrice() * (item.getTaxAmount() / 100d);
        BigDecimal v = new BigDecimal(taxedAmountBeforeRounding);
        BigDecimal increment = new BigDecimal(0.05);
        BigDecimal numbersOfIncrementInTaxesAmount = v.divide(increment,0, RoundingMode.UP);
        BigDecimal taxedAmountAfterRounding = numbersOfIncrementInTaxesAmount.multiply(increment);
        return  Double.valueOf(taxAndCostFormat.format(taxedAmountAfterRounding.doubleValue()));
    }
    /**
     * Computes the cost of a single ordered item based on its taxes amount
     * @return the unit cost + computed taxes amount
     */
    private double computeUnitCost(){
        double unitCost = item.getPrice() + computeTaxAmount();
        log.debug("Unit cost for item {}: {}", item.getName(), unitCost);
        return unitCost;
    }
}
