package model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ImageFile {
    private String name;
    private String absoluteAddress;

    private TagManager tagStore;

    private static final Logger logger = Logger.getLogger(ImageFile.class.getName());
    private static Handler fileHandler;

    /**
     * Create a new empty ImageFile.
     * @param name the name of this ImageFile object
     * @param absoluteAddress the absolute address of this ImageFile object
     */
    public ImageFile(String name, String absoluteAddress) throws IOException {
        this.name = name;
        this.absoluteAddress = absoluteAddress;
        this.tagStore = new TagManager(this.toString());

        // how to make its name unique
        FileHandler fileHandler = new FileHandler("./logHistory.txt");
        fileHandler.setLevel(Level.FINE);
        fileHandler.setFormatter(new java.util.logging.SimpleFormatter());

        logger.setLevel(Level.ALL);
        logger.addHandler(fileHandler);
    }

    /**
     * Add user input tag name if it does not exist and rename this ImageFile.
     * @param userInputAdd the tag name which is supposed to be added to this ImageFile name
     */
    public void addTag(String userInputAdd) {
        String[]  nameToAdd = userInputAdd.split(",");
        for (String name: nameToAdd) {
            if (!this.tagStore.checkExist(name)) {
                this.tagStore.addTag(name);
                this.renameAdd(name);
            }
        }

    }

    /**
     * Delete user input tag name if it exists and rename this ImageFile.
     * @param userInputDelete the tag name which is supposed to be deleted from this ImageFile name
     */
    public void deleteTag(String userInputDelete) {
        String[]  nameToDelete = userInputDelete.split(",");
        for (String name: nameToDelete) {
            if (!this.tagStore.checkExist(name)) {
                this.tagStore.deleteTag(name);
                this.renameDelete(name);
            }
        }
    }

    /**
     * Change the absolute directory of this ImageFile object.
     * @param newImageName new name of this ImageFile object
     */
    public void changeDirectory(String newImageName) {
        this.absoluteAddress = this.absoluteAddress.replace(this.name, newImageName);
    }

    /**
     * Change the name of this ImageFile object.
     * @param newImageName new name of this ImageFile object
     */
    public void changeImageName(String newImageName) {
        logger.log(Level.FINE, "Renamed this image file from " + this.name + " to " + newImageName);
        this.name = newImageName;
        this.changeDirectory(newImageName);
    }

    /**
     * Rename this ImageFile by adding a tag.
     * @param tagToAdd the tag name need to add
     */
    public void renameAdd(String tagToAdd) {
        this.changeImageName(this.name + "@" + tagToAdd);
    }

    /**
     * Rename this ImageFile by deleting a tag.
     * @param tagToDelete the tag name need to delete
     */
    public void renameDelete(String tagToDelete) {
        this.changeImageName(this.name.replace("@" + tagToDelete, ""));
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
    public String getLog() throws FileNotFoundException {
        Scanner scanner = new Scanner(new FileInputStream("./logHistory.txt"));
        String logHistory = "";

        while (scanner.hasNextLine()) {
            logHistory = logHistory.concat(scanner.nextLine().concat("/n"));
        }
        scanner.close();
        return logHistory;
    }

    /**
     * Get this ImageFile object's name.
     * @return the name of this ImageFile object
     */
    public String getName() {
        return name;
    }

    /**
     * Set this ImageFile object's name.
     * @param name the new name of this ImageFile object
     */
    public void setName(String name) {
        this.name = name;
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
}
