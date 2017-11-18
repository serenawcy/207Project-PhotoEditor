package model;


import java.io.IOException;
import java.io.File;
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

    /**
     * Writes the imageFileList to file at filePath.
     * @param filePath the file to write the records to
     * @throws IOException
     */
    public void writeToFile(String filePath) throws IOException{
        try {
            OutputStream file = new FileOutputStream(filePath);
            OutputStream buffer = new BufferedOutputStream(file);
            ObjectOutput output = new ObjectOutputStream(buffer);
            output.writeObject(imageFileList);
            output.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Adds ImageFile to this ImageFileManager.
     * @param newImageFile a new ImageFile to be added.
     */
    public void add(ImageFile newImageFile) {
        imageFileList.add(newImageFile);
    }

    


}