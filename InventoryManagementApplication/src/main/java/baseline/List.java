package baseline;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class List
{
    //create an observable list
    ObservableList<inventoryEditor> inventoryList = FXCollections.observableArrayList();
    //create method to add to observable list
     public void addList (inventoryEditor inventory)
     {
         //add to the list
     }

    public ObservableList<inventoryEditor> getInventoryList()
    {
        //return the list
        return inventoryList;
    }

}
