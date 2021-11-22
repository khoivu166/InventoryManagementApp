package baseline;

import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class SaveFileHTML
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
            writer.write ("<table>");
            writer.newLine();
            writer.write ("\t" + "<tr>");
            writer.newLine();
            writer.write("\t\t" + "<th>Serial</th> <th>Name</th> <th>Value</th>");
            writer.newLine();

            for (inventoryEditor item : observableList)
            {
                writer.write ("\t" + "</tr>");
                writer.newLine();
                writer.write("\t" + "<td> " + item.getSerialNum() + " </td>");
                writer.write("<td> " + item.getName() + " </td>");
                writer.write("<td> " + item.getPrice() + " </td>");
                writer.newLine();
                writer.write ("\t" + "</tr>");
                writer.newLine();
            }
            writer.write("</table>");
        }
    }
}
