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

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ConsoleAppMainTest {

    private static final String INPUT_DIR = "consoleAppTest/input/";
    private static final String OUTPUT_DIR = "consoleAppTest/expectedOutput/";
    private static final String FILE_EXTENSION = ".txt";

    @ParameterizedTest
    @ValueSource(strings = {"test1", "test2","test3","skipItem","test3priceComma"})
    public void testCallComputePriceAndTaxesService(String fileName) throws FileNotFoundException {
        String pathToTestFile =
                this.getClass().getClassLoader().
                        getResource(INPUT_DIR + fileName + FILE_EXTENSION).getFile();
        String result = ConsoleAppMain
                .callComputePriceAndTaxesService(pathToTestFile);
        String expectedOutput = getExpectedResultFromOutputFile(fileName);
        Assertions.assertEquals(expectedOutput, result);
    }

    @Test
    public void testCallComputePriceAndTaxesServiceFileNotFound(){
        Assertions.assertThrows(FileNotFoundException.class, () -> {
            ConsoleAppMain
                    .callComputePriceAndTaxesService("notExistingFile");
        });
    }

    private String getExpectedResultFromOutputFile(String fileName) throws FileNotFoundException {
        StringBuilder outputBuilder = new StringBuilder();
        File outputFile = new File(this.getClass().getClassLoader().
                getResource(OUTPUT_DIR + fileName + FILE_EXTENSION).getFile());
        Scanner outputScan = new Scanner(outputFile);
        while(outputScan.hasNextLine()){
            outputBuilder.append(String.format("%s\n",outputScan.nextLine()));
        }
        return outputBuilder.toString();
    }
}
