package model;


////import java.io.IOException;
//import java.io.File;
//import java.io.IOException;
import java.io.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.logging.Level;
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

    public void readFromFile(String path) throws ClassNotFoundException {

        try {
            InputStream file = new FileInputStream(path);
            InputStream buffer = new BufferedInputStream(file);
            ObjectInput input = new ObjectInputStream(buffer);

            //deserialize the list
            imageFileList = (ArrayList<ImageFile>) input.readObject();
            input.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


}