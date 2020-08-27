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

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ItemTest {

    @Test
    public void createFoodItem(){
        Item chocolateBar = new Item("chocolate bar", 5, false);
        Assertions.assertEquals(0,chocolateBar.getTaxAmount());
    }
    @Test
    public void createImportedFoodItem(){
        Item chocolateBar = new Item("chocolate bar", 5, true);
        Assertions.assertEquals(5,chocolateBar.getTaxAmount());
    }
    @Test
    public void createGeneralItem(){
        Item chocolateBar = new Item("bottle of perfume", 30, false);
        Assertions.assertEquals(10,chocolateBar.getTaxAmount());
    }
    @Test
    public void createImportedGeneralItemNotFound(){
        Item chocolateBar = new Item("Video game", 25, true);
        Assertions.assertEquals(15,chocolateBar.getTaxAmount());
    }
}
