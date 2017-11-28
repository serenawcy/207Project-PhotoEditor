package model;

import java.io.*;
import java.util.ArrayList;

public abstract class SerializeManager<T> {

    public ArrayList<T> managerList= new ArrayList<>();


    /**
     * Creates a new empty StudentManager.
     * @throws IOException : throw a IOException
     * @throws ClassNotFoundException: throw a ClassNotFoundException
     */
    public SerializeManager(String filePath) throws ClassNotFoundException, IOException {
        managerList = new ArrayList<>();

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
    public void readFromFile(String filePath) throws ClassNotFoundException {
        try {
            InputStream file = new FileInputStream(filePath);
            InputStream buffer = new BufferedInputStream(file);
            ObjectInput input = new ObjectInputStream(buffer);
            //deserialize the list
            managerList = (ArrayList<T>)input.readObject();
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
    public void writeToFile(String filePath) throws IOException{
        try {
            OutputStream file = new FileOutputStream(filePath);
            OutputStream buffer = new BufferedOutputStream(file);
            ObjectOutput output = new ObjectOutputStream(buffer);
            output.writeObject(managerList);
            output.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Adds ImageFile to this ImageFileManager.
     * @param newItem a new ImageFile to be added.
     */
    public abstract void add(T newItem, String path) throws IOException;
//        String[] tagToAdd = newTag.split(",");
//
//        for (String tag : tagToAdd) {
//            if (!managerList.contains(tag.trim())) {
//                managerList.add(tag.trim());
//                writeToFile(path);
//            }
//        }


    /**
     * Get imageFileList from this ImageFileManager.
     */
    public ArrayList<T> getSerializedList() {
        return managerList;
    }

    public void delete(T oldItem, String path) throws IOException {
        managerList.remove(oldItem);
        writeToFile(path);
    }
}

