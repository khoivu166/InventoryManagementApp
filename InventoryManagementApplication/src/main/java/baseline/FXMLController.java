package baseline;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class FXMLController implements Initializable
{


    public TextField nameTextField;
    public TextField priceTextField;
    public TextField serialNumText;

    @FXML


    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        //initialize variables
        //initialize a filtered list to the change filter the list using a text box
    }

    public void buttonAdd(ActionEvent actionEvent)
    {
        inventoryEditor add = new inventoryEditor(nameTextField.getText(), serialNumText.getText(), priceTextField.getText());
        //set date from observable list
    }


    public void deleteItem(ActionEvent actionEvent)
    {
        //delete the item from the observable list
        //since the observable list is linked to the tableview it will update
    }

    public void clearList(ActionEvent actionEvent)
    {
        //clear the entire observable list
    }


    public void openList(ActionEvent actionEvent)
    {
        //use a file chooser to open the file
        //parse JSON file if the a JSON file is open
        //parse the HTML file if the an HTML file is open
        //read TXT file if txt file is open
    }


    public void saveList(ActionEvent actionEvent)
    {
        //save the list as a HTML, JSON, or TXT
    }

    public void changeName(TableColumn.CellEditEvent cellEditEvent)
    {
        //create an object to an edit item class
        EditItem edit = new EditItem();
        //access the method to change the name
        edit.editName();
    }


    public void changeSerial(TableColumn.CellEditEvent cellEditEvent)
    {
        EditItem edit = new EditItem();
        //access the method to change the serial number
        edit.editSerial();
    }


    public void changePrice(TableColumn.CellEditEvent cellEditEvent)
    {
        EditItem edit = new EditItem();
        //access the method to change price
        edit.editPrice();
    }
}
