package baseline;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class OpenListFile
{


    public void openFile(FileChooser fileChooser) throws IOException
    {
        //open the file chooser window
        File file = fileChooser.showOpenDialog(new Stage());
        if (file != null)
        {
            openData(file);
        }
    }

    private void openData(File file)
    {
        List.inventoryList.clear();

        //read each line and copy to tableview
        try(BufferedReader reader = new BufferedReader(new FileReader(file)))
        {
            String line;
            int iteration = 0;
            while ((line = reader.readLine()) != null)
            {
                if (iteration == 0)
                {
                    iteration++;
                    continue;
                }
                String [] info = line.split("\t");
                inventoryEditor inventoryeditor = new inventoryEditor(info[1], info[0], info[2]);
                List.inventoryList.add(inventoryeditor);
            }


        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
