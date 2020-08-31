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

import com.salestaxes.model.ItemTypeUtils.ItemType;
import lombok.extern.slf4j.Slf4j;

/**
 * Representation of an item with the total percentage amount of taxes
 */
@Slf4j
@Data
public class Item {

    private String name;
    private double price;
    private boolean isImported;
    private int taxAmount;
    private ItemType type;



    public Item(String name, double price, boolean isImported){
        this.name = name;
        this.price = price;
        this.isImported = isImported;
        this.type = ItemTypeUtils.getItemTypeFromName(name);
        this.taxAmount = computeTaxAmount();
    }

    /**
     * Computes the total amount of taxes based on the item type and
     * if it's imported
     * @return the total amount of taxes
     */
    private int computeTaxAmount(){
        int totalTaxAmount = 0;
        if(type.equals(ItemType.GENERAL)) {
            totalTaxAmount = 10;
        }
        if(isImported){
            totalTaxAmount += 5;
        }
        log.debug("Total tax amount for item {}, of type {}: {}", name, type, totalTaxAmount);
        return totalTaxAmount;
    }
}
