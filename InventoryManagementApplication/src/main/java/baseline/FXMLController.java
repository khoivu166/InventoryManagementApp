package baseline;

import javafx.beans.binding.BooleanBinding;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.lang.invoke.StringConcatException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;
import java.util.regex.Pattern;

public class FXMLController implements Initializable
{


    public TextField nameTextField;
    public TextField priceTextField;
    public TextField serialNumText;
    public TableView<inventoryEditor> tableview;
    public TableColumn<inventoryEditor, String> nameColumn;
    public TableColumn<inventoryEditor, String> serialColumn;
    public TableColumn<inventoryEditor, String> priceColumn;
    public Label errorLabel;
    public TextField searchBox;
    public Button addButton;
    List list = new List();
    FileChooser fileChooser = new FileChooser();

    @FXML


    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        //initialize variables
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        serialColumn.setCellValueFactory(new PropertyValueFactory<>("serialNum"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        tableview.setItems(list.getInventoryList());
        tableview.setEditable(true);
        nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        serialColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        priceColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        fileChooser.setInitialDirectory(new File("data/"));

        //initialize a filtered list to the change filter the list using a text box
        nameTextField.lengthProperty().addListener((observable, oldValue, newValue) ->
        {
            if (newValue.intValue() > oldValue.intValue() && nameTextField.getText().length() >= 256)
            {
                nameTextField.setText(nameTextField.getText().substring(0, 256));
            }
        });

        //if the name field is empty disable the add button
        BooleanBinding bb = new BooleanBinding()
        {
            {
                super.bind(nameTextField.textProperty());
            }
            @Override
            protected boolean computeValue()
            {
                return (nameTextField.getText().length()<2);
            }
        };
        addButton.disableProperty().bind(bb);

        FilteredList<inventoryEditor> filteredlist = new FilteredList<>(List.inventoryList, p -> true);
        searchBox.textProperty().addListener(((observable, oldValue, newValue) ->
                filteredlist.setPredicate(event ->
                {
                    if (newValue == null || newValue.isEmpty())
                    {
                        return true;
                    }

                    // allow the task to be found regardless of case
                    String lowerCaseFilter = newValue.toLowerCase();
                    return event.getName().toLowerCase().contains(lowerCaseFilter) || event.getSerialNum().toLowerCase().contains(lowerCaseFilter);
                })));
        SortedList<inventoryEditor> sortedList = new SortedList<>(filteredlist);
        sortedList.comparatorProperty().bind(tableview.comparatorProperty());
        tableview.setItems(sortedList);
    }

    public void buttonAdd()
    {
        String name = nameTextField.getText();
        String serial = serialNumText.getText();
        String price = priceTextField.getText();
        //ensure that user input matches format
        int flag = 0;
        //use a try catch
        try
        {
            //checks
            Double.parseDouble(price);
            if (name.matches("[0-9]+"))
            {
                flag = 1;
                throw new Exception();
            }

            if (!serial.matches("[a-zA-Z](-)[0-9][0-9][0-9](-)[0-9][0-9][0-9](-)[0-9][0-9][0-9]"))
            {
                flag = 2;
                throw new Exception();
            }
            for (inventoryEditor item : tableview.getItems())
            {
                if (item.getSerialNum().equals(serial))
                {
                    flag = 3;
                    throw new Exception();
                }
            }
            inventoryEditor inventory = new inventoryEditor(name, serial, price);
            List.inventoryList.add(inventory);
        }
        catch (NumberFormatException e )
        {
            errorLabel.setText("Enter a valid price");
        }
        catch (Exception e)
        {
            if (flag == 1)
            {
                errorLabel.setText("Enter a valid name");
            }
            else if (flag == 2)
            {
                errorLabel.setText("Enter a valid serial number");
            }
            else if (flag == 3)
            {
                errorLabel.setText("item already exists");
            }
        }
        //clear textfields
        nameTextField.clear();
        serialNumText.clear();
        priceTextField.clear();

    }

    public void deleteItem()
    {
        //delete the item from the observable list
        //since the observable list is linked to the tableview it will update
        List.inventoryList.remove(tableview.getSelectionModel().getSelectedItem());

    }

    public void clearList()
    {
        //clear the entire observable list
        List.inventoryList.clear();
    }

    public void openList(ActionEvent actionEvent) throws IOException
    {
        //use a file chooser to open the file
        OpenListFile open = new OpenListFile();
        open.openFile(fileChooser);
    }


    public void saveList() throws IOException
    {
        //save the list as an HTML, JSON, or TXT
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("TSV", "*.txt"));
        SaveFile save = new SaveFile();
        save.save(fileChooser, tableview);
    }



    public void changeName(TableColumn.CellEditEvent cellEditEvent)
    {
        //create an object to an edit item class
        EditItem edit = new EditItem();
        //access the method to change the name
        edit.editName(tableview, cellEditEvent, errorLabel);
    }


    public void changeSerial(TableColumn.CellEditEvent cellEditEvent)
    {
        EditItem edit = new EditItem();
        //access the method to change the serial number
        edit.editSerial(tableview, cellEditEvent, errorLabel);
    }


    public void changePrice(TableColumn.CellEditEvent cellEditEvent)
    {
        EditItem edit = new EditItem();
        //access the method to change price
        edit.editPrice(tableview, cellEditEvent, errorLabel);
    }

    public void saveListHTML(ActionEvent actionEvent) throws IOException {
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("HTML", "*.html"));
        SaveFileHTML save = new SaveFileHTML();
        save.save(fileChooser, tableview);
    }

    public void saveListJSON(ActionEvent actionEvent)
    {
        //tbh idk
    }

    public void openListHTML(ActionEvent actionEvent) throws IOException
    {
        //this don't work
        OpenListFileHTML open = new OpenListFileHTML();
        open.openFile(fileChooser);
    }
}
