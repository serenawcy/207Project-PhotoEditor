package model;

import java.io.Serializable;
import java.util.ArrayList;

public class TagManager implements Serializable {
    private ArrayList<String> allTags;
    private String filePath;

    public TagManager(String imageID) {
        this.filePath = imageID;
    }


}
