package view_control;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.ImageFile;

import java.util.ArrayList;

class SelectTagScene {


    /** Initialize an ImageFile */
    private static ImageFile inputFile;

    /** Magic Number 10 */
    private static final int MAGIC10 = 10;

    /** Magic Number 20 */
    private static final int MAGIC20 = 20;

    /** Magic Number 120 */
    private static final int MAGIC120 = 120;

    /** Magic Number 550 */
    private static final int MAGIC550 = 550;

    /** Magic Number 600 */
    private static final int MAGIC600 = 600;

    /** Display the Scene and construct the buttons. */
    static void display(){
        Stage window = new Stage();
        window.setTitle("Select Old Tag(s)");
        window.initModality(Modality.APPLICATION_MODAL);
        Label label = new Label("Please check the tag(s) you want to rename the photo for");
        Button submit = new Button("Rename");
        Button back = new Button("Go back");
        submit.setMinWidth(MAGIC120);
        back.setMinWidth(MAGIC120);
        VBox layout = new VBox(MAGIC10);
        layout.setPadding(new Insets(MAGIC20, MAGIC20, MAGIC20, MAGIC20));
        layout.getChildren().add(label);
        ArrayList<CheckBox> checkBox = new ArrayList<>(); //Type is CheckBox box1 = new CheckBox();
        for (String tag : inputFile.getExistTag()) {
            CheckBox box = new CheckBox(tag);
            checkBox.add(box);
            layout.getChildren().add(box); //Display
        }
        layout.getChildren().add(submit);
        layout.getChildren().add(back);
        submit.setOnAction(e -> handleOptions((checkBox)));
        back.setOnAction(e -> window.close());
        Scene scene = new Scene(layout, MAGIC550, MAGIC600);
        window.setScene(scene);
        window.show();
    }


    private static void  handleOptions(ArrayList<CheckBox> checkBox) {

        StringBuilder currentName = new StringBuilder();
        currentName.append(inputFile.getOriginalName());
        ArrayList<String> deleteTag = new ArrayList<>();

        for (CheckBox box : checkBox) {
            if (box.isSelected()) {
                currentName.append("@").append(box.getText());
            } else {
                deleteTag.add(box.getText());
            }

        }
        try {
            inputFile.changeImageName(currentName.toString());
            for (String a: deleteTag) {
                inputFile.deleteTag(a);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Set the ImageFile
     *
     * @param imageFile the ImageFile
     */
    
    static void setImageFile(ImageFile imageFile) {
        inputFile = imageFile;
    }

}