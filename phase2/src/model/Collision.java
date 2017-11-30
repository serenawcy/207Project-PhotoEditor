package model;

import java.util.ArrayList;

/**
 * This class deals with the potential file collision
 */
public class Collision {

    /**
     *
     * @param nameBeforeChange the String of name before the changes
     * @param tagsToAdd the ArrayList of String of tags which wanted to be added
     * @return the String of the current name
     */
    public String changeNameAdd(String nameBeforeChange, String tagsToAdd) {
        StringBuilder currentName = new StringBuilder();

        if (tagsToAdd.contains(",")) {
            String[] tagsAdd = tagsToAdd.split(",");

            for (String tags: tagsAdd) {
                currentName.append(" @").append(tags.trim());
            }
        } else {
            currentName.append(" @").append(tagsToAdd.trim());
        }
        return currentName.toString();
    }

    public String changeNameDelete(String nameBeforeChange, String tagToDelete) {
        return nameBeforeChange.replace(" @" + tagToDelete, "");
    }

    public String selectName(String name, String getSuffix) {
        Integer suffixLocation = getSuffix.lastIndexOf(".");
        String suffix = getSuffix.substring(suffixLocation, getSuffix.length());

        return name + suffix;
    }
}
