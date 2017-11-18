package model;

import javafx.scene.image.Image;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ImageFile {
    private File file;
    private Image image;
    private String originalName;
    private ArrayList<String> existTag;
    private ArrayList<String> oldName;

    private static final Logger logger = Logger.getLogger(ImageFile.class.getName());

    /**
     * Create a new empty ImageFile.
     * @param file the File object which used to construct ImageFile
     * @throws IOException
     */
    public ImageFile(File file) throws IOException {

        this.file = file;
        this.image = new Image(file.toURI().toString());
        this.originalName = this.getNameWithoutSuffix(file);

        this.existTag = new ArrayList<>();
        this.oldName = new ArrayList<>();

        this.oldName.add(this.originalName);

        FileHandler fileHandler = new FileHandler("./logHistory.txt");
        fileHandler.setLevel(Level.FINE);
        fileHandler.setFormatter(new java.util.logging.SimpleFormatter());

        logger.setLevel(Level.ALL);
        logger.addHandler(fileHandler);
    }

//    /**
//     * Get the name of a File object without suffix
//     * @param file the File object to get its name without suffix
//     * @return the name of the File object without suffix
//     */
//    public String getNameWithoutSuffix(File file) {
//        String[] separate = file.getName().split("\\.(?=[^.]+$)");
//        return separate[0];
//    }
//
//    /**
//     * Get the suffix of a File object
//     * @param file the File object to get its suffix
//     * @return the suffix of the File object
//     */
//    public String getSuffix(File file) {
//        String[] separate = file.getName().split("\\.(?=[^.]+$)");
//        return separate[1];
//    }
//
//    /**
//     * Add user input tag name if it does not exist and rename this ImageFile.
//     * @param userInputAdd the tag name which is supposed to be added to this ImageFile name
//     */
//    public void addTag(String userInputAdd) {
//        String[] tagToAdd = userInputAdd.split(",");
//        for (String tag: tagToAdd) {
//            if (!this.existTag.contains(tag)) {
//                this.existTag.add(tag);
//                this.renameAdd(tag);
//            }
//        }
//    }
//
//    /**
//     * Delete user input tag name and rename this ImageFile.
//     * @param tagToDelete the tag name which is supposed to be deleted from this ImageFile name
//     */
//    public void deleteTag(String tagToDelete) {
//        this.existTag.remove(tagToDelete);
//        this.renameDelete(tagToDelete);
//    }
//
//    /**
//     * Change the Directory of this ImageFile object and reset the image of this ImageFile object.
//     * @param newParentDirectory new parent directory of this ImageFile object
//     */
//    public void changeDirectory(String newParentDirectory) {
//        File dir = new File(newParentDirectory);
//
//        boolean success = file.renameTo(new File(dir, file.getName()));
//        if (success) {
//            this.setImage(this.file);
//        }
//    }
//
//    /**
//     * Change the name of this ImageFile object.
//     * @param newImageName new name of this ImageFile object
//     */
//    public void changeImageName(String newImageName) {
//        logger.log(Level.FINE, "Renamed this image file from " + this.file.getName() + " to "
//                + newImageName + "." + this.getSuffix(this.file));
//
//        File renameFile = new File(this.file.getName().replace(this.getNameWithoutSuffix(this.file), newImageName));
//        boolean success = this.file.renameTo(renameFile);
//        if (success) {
//            this.setImage(this.file);
//            this.oldName.add(this.file.getName());
//        }
//    }
//
//    /**
//     * Rename this ImageFile by adding a tag.
//     * @param tagToAdd the tag name need to be added
//     */
//    public void renameAdd(String tagToAdd) {
//        this.changeImageName(this.getNameWithoutSuffix(this.file) + " @" + tagToAdd);
//    }
//
//    /**
//     * Rename this ImageFile by deleting a tag.
//     * @param tagToDelete the tag name need to be deleted
//     */
//    public void renameDelete(String tagToDelete) {
//        this.changeImageName(this.file.getName().replace(" @" + tagToDelete, ""));
//    }
//
//    /**
//     * Get the log history of this ImageFile object.
//     * @return the String of log history
//     */
//    public static String getLog() throws FileNotFoundException {
//
//        Scanner scanner = new Scanner(new FileInputStream("./logHistory.txt"));
//        String logHistory = "";
//
//        while (scanner.hasNextLine()) {
//            logHistory = logHistory.concat(scanner.nextLine().concat("\n"));
//        }
//        scanner.close();
//        return logHistory;
//    }
//
//    /**
//     * Get this ImageFile object's file.
//     * @return the file of this ImageFile object
//     */
//    public File getFile() {
//        return file;
//    }
//
//    /**
//     *  Set this ImageFile object's file.
//     * @param file the new File to be the file of this ImageFile object
//     */
//    public void setFile(File file) {
//        this.file = file;
//    }
//
//    /**
//     * Get this ImageFile object's originalName.
//     * @return the originalName of this ImageFile object
//     */
//    public String getOriginalName() {
//        return originalName;
//    }
//
//    /**
//     * Get this ImageFile object's oldName.
//     * @return the oldName of this ImageFile object
//     */
//    public ArrayList<String> getOldName() {
//        return oldName;
//    }
//
//    /**
//     * Get this ImageFile object's image.
//     * @return the image of this ImageFile object
//     */
//    public Image getImage() {
//        return image;
//    }
//
//    /**
//     *  Set this ImageFile object's image.
//     * @param newFile the new File to be the image of this ImageFile object
//     */
//    public void setImage(File newFile) {
//        this.image = new Image(newFile.toURI().toString());
//    }
//
//    /**
//     * Get this ImageFile object's existTag.
//     * @return the existTag of this ImageFile object
//     */
//    public ArrayList<String> getExistTag() {
//        return existTag;
//    }
//
//    /**
//     * Return whether this ImageFile's file has the same absolute path as the other ImageFile's file's.
//     * @param imageFile other ImageFile object which compare with this ImageFile object
//     * @return whether this ImageFile's file has the same absolute path as the other ImageFile's file's
//     */
//    public boolean equals(ImageFile imageFile) {
//        return Objects.equals(this.file.getAbsolutePath(), imageFile.file.getAbsolutePath());
//    }
}
