# sales-taxes-problem
## How to build
Go to the root of the project:

* **Compile and run unit tests:** mvn clean install / mvn install

The build will also run the unit tests. ConsoleAppMainTest.java is the component test class in which there's the parametrized
test *testCallComputePriceAndTaxesService* that reads in input a cart and compares the output with an expectedOutput file.

Input and Output files are in the corresponding folder under: src/main/resources/consoleAppTest.
## How to run
Go to the root of the project:

* **Run the program:** mvn exec:java

The program will ask the absolute path to a text file containing a single cart with the following format for each line:

itemQuantity itemName *at* itemPrice

Note: itemName might contain the attribute *imported*

Example:
```bash
1 imported bottle of perfume at 27.99
1 bottle of perfume at 18.99
1 packet of headache pills at 9.75
3 box of imported chocolates at 11.25
```

### Add new items with type
A set of known objects is imported from the file src/main/resources/itemData/itemsToType.txt.

In case an item found in input is not a *known* one, the GENERAL type will be assigned to it and will have the maximum tax rate. 

To add new items with the corresponding type, update the file in src/main/resources/itemData/itemsToType.txt using the
following format: **item name -- item type**