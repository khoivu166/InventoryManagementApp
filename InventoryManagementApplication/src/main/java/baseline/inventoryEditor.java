package baseline;

import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleStringProperty;

public class inventoryEditor
{
    private SimpleStringProperty name;
    private SimpleStringProperty serialNum;
    private SimpleStringProperty price;

    public inventoryEditor (String name, String serialNum, String price)
    {
        this.name = new SimpleStringProperty(name);
        this.serialNum = new SimpleStringProperty(serialNum);
        this.price = new SimpleStringProperty(price);
    }

    public String getName()
    {
        return name.get();
    }

    public String getSerialNum()
    {
        return serialNum.get();
    }

    public String getPrice()
    {
        return price.get();
    }

    public void setName(String name)
    {
        this.name = new SimpleStringProperty(name);
    }

    public void setSerialNum (String serialNum)
    {
        this.serialNum = new SimpleStringProperty(serialNum);
    }

    public void setPrice (String price)
    {
        this.price = new SimpleStringProperty(price);
    }

}
