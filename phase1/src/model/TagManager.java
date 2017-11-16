package model;
import java.io.*;
import java.util.*;

public class TagManager implements Serializable {

    protected ArrayList<String> allTags;
    protected String filePath;


    public TagManager(String imageID) {
        this.allTags = new ArrayList<String>();
        this.filePath = imageID + ".bin";
//    readTags();
    }


//    public void addTag(String tag) {
//        this.allTags.add(tag);
//        writeTags();
//    }
//
//
//    public void deleteTag(String tag) {
//
//        this.allTags.remove(tag);
//        writeTags();
//    }
//
//    public boolean checkExist(String tagToCheck) {
//        if (allTags.contains(tagToCheck)) {
//            return true;
//        }
//        return false;
//    }
//
//
    public void writeTags() {
        try {
            OutputStream file = new FileOutputStream(filePath);
            OutputStream buffer = new BufferedOutputStream(file);
            ObjectOutput output = new ObjectOutputStream(buffer);
            output.writeObject(allTags);
            output.close();
        } catch (FileNotFoundException e) {
            new File(filePath);
//      writeTags();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @SuppressWarnings("unchecked")
    public ArrayList<String> readTags() {
        try {
            InputStream file = new FileInputStream(filePath);
            InputStream buffer = new BufferedInputStream(file);
            ObjectInput input = new ObjectInputStream(buffer);
            allTags = (ArrayList<String>) input.readObject();
            input.close();
        } catch (FileNotFoundException e) {
            allTags = new ArrayList<String>();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return allTags;
    }
}