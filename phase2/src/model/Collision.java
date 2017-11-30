package model;

import java.util.ArrayList;

public class Collision {

    public String changeNameAdd(String nameBeforeChange, ArrayList<String> tagsToAdd) {
        StringBuilder currentName = new StringBuilder();
        for (String tags: tagsToAdd) {
            currentName.append(" @").append(tags);
        }
        return currentName.toString();
    }

    public String changeNameDelete(String nameBeforeChange, String tagToDelete) {

        return nameBeforeChange.replace(" @" + tagToDelete, "");

    }
}
