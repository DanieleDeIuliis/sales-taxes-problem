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

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

/**
 * Utility class to compute the type of an item based on its name
 */
@Slf4j
public class ItemTypeUtils {

    public enum ItemType {
        BOOK,
        FOOD,
        MEDICINE,
        GENERAL
    }

    private static Map<String, ItemType> itemToTypeMap = new HashMap<>() {{
        put("chocolate bar", ItemType.FOOD);
        put("book", ItemType.BOOK);
        put("headache pills", ItemType.MEDICINE);
        put("bottle of perfume", ItemType.GENERAL);
    }};

    /**
     * retrieves the type of the item based on its name. GENERAL is the default type
     * @param itemName name of the item
     * @return the type of the item
     */
    public static ItemType getItemTypeFromName(String itemName){
        ItemType type;
        if(itemToTypeMap.containsKey(itemName)){
            type = itemToTypeMap.get(itemName);
        }else{
            type = ItemType.GENERAL;
        }
        log.debug("Type of {}: {}", itemName, type);
        return type;
    }
}
