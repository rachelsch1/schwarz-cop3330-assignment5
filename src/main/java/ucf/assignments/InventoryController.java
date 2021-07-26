package ucf.assignments;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class InventoryController implements Initializable {
    @FXML
    private TextField searchBar;

    @FXML
    private TableView<Item> table;

    @FXML
    private TableColumn<Item, String> colName;

    @FXML
    private TableColumn<Item, String> colSerial;

    @FXML
    private TableColumn<Item, String> colValue;

    public static List list = new List();

    public void initialize(URL location, ResourceBundle resources) {
        // call initTable
        initTable();
    }

    private void initTable() {
        // call initCols
        initCols();
    }

    private void initCols() {
        /*
        set name value as "name"
        set serial value as "serial"
        set value as "value"

        call editTableCols
        */
        colName.setCellValueFactory(new PropertyValueFactory<Item, String>("name"));
        colSerial.setCellValueFactory(new PropertyValueFactory<Item, String>("serial"));
        colValue.setCellValueFactory(new PropertyValueFactory<Item, String>("value"));

        editableCols();
    }

    private void editableCols() {
        /*
         name:
         set cell as text field
         set value as user input

         serial:
         set cell as text field
         set value as user input

         value:
         set cell as text field
         set value as user input

         set table info to editable
         */

        colName.setCellFactory(TextFieldTableCell.forTableColumn());
        colName.setOnEditCommit(e -> {
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setName(e.getNewValue());
        });

        colSerial.setCellFactory(TextFieldTableCell.forTableColumn());
        colSerial.setOnEditCommit(e -> {
            int flag = 0;
            for(Item it : list.invList) {
                if(e.getNewValue().equals(it.getSerial())) {
                    flag = 1;
                }
            }

            if(flag == 0) {
                e.getTableView().getItems().get(e.getTablePosition().getRow()).setSerial(e.getNewValue());
            } else {
                e.getTableView().getItems().get(e.getTablePosition().getRow()).setSerial("XXXXXXXXXX");
                Stage dialogStage = new Stage();

                VBox vbox = new VBox(new Text("ERROR: Serial number already in use."));
                vbox.setAlignment(Pos.CENTER);
                vbox.setPadding(new Insets(20));

                dialogStage.setScene(new Scene(vbox));
                dialogStage.show();
            }

        });

        colValue.setCellFactory(TextFieldTableCell.forTableColumn());
        colValue.setOnEditCommit(e -> {
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setValue(e.getNewValue());
        });


        table.setEditable(true);

    }
    private void loadData() {
        /*
        call addItem
        set table items as invList
         */
        list.addItem();
        table.setItems(list.invList);
    }

    @FXML
    void addButtonClicked(ActionEvent event) {
        /*
        call loadData which adds a new row to the table
         */
        loadData();
    }

    @FXML
    void deleteButtonClicked(ActionEvent event) {
        /*
        remove row from tableview
        call list.deleteItem, pass selected item
         */
        Item it = table.getSelectionModel().getSelectedItem();
        table.getItems().removeAll(it);
        list.deleteItem(it);
    }

    @FXML
    void loadTSVButtonClicked(ActionEvent event) throws FileNotFoundException {
        /*
        prompt user for directory:
        create new text input dialog
        set title of text input dialog
        set content of input dialog to "please input directory"
        set field as string and show and wait

        collect input
        call list.loadInventoryTSV to open and read the file
        set items as the updated invList
         */
        TextInputDialog textInput = new TextInputDialog();
        textInput.setTitle("Load inventory");
        textInput.getDialogPane().setContentText("Please input directory, including file name and extension:");

        Optional<String> result = textInput.showAndWait();

        TextField input = textInput.getEditor();

        list.loadInventoryTSV(input.getText());

        table.setItems(list.invList);
    }

    @FXML
    void loadHTMLButtonClicked(ActionEvent event) throws FileNotFoundException {
        /*
        prompt user for directory:
        create new text input dialog
        set title of text input dialog
        set content of input dialog to "please input directory"
        set field as string and show and wait

        collect input
        call list.loadInventoryHTML to open and read the file
        set items as the updated invList
         */
        TextInputDialog textInput = new TextInputDialog();
        textInput.setTitle("Load inventory");
        textInput.getDialogPane().setContentText("Please input directory, including file name and extension:");

        Optional<String> result = textInput.showAndWait();

        TextField input = textInput.getEditor();

        list.loadInventoryHTML(input.getText());

        table.setItems(list.invList);
    }

    @FXML
    void saveAsHtmlClicked(ActionEvent event) throws IOException {
        /* (save current list)
        prompt user for directory:
        create new text input dialog
        set title of text input dialog
        set content of input dialog to "please input directory"
        set field as string and show and wait

        create new text field and make editable
        collect user input
        call list.setDirectory
        call list.createHTMLFile
        */
        TextInputDialog textInput = new TextInputDialog();
        textInput.setTitle("Save list");
        textInput.getDialogPane().setContentText("Please input directory:");

        Optional<String> result = textInput.showAndWait();

        TextField input = textInput.getEditor();

        list.setDirectory(input.getText());
        list.createHTMLFile();
    }

    @FXML
    void saveAsTsvClicked(ActionEvent event) throws IOException {
        /* (save current list)
        prompt user for directory:
        create new text input dialog
        set title of text input dialog
        set content of input dialog to "please input directory"
        set field as string and show and wait

        create new text field and make editable
        collect user input
        call list.setDirectory
        call list.createTSVFile
        */
        TextInputDialog textInput = new TextInputDialog();
        textInput.setTitle("Save inventory");
        textInput.getDialogPane().setContentText("Please input directory:");

        Optional<String> result = textInput.showAndWait();

        TextField input = textInput.getEditor();

        list.setDirectory(input.getText());
        list.createTSVFile();
    }

    @FXML
    void searchUsed(ActionEvent event) {
        /*
        create new observable list
        get text from search bar
        set searchList equal to list.search and pass txt
        display searchList in table
         */

        ObservableList<Item> searchList = FXCollections.observableArrayList();

        String txt = searchBar.getText();
        searchList = list.search(txt);

        table.setItems(searchList);
    }

    @FXML
    void showAllButtonClicked(ActionEvent event) {
        /*
        set table items as list.invList
         */
        table.setItems(list.invList);
    }
}
