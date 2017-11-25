package model;


import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

import java.util.ArrayList;

public class ImageFileManager {

    /**
     * A list that contains all opened ImageFile instances
     */
    private static ArrayList<ImageFile> imageFileList = new ArrayList<>();

    /**
     * Creates a new empty StudentManager.
     * @throws IOException: throw a IOException
     * @throws ClassNotFoundException: throw a ClassNotFoundException
     */
    public ImageFileManager(String filePath) throws ClassNotFoundException, IOException {
        imageFileList = new ArrayList<>();

        File file = new File(filePath);
        if (file.exists()) {
            readFromFile(filePath);
        } else {
            file.createNewFile();
        }
    }

    /**
     * Writes the imageFileList to file at filePath.
     * @param filePath the file to write the records to
     * @throws ClassNotFoundException: throw a ClassNotFoundException
     */
    private void readFromFile(String filePath) throws ClassNotFoundException {
        try {
            InputStream file = new FileInputStream(filePath);
            InputStream buffer = new BufferedInputStream(file);
            ObjectInput input = new ObjectInputStream(buffer);

            //deserialize the list
            imageFileList = (ArrayList<ImageFile>)input.readObject();
            input.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Writes the imageFileList to file at filePath.
     * @param filePath the file to write the records to
     * @throws IOException: throw a IOException
     */
    private static void writeToFile(String filePath) throws IOException{
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
    public static void add(ImageFile newImageFile) throws IOException {
        imageFileList.add(newImageFile);
        writeToFile("./serializedImageFiles.ser");
    }

    /**
     * Get imageFileList from this ImageFileManager.
     */
    public static ArrayList<ImageFile> getImageFileList() {
        return imageFileList;
    }

    static void delete(ImageFile oldImageFile) throws IOException {
        imageFileList.remove(oldImageFile);
        writeToFile("./serializedImageFiles.ser");
    }
}