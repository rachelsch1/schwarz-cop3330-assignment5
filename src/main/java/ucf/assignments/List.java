package ucf.assignments;

/*
 *  UCF COP3330 Summer 2021 Assignment 5 Solution
 *  Copyright 2021 Rachel Schwarz
 */

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.io.*;
import java.util.Scanner;

public class List {
    /*
    has string "directory"
    has list "invList"
     */

    public static String directory;
    public static ObservableList<Item> invList = FXCollections.observableArrayList();

    public List() {
        /*
        declares an empty observable array list "invList"
         */
        invList = FXCollections.observableArrayList();
    }

    public static String getDirectory() {
        /*
        returns directory
         */
        return directory;
    }

    public static void setDirectory(String directory) {
        /*
        set directory
         */
        List.directory = directory;
    }

    public void addItem() {
        /*
        add new item to invList and populate vars with default values that will show in the table
         */
        invList.add(new Item("name", "XXXXXXXXXX", "00.00"));
    }

    public void deleteItem(Item it) {
        /*
        remove item from list
         */
        invList.remove(it);
    }

    public ObservableList<Item> search(String txt) {
        /*
        create new observable list
        for each item in invList, check if it contains the string in the search bar in either the name or serial variables
        if it does, add it to searchList
        return searchList
         */
        ObservableList<Item> searchList = FXCollections.observableArrayList();

        for(Item it : invList) {
            if(it.getName().contains(txt)) {
                searchList.add(it);
            } else if(it.getSerial().contains(txt)) {
                searchList.add(it);
            }
        }

        return searchList;
    }

    public void createTSVFile() throws IOException {
        /*
        create new text file using the directory string
        the file's name is the list's title
        create filewriter
        loop thru items and print name, serial, and value on a line separated by tabs
        close writer
         */
        File file = new File(directory + ".txt");
        FileWriter writer = new FileWriter(file);

        for(Item it : invList) {
            writer.write(it.getName() + "    " + it.getSerial() + "   " + it.getValue() + "\n");
        }

        writer.close();
    }

    public void createHTMLFile() throws IOException {
        /*
        create new html file using the directory string
        create new print writer
        establish table
        for each item, populate row with item's info
        end table
        close print writer
         */
        File file = new File(directory + ".html");
        PrintWriter pw = new PrintWriter(new FileWriter(file));
        pw.println("<TABLE BORDER><TR><TH>Name<TH>Serial<TH>Value</TR>");
        for(Item it : invList) {
            pw.println("<TR><TD>" + it.name + "<TD>" + it.serial + "<TD>" + it.value);
        }
        pw.println("</TABLE>");
        pw.close();
    }

    public void loadInventoryTSV(String dir) throws FileNotFoundException {
        /*
        clear previous list
        open file
        create new scanner
        set tab as delimiter
        for each line, create new item, add to list, and populate with info from the line
        close scanner
         */
        invList.clear();
        File file = new File(dir);
        Scanner input = new Scanner(file);

        input.useDelimiter("    ");

        while(input.hasNextLine()) {
            Item it = new Item("", "", "");
            invList.add(it);
            it.name = input.next();
            it.serial = input.next();
            it.value = input.next();
        }
        input.close();
    }


    public void loadInventoryHTML(String dir) throws FileNotFoundException {
        /*
        clear previous list
        open file
        create new scanner
        set <TD> and <TR> as delimiters
        skip first line
        for each line, create new item, add to list, and populate with info from the line
        close scanner
         */
        invList.clear();
        File file = new File(dir);
        Scanner input = new Scanner(file);

        input.useDelimiter("<TD>|<TR>");

        input.nextLine();
        while(input.hasNextLine()) {
            Item it = new Item("", "", "");
            invList.add(it);
            it.name = input.next();
            it.serial = input.next();
            it.value = input.next();
        }
        input.close();
    }
}
