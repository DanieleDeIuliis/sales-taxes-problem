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
import com.salestaxes.model.OrderItem;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public class InputParserUtils {

    /**
     * Create a list of OrderItem from an input .txt file
     * @param inputFile file .txt containing the input
     * @return a list of corresponding OrderItem
     */
    public static List<OrderItem> parseInputFromFile(File inputFile) throws FileNotFoundException {
        List<OrderItem> orderedItems = new ArrayList<>();
        try {
            Scanner scanInput = new Scanner(inputFile);
            while(scanInput.hasNextLine()){
                String line = scanInput.nextLine();
                List<String> splittedLine = splitLine(line);
                int quantity = Integer.valueOf(splittedLine.get(0));
                if(quantity > 0){
                    orderedItems.add(createOrderItem(splittedLine));
                }else{
                    log.warn(String.format("Quantity value not valid, " +
                            "creation of item {} will be skipped."), splittedLine.get(1));
                }
            }
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("Input file not found. Insert an existing file name");
        }
        return orderedItems;
    }

    /**
     * Reads a line from an input file and extrapolates the relevant information
     * @param line a line from an input file with the following structure:
     *             [quantity] [name] at [price]
     * @return a list of quantity, name and price
     */
    private static List<String> splitLine(String line){
        List<String> splittedLineToReturn = new ArrayList<>();
        String regex = "([0-9]*)(.*)(at[ ]*)([0-9]*\\.?[0-9]*)";
        Pattern patternToMatch = Pattern.compile(regex);
        Matcher patternMatched = patternToMatch.matcher(line);
        while(patternMatched.find()){
            splittedLineToReturn.addAll(List.of(
                    patternMatched.group(1).trim(),
                    patternMatched.group(2).trim(),
                    patternMatched.group(4).trim()
            ));
        }
        return splittedLineToReturn;
    }

    /**
     * Creates an OrderItem object from a list of strings
     * @param splittedInput list of quantity, name and price
     * @return the corresponding OrderItem
     */
    private static OrderItem createOrderItem(List<String> splittedInput){
        boolean isImported = splittedInput.get(1).contains("imported");
        Item item = new Item(splittedInput.get(1),Double.valueOf(splittedInput.get(2)), isImported);
        return new OrderItem(item, Integer.valueOf(splittedInput.get(0)));
    }
}
