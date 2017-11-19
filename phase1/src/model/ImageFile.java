package model;

import javafx.scene.image.Image;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;

public class ImageFile implements Serializable{
    private File file;
    private static Image image;
    private String originalName;
    private ArrayList<String> existTag;
    private ArrayList<String> oldName;

    private ArrayList<String> history;

    /**
     * Create a new empty ImageFile.
     * @param file the File object which used to construct ImageFile
     * @throws IOException throw a IOException
     */
    public ImageFile(File file) throws IOException {

        this.file = file;
        image = new Image(file.toURI().toString());

        if (!this.file.getName().contains("@")) {
            this.originalName = this.getNameWithoutSuffix(this.file);
        } else {
            Integer target = this.file.getName().indexOf("@");
            this.originalName = this.file.getName().substring(0, target - 1);
        }

        this.history = new ArrayList<>();
        this.existTag = new ArrayList<>();
        this.oldName = new ArrayList<>();

        this.oldName.add(this.file.getName());
    }

    /**
     * Get the name of a File object without suffix
     * @param file the File object to get its name without suffix
     * @return the name of the File object without suffix
     */
    private String getNameWithoutSuffix(File file) {
        String[] separate = file.getName().split("\\.(?=[^.]+$)");
        return separate[0];
    }

    /**
     * Get the suffix of a File object
     * @param file the File object to get its suffix
     * @return the suffix of the File object
     */
    private String getSuffix(File file) {
        String[] separate = file.getName().split("\\.(?=[^.]+$)");
        return separate[1];
    }

    /**
     * Add user input tag name if it does not exist and rename this ImageFile.
     * @param userInputAdd the tag name which is supposed to be added to this ImageFile name
     */
    public void addTag(String userInputAdd) throws IOException {
        String[] tagToAdd = userInputAdd.split(",");

        StringBuilder tagAdd = new StringBuilder();

        for (String tag: tagToAdd) {
            this.existTag.add(tag);
            tagAdd = tagAdd.append(tag).append(" @");
        }

        tagAdd.replace(tagAdd.length() - 2, tagAdd.length(), "");
        this.renameAdd(tagAdd.toString());
    }

    /**
     * Delete user input tag name and rename this ImageFile.
     * @param tagToDelete the tag name which is supposed to be deleted from this ImageFile name
     */
    public void deleteTag(String tagToDelete) throws IOException {
        this.existTag.remove(tagToDelete);
        this.renameDelete(tagToDelete);
    }

    /**
     * Change the Directory of this ImageFile object and reset the image of this ImageFile object.
     * @param newParentDirectory new parent directory of this ImageFile object
     */
    public void changeDirectory(String newParentDirectory) throws IOException {
        File dir = new File(newParentDirectory);

        boolean success = file.renameTo(new File(dir, file.getName()));
        this.file = new File(dir, file.getName());
        ImageFileManager.add(this);
        ImageFileManager.writeToFile("./serializedImageFiles.ser");
        if (success) {
            this.setImage(this.file);
        }
    }

    /**
     * Change the name of this ImageFile object.
     * @param newImageName new name of this ImageFile object
     */
    public void changeImageName(String newImageName) throws IOException {

        Date time = new Date();

        history.add(time + "Renamed this image file from " + this.file.getName() +
                " to " + newImageName + "." + this.getSuffix(this.file) + "\n");

        File renameFile = new File(this.file.getAbsolutePath().replace(this.getNameWithoutSuffix(this.file), newImageName));
        boolean success = this.file.renameTo(renameFile);
        this.file = renameFile;

        if (success) {
            this.setImage(this.file);
            if (!this.oldName.contains(this.file.getName())){
                this.oldName.add(this.file.getName());
            }
            ImageFileManager.add(this);
            ImageFileManager.writeToFile("./serializedImageFiles.ser");
        }
    }

    /**
     * Rename this ImageFile by adding a tag.
     * @param tagToAdd the tag name need to be added
     */
    private void renameAdd(String tagToAdd) throws IOException {
        this.changeImageName(this.getNameWithoutSuffix(this.file) + " @" + tagToAdd);
    }

    /**
     * Rename this ImageFile by deleting a tag.
     * @param tagToDelete the tag name need to be deleted
     */
    private void renameDelete(String tagToDelete) throws IOException {
        String suffix = this.getSuffix(this.file);
        this.changeImageName(this.file.getName().replace(" @" + tagToDelete, "").replaceFirst("." + suffix, ""));
    }

    /**
     * Update the existTag.
     * @param tagsToRename the ArrayList of tags to be renamed
     */
    public void changeTagHistory(ArrayList<String> tagsToRename) throws IOException {
        this.existTag = tagsToRename;
        ImageFileManager.add(this);
        ImageFileManager.writeToFile("./serializedImageFiles.ser");
    }

    /**
     * Get the log history of this ImageFile object.
     * @return the String of log history
     */
    public String getLog() {
        StringBuilder allHistory = new StringBuilder();
        for (String his: history)
        {
            allHistory.append(his);
        }
        return allHistory.toString();
    }

    /**
     * Get this ImageFile object's file.
     * @return the file of this ImageFile object
     */
    public File getFile() {
        return file;
    }

    /**
     *  Set this ImageFile object's file.
     * @param file the new File to be the file of this ImageFile object
     */
    public void setFile(File file) {
        this.file = file;
    }

    /**
     * Get this ImageFile object's originalName.
     * @return the originalName of this ImageFile object
     */
    public String getOriginalName() {
        return originalName;
    }

    /**
     * Get this ImageFile object's oldName.
     * @return the oldName of this ImageFile object
     */
    public ArrayList<String> getOldName() {
        return oldName;
    }

    /**
     * Get this ImageFile object's image.
     * @return the image of this ImageFile object
     */
    public static Image getImage() {
        return image;
    }

    /**
     *  Set this ImageFile object's image.
     * @param newFile the new File to be the image of this ImageFile object
     */
    public void setImage(File newFile) {
        image = new Image(newFile.toURI().toString());
    }

    /**
     * Get this ImageFile object's existTag.
     * @return the existTag of this ImageFile object
     */
    public ArrayList<String> getExistTag() {
        return existTag;
    }

    /**
     * Return whether this ImageFile's file has the same absolute path as the other ImageFile's file's.
     * @param imageFile other ImageFile object which compare with this ImageFile object
     * @return whether this ImageFile's file has the same absolute path as the other ImageFile's file's
     */
    public boolean equals(ImageFile imageFile) {
        return (imageFile.getFile().getAbsolutePath()).equals(this.file.getAbsolutePath());
    }
}