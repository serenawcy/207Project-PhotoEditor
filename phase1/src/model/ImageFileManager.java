package model;


////import java.io.IOException;
//import java.io.File;
//import java.io.IOException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.logging.Level;

public class ImageFileManager {
    private static ArrayList<ImageFile> imageFileList = new ArrayList<ImageFile>();

    /**
     * Creates a new empty StudentManager.
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public ImageFileManager(String filePath) throws ClassNotFoundException, IOException {
        imageFileList = new ArrayList<ImageFile>();

        // Reads serializable objects from file.
        // Populates the record list using stored data, if it exists.
        File file = new File(filePath);
        if (file.exists()) {
            readFromFile(filePath);
        } else {
            file.createNewFile();
        }
    }


}