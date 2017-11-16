package model;

import java.util.logging.Handler;
import java.util.logging.Logger;

public class Image {
    private String name;
    private String absoluteAddress;

    private TagManager tagStore;

    private static final Logger logger = Logger.getLogger(Image.class.getName());
    private static Handler fileHandler;

    /**
     * Create a new empty Image.
     * @param name the name of this Image object
     * @param absoluteAddress the absolute address of this Image object
     */
    public Image(String name, String absoluteAddress) {
        this.name = name;
        this.absoluteAddress = absoluteAddress;
        this.tagStore = new TagManager();
        // TODO: Log and Handler
    }

    /**
     * Add user input tag name if it does not exist and rename this Image.
     * @param userInputAdd the tag name which is supposed to be added to this Image name
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
     * Delete user input tag name if it exists and rename this Image.
     * @param userInputDelete the tag name which is supposed to be deleted from this Image name
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
     * Change the absolute directory of this Image object.
     * @param newImageName new name of this Image object
     */
    public void changeDirectory(String newImageName) {
        this.absoluteAddress = this.absoluteAddress.replace(this.name, newImageName);
    }

    /**
     * Change the name of this Image object.
     * @param newImageName new name of this Image object
     */
    public void changeImageName(String newImageName) {
        this.name = newImageName;
        this.changeDirectory(newImageName);
        // TODO: Log
    }

    /**
     * Rename this Image by adding a tag.
     * @param tagToAdd the tag name need to add
     */
    public void renameAdd(String tagToAdd) {
        this.changeImageName(this.name + "@" + tagToAdd);
    }

//    /**
//     * Rename this Image by deleting a tag.
//     * @param tagToDelete the tag name need to delete
//     */
//    public void renameDelete(String tagToDelete) {
//        this.changeImageName(this.name.replace("@" + tagToDelete, ""));
//    }

//    /**
//     * Get this Image object's tagStore.
//     * @return a TagManager object
//     */
//    public TagManager getTagManager() {
//        return this.tagStore;
//    }
//
//    /**
//     * Get the log history of this Image object.
//     * @return the String of log history
//     */
//    public String getLog() {
//        TODO: Log
//    }

    /**
     * Get this Image object's name.
     * @return the name of this Image object
     */
    public String getName() {
        return name;
    }

    /**
     * Set this Image object's name.
     * @param name the new name of this Image object
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get this Image object's absoluteAddress.
     * @return the absolute address of this Image object
     */
    public String getAbsoluteAddress() {
        return absoluteAddress;
    }

    /**
     * Set this Image object's absoluteAddress.
     * @param absoluteAddress the new absolute address of this Image object
     */
    public void setAbsoluteAddress(String absoluteAddress) {
        this.absoluteAddress = absoluteAddress;
    }
}
