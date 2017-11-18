package model;


//import java.io.IOException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class ImageFileManager {
    private static ArrayList<ImageFile> imageFileList = new ArrayList<ImageFile>();

    public ImageFileManager(){
    }

    public ImageFile checkExist(File newFile) throws IOException {
        //if the image exists according to its absoluteAddress, return this image
        for (ImageFile checkImageFile: imageFileList) {
            if (checkImageFile.getFile().getAbsolutePath().equals(newFile.getAbsolutePath())) {
                return checkImageFile;
            }
        }
        // if not, return a new ImageFile
        ImageFile newImageFile = new ImageFile(newFile);
        imageFileList.add(newImageFile);
        return newImageFile;
    }
}