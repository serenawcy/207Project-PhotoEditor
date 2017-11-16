package model;


//import java.io.IOException;
import java.io.IOException;
import java.util.ArrayList;

public class ImageFileManager {
    private ArrayList<ImageFile> imageList;

    public ImageFileManager(){
        this.imageList = new ArrayList<ImageFile>();
    }

    public ImageFile checkExist(String name, String absoluteAddress) throws IOException {
        //if the image exists according to its absoluteAddress, return this image
        for (ImageFile checkImage: this.imageList) {
            if (checkImage.getAbsoluteAddress().equals(absoluteAddress)) {
                return checkImage;
            }
        }
        ImageFile newImageFile = new ImageFile(name, absoluteAddress);
        this.imageList.add(newImageFile);
        return newImageFile;
    }
}
