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

import com.salestaxes.model.OrderItem;
import com.salestaxes.service.ComputePriceAndTaxes;
import com.salestaxes.service.ComputePriceAndTaxesImpl;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.List;
import java.util.Scanner;

public class ConsoleAppMain {

    public static String callComputePriceAndTaxesService(String inputFileName) throws FileNotFoundException {
        ComputePriceAndTaxes service = new ComputePriceAndTaxesImpl();
        List<OrderItem> orderedItems = null;
        try{
            File inputFile = new File(inputFileName);
            orderedItems = InputParserUtils.parseInputFromFile(inputFile);
        }catch(FileNotFoundException e){
            throw new FileNotFoundException("Input file not found. Insert an existing file name");
        }
        return service.computeTotalPriceWithTaxes(orderedItems);
    }

    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("Please insert the name of the input file including the extension:");
        Scanner inputReader = new Scanner(System.in);
        String inputFileName = inputReader.nextLine();
        System.out.println(callComputePriceAndTaxesService(inputFileName));
    }
}
