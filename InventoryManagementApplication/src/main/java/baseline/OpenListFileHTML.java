package baseline;

import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class OpenListFileHTML {
    public void openFile(FileChooser fileChooser) throws IOException {
        //open the file chooser window
        File file = fileChooser.showOpenDialog(new Stage());
        if (file != null) {
            openData(file);
        }
    }

    private void openData(File file) {
        List.inventoryList.clear();

        //use a try catch to read file]
        //I CAN'T GET IT TO WORK
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            String text = null;
            int iteration = 0;
            int stop = 3;
            String[] infoArray = new String[3];
            while ((line = reader.readLine()) != null)
            {
                if (iteration <= 3)
                {
                    iteration++;
                    continue;
                }
                Document doc = Jsoup.parse(line);
                text = doc.text();
                for (int i=0; i<text.length(); i++)
                {
                    infoArray = text.split("");
                }

                inventoryEditor inventoryeditor = new inventoryEditor(infoArray[0], infoArray[1], infoArray[2]);
                List.inventoryList.add(inventoryeditor);
           }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
