import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

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
    private TableColumn<Item, Double> colValue;

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
        colValue.setCellValueFactory(new PropertyValueFactory<Item, Double>("value"));

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
        colName.setOnEditCommit(e-> {
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setName(e.getNewValue());
        });

        colSerial.setCellFactory(TextFieldTableCell.forTableColumn());
        colSerial.setOnEditCommit(e-> {
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setSerial(e.getNewValue());
        });

        colValue.setOnEditCommit(e-> {
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
        call List.deleteItem, pass description
         */
        Item it = table.getSelectionModel().getSelectedItem();
        table.getItems().removeAll(it);
        list.deleteItem(it);
    }

    @FXML
    void loadButtonClicked(ActionEvent event) {

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
        call List.setDirectory
        call List.createHTMLFile
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
    void saveAsJsonClicked(ActionEvent event) throws IOException {
        /* (save current list)
        prompt user for directory:
        create new text input dialog
        set title of text input dialog
        set content of input dialog to "please input directory"
        set field as string and show and wait

        create new text field and make editable
        collect user input
        call List.setDirectory
        call List.createJSONFile
        */
        TextInputDialog textInput = new TextInputDialog();
        textInput.setTitle("Save list");
        textInput.getDialogPane().setContentText("Please input directory:");

        Optional<String> result = textInput.showAndWait();

        TextField input = textInput.getEditor();

        list.setDirectory(input.getText());
        list.createJSONFile();
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
        call List.setDirectory
        call List.createTSVFile
        */
        TextInputDialog textInput = new TextInputDialog();
        textInput.setTitle("Save list");
        textInput.getDialogPane().setContentText("Please input directory:");

        Optional<String> result = textInput.showAndWait();

        TextField input = textInput.getEditor();

        list.setDirectory(input.getText());
        list.createTSVFile();
    }
}
