package model;

import javafx.scene.image.Image;

import java.io.*;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ImageFile {
    private String nameWithoutSuffix;
    private String suffix;
    private String nameWithSuffix;

    private String absoluteAddress;
    private String originalName;
    private File file;
    private Image image;


    private TagManager tagStore;

    private static final Logger logger = Logger.getLogger(ImageFile.class.getName());

    /**
     * Create a new empty ImageFile.
     * @param name the name of this ImageFile object
     * @param absoluteAddress the absolute address of this ImageFile object
     */
    public ImageFile(String name, String absoluteAddress) throws IOException {
        String[] separate = name.split("\\.(?=[^.]+$)");

        this.nameWithSuffix = name;
        this.nameWithoutSuffix = separate[0];
        this.suffix = separate[1];

        if (!this.nameWithoutSuffix.contains("@")) {
            this.originalName = this.nameWithoutSuffix;
        } else {
            Integer target = name.indexOf("@");
            this.originalName = this.nameWithoutSuffix.substring(0, target - 1);
        }

        this.absoluteAddress = absoluteAddress;
        this.tagStore = new TagManager(this.toString());

        FileHandler fileHandler = new FileHandler("./logHistory.txt");
        fileHandler.setLevel(Level.FINE);
        fileHandler.setFormatter(new java.util.logging.SimpleFormatter());

        logger.setLevel(Level.ALL);
        logger.addHandler(fileHandler);

        this.file = new File(absoluteAddress);
        this.image = new Image(file.toURI().toString());
    }

    /**
     * Add user input tag name if it does not exist and rename this ImageFile.
     * @param userInputAdd the tag name which is supposed to be added to this ImageFile name
     */
    public void addTag(String userInputAdd) throws Exception {
        String[]  nameToAdd = userInputAdd.split(",");
        for (String name: nameToAdd) {
            if (!this.tagStore.checkExist(name)) {
                this.tagStore.addTag(name);
                this.renameAdd(name);
            }
        }

    }

    /**
     * Delete user input tag name and rename this ImageFile.
     * @param tagToDelete the tag name which is supposed to be deleted from this ImageFile name
     */
    public void deleteTag(String tagToDelete) throws Exception {
        this.tagStore.deleteTag(tagToDelete);
        this.renameDelete(tagToDelete);
    }

    /**
     * Change the absolute of this ImageFile object and reset the image of this ImageFile object.
     * @param newDirectory new absolute directory of this ImageFile object
     */
    public void changeDirectory(String newDirectory) throws Exception{
        // newDirectory is a parent address
        File dir = new File(newDirectory);
        File fileNewPath = new File(dir, nameWithSuffix);
        boolean renamed = file.renameTo(fileNewPath);
        if (renamed) {
            this.absoluteAddress = fileNewPath.getAbsolutePath();
        }
        this.setImage(fileNewPath);
//        File newFile = new File(newDirectory);
//        boolean rename = file.renameTo(newFile);
//        if (rename) {
//            this.absoluteAddress = newDirectory;
//        }
//        this.setImage(newFile);
    }

    /**
     * Change the name of this ImageFile object.
     * @param newImageName new name of this ImageFile object
     */
    public void changeImageName(String newImageName) throws Exception {
        logger.log(Level.FINE, "Renamed this image file from " + this.nameWithSuffix + " to " + newImageName + this.suffix);
        String newDirectory = this.absoluteAddress.replace(this.nameWithoutSuffix, newImageName);
        this.nameWithoutSuffix = newImageName;
        this.changeDirectory(newDirectory);
    }

    /**
     * Rename this ImageFile by adding a tag.
     * @param tagToAdd the tag name need to add
     */
    public void renameAdd(String tagToAdd) throws Exception {
        this.changeImageName(this.nameWithoutSuffix + " @" + tagToAdd);
    }

    /**
     * Rename this ImageFile by deleting a tag.
     * @param tagToDelete the tag name need to delete
     */
    public void renameDelete(String tagToDelete) throws Exception {
        this.changeImageName(this.nameWithoutSuffix.replace("@" + tagToDelete, ""));
    }

    /**
     * Get this ImageFile object's tagStore.
     * @return a TagManager object
     */
    public TagManager getTagManager() {
        return this.tagStore;
    }

    /**
     * Get the log history of this ImageFile object.
     * @return the String of log history
     */
    public static String getLog() throws FileNotFoundException {

        Scanner scanner = new Scanner(new FileInputStream("./logHistory.txt"));
        String logHistory = "";

        while (scanner.hasNextLine()) {
            logHistory = logHistory.concat(scanner.nextLine().concat("\n"));
        }
        scanner.close();
        return logHistory;
    }

    /**
     * Get this ImageFile object's original name.
     * @return the original name of this ImageFile object
     */
    public String getOriginalName() {
        return originalName;
    }

    /**
     * Get this ImageFile object's name without suffix.
     * @return the name of this ImageFile object
     */
    public String getNameWithoutSuffix() {
        return nameWithoutSuffix;
    }

    /**
     * Set this ImageFile object's name without suffix.
     * @param name the new name of this ImageFile object
     */
    public void setNameWithoutSuffix(String name) {
        this.nameWithoutSuffix = name;
    }

    /**
     * Get this ImageFile object's absoluteAddress.
     * @return the absolute address of this ImageFile object
     */
    public String getAbsoluteAddress() {
        return absoluteAddress;
    }

    /**
     * Set this ImageFile object's absoluteAddress.
     * @param absoluteAddress the new absolute address of this ImageFile object
     */
    public void setAbsoluteAddress(String absoluteAddress) {
        this.absoluteAddress = absoluteAddress;
    }

    /**
     * Get this ImageFile object's image
     * @return the image of this ImageFile object
     */
    public Image getImage() {
        return image;
    }

    /**
     *  Set this ImageFile object's image
     * @param newFile the new File to be the image of this ImageFile object
     */
    public void setImage(File newFile) {
        this.image = new Image(newFile.toURI().toString());
    }
}
