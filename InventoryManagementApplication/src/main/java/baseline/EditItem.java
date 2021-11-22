package baseline;

import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;


public class EditItem
{

    public void editName(TableView<inventoryEditor> tableview, TableColumn.CellEditEvent<inventoryEditor, String> cellEditEvent, Label errorLabel)
    {
        //edit the name of the item
        String name;
        inventoryEditor edit = tableview.getSelectionModel().getSelectedItem();
        name = cellEditEvent.getNewValue();
        try
        {
            if (name.matches("[0-9]+"))
            {
                throw new Exception();
            }
            edit.setName(cellEditEvent.getNewValue());
        }
        catch (Exception e)
        {
            errorLabel.setText("Enter a valid name (name not updated)");
            edit.setName(edit.getName());
        }
    }

    public void editSerial(TableView<inventoryEditor> tableview, TableColumn.CellEditEvent<inventoryEditor, String> cellEditEvent, Label errorLabel)
    {
        //edit the serial number of the item
        inventoryEditor edit = tableview.getSelectionModel().getSelectedItem();
        String serial;
        int flag = 0;
        serial = cellEditEvent.getNewValue();
        try
        {
            if (!serial.matches("[a-zA-Z](-)[0-9][0-9][0-9](-)[0-9][0-9][0-9](-)[0-9][0-9][0-9]"))
            {
                flag = 1;
                throw new Exception();
            }
            for (inventoryEditor item : tableview.getItems())
            {
                if (item.getSerialNum().equals(serial))
                {
                    flag = 2;
                    throw new Exception();
                }
            }
            edit.setSerialNum(serial);
        }
        catch (Exception e)
        {
            if (flag == 1)
            {
                errorLabel.setText("Enter a valid serial number (serial not updated)");
                edit.setSerialNum(edit.getSerialNum());
            }
            if (flag == 2)
            {
                errorLabel.setText("Serial Number already exists (serial not updated)");
                edit.setSerialNum(edit.getSerialNum());
            }
        }
    }

    public void editPrice(TableView<inventoryEditor> tableview, TableColumn.CellEditEvent<inventoryEditor, String> cellEditEvent, Label errorLabel)
    {
        //edit the price of the item
        inventoryEditor edit = tableview.getSelectionModel().getSelectedItem();
        String price;
        price = cellEditEvent.getNewValue();
        try
        {
            Double.parseDouble(price);
            edit.setPrice(price);
        }
        catch (NumberFormatException e)
        {
            errorLabel.setText("Enter a valid price (price not updated)");

        }

    }
}
