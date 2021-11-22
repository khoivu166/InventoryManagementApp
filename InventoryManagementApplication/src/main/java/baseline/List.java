package baseline;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class List
{
    //create an observable list
   protected static ObservableList<inventoryEditor> inventoryList = FXCollections.observableArrayList();

    public ObservableList<inventoryEditor> getInventoryList()
    {
        //return the list
        return inventoryList;
    }

}
