package model;


//import java.io.IOException;
import java.io.IOException;
import java.util.ArrayList;

public class ImageFileManager {
    private static ArrayList<ImageFile> imageList = new ArrayList<ImageFile>();

    public ImageFileManager(){
    }

    public ImageFile checkExist(String name, String absoluteAddress) throws IOException {
        //if the image exists according to its absoluteAddress, return this image
        for (ImageFile checkImage: imageList) {
            if (checkImage.getAbsoluteAddress().equals(absoluteAddress)) {
                return checkImage;
            }
        }
        // if not, return a new ImageFile
        ImageFile newImageFile = new ImageFile(name, absoluteAddress);
        imageList.add(newImageFile);
        return newImageFile;
    }
}
