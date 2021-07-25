import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

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
        invList.add(new Item("name", "serial", 00.00));
    }

    public void deleteItem(Item it) {
        /*
        remove item from list
         */
        invList.remove(it);
    }

    public void createTSVFile() throws IOException {
        /*
        create new text file using the directory string
        the file's name is the list's title
        create filewriter
        loop thru items and print name, serial, and value on a line separated by tabs
        close writer
         */
        File file = new File(directory + "inventory" + ".txt");
        FileWriter writer = new FileWriter(file);

        for(Item it : invList) {
            writer.write(it.getName() + "    " + it.getSerial() + "   " + it.getValue() + "\n");
        }

        writer.close();
    }

    public void createHTMLFile() throws IOException {
        /*
        create new text file using the directory string
        the file's name is the list's title
        create filewriter
        call write for title
        loop thru items and print desc, date, and status on a line separated by commas
        close writer
         */
        File file = new File(directory + title + ".txt");
        FileWriter writer = new FileWriter(file);

        for(Item it : itemList) {
            System.out.println(it.description + it.dueDate.getValue() + it.getStatus().isSelected());
            writer.write(it.description + "," + it.dueDate.getValue() + "," + it.getStatus().isSelected() + "\n");
        }

        writer.close();
    }

    public void createJSONFile() throws IOException {
        /*
        create new text file using the directory string
        the file's name is the list's title
        create filewriter
        call write for title
        loop thru items and print desc, date, and status on a line separated by commas
        close writer
         */
        File file = new File(directory + title + ".txt");
        FileWriter writer = new FileWriter(file);

        for(Item it : itemList) {
            System.out.println(it.description + it.dueDate.getValue() + it.getStatus().isSelected());
            writer.write(it.description + "," + it.dueDate.getValue() + "," + it.getStatus().isSelected() + "\n");
        }

        writer.close();
    }
}
