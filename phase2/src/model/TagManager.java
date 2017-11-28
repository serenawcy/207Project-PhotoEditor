package model;

import java.io.IOException;

public class TagManager extends SerializeManager<String>{
//
//    /**
//     * A list that contains all opened ImageFile instances
//     */
//    private static ArrayList<String> tagList = new ArrayList<>();

    /**
     * Creates a new empty StudentManager.
     * @throws IOException: throw a IOException
     * @throws ClassNotFoundException: throw a ClassNotFoundException
     */
    public TagManager(String filePath) throws ClassNotFoundException, IOException {
        super(filePath);
//        tagList = new ArrayList<>();
//
//        File file = new File(filePath);
//        if (file.exists()) {
//            readFromFile(filePath);
//        } else {
//            file.createNewFile();
//        }
    }

//    /**
//     * Writes the imageFileList to file at filePath.
//     * @param filePath the file to write the records to
//     * @throws ClassNotFoundException: throw a ClassNotFoundException
//     */
//    private void readFromFile(String filePath) throws ClassNotFoundException {
//        try {
//            InputStream file = new FileInputStream(filePath);
//            InputStream buffer = new BufferedInputStream(file);
//            ObjectInput input = new ObjectInputStream(buffer);
//            //deserialize the list
//            tagList = (ArrayList<String>)input.readObject();
//            input.close();
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        }
//    }

//    /**
//     * Writes the imageFileList to file at filePath.
//     * @param filePath the file to write the records to
//     * @throws IOException: throw a IOException
//     */
//    private static void writeToFile(String filePath) throws IOException{
//        try {
//            OutputStream file = new FileOutputStream(filePath);
//            OutputStream buffer = new BufferedOutputStream(file);
//            ObjectOutput output = new ObjectOutputStream(buffer);
//            output.writeObject(tagList);
//            output.close();
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        }
//    }

    /**
     * Adds ImageFile to this ImageFileManager.
     * @param newTag a new ImageFile to be added.
     */
    public void add(String newTag, String path) throws IOException {
        String[] tagToAdd = newTag.split(",");

        for (String tag : tagToAdd) {
            if (!managerList.contains(tag.trim())) {
                managerList.add(tag.trim());
                writeToFile(path);
            }
        }
    }

//    /**
//     * Get imageFileList from this ImageFileManager.
//     */
//    public static ArrayList<String> getTagList() {
//        return tagList;
//    }
//
//    public static void delete(String oldTag) throws IOException {
//        tagList.remove(oldTag);
//        writeToFile("./serializedTagFiles.ser");
//    }
}