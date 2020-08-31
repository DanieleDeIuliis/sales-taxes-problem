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
package com.salestaxes.consoleapp;

import com.salestaxes.model.Item;
import com.salestaxes.model.ItemTypeUtils;
import com.salestaxes.model.OrderItem;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

public class InputParserUtilsTest {

    @Test
    public void testParseInputFromFile() throws FileNotFoundException {
        File inputTestFile = new File(
                InputParserUtilsTest.class.getClassLoader().getResource("parseInputTest.txt").getFile());
        List<OrderItem> orderedItems = InputParserUtils.parseInputFromFile(inputTestFile);
        Assertions.assertEquals(1, orderedItems.size());
        Item item = orderedItems.get(0).getItem();
        Assertions.assertEquals("imported box of chocolates", item.getName());
        Assertions.assertEquals(5,item.getTaxAmount());
        Assertions.assertEquals(ItemTypeUtils.ItemType.FOOD, item.getType());
        Assertions.assertEquals(10.50,item.getPrice());
        Assertions.assertEquals(true, item.isImported());
        Assertions.assertEquals(2, orderedItems.get(0).getQuantity());
    }

    @Test
    public void testParseInputFromFileWithZeroQuantity() throws FileNotFoundException {
        File inputTestFile = new File(
                InputParserUtilsTest.class.getClassLoader().getResource("parseInputZeroQuantityTest.txt").getFile());
        List<OrderItem> orderedItems = InputParserUtils.parseInputFromFile(inputTestFile);
        Assertions.assertEquals(0, orderedItems.size());
    }

    @Test
    public void testParseInputFromFileNotFound(){
        Assertions.assertThrows(FileNotFoundException.class, () -> {
            InputParserUtils.parseInputFromFile(new File(""));
        });
    }
}
