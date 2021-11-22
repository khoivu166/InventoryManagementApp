package baseline;

import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class SaveFile
{
    public void save(FileChooser fileChooser, TableView<inventoryEditor> tableview) throws IOException
    {
        //show file chooser window
        File file = fileChooser.showSaveDialog(new Stage());
        if (file != null)
        {
            saveList(file, tableview.getItems());
        }
    }

    public void saveList(File file, ObservableList<inventoryEditor> observableList) throws IOException
    {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(file)))
        {
            writer.write("Serial Number" + "\t" + "Name" + "\t" + "Value");
            writer.newLine();
            for (inventoryEditor item : observableList)
            {
                writer.write (item.getSerialNum() + "\t" + item.getName() + "\t" + item.getPrice());
                writer.newLine();
            }
        }
    }
}
