package view_control;

import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;

public class AddTagScene {

//    private File inputFile;


    public static void display() {
        Stage window = new Stage();
//        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Add tag(s)");
        window.setMinWidth(250);
        Label label = new Label();
        label.setText("Please enter tag(s), separated with \",\"");

        //Create two buttons
        Button done = new Button("Done");
        Button goBack = new Button("Go back");
        //Form a Text area
        TextField tagInput = new TextField();

    // Clicking will addTags and close window
    done.setOnAction(
        e -> {
          String tags = tagInput.getText();
          System.out.println(tags);
          //            File.addTags(tags);
          window.close();
        });
        goBack.setOnAction(e -> {
            window.close();
//            ManipulationManagerScene.setFile(inputFile);
//            ManipulationManagerScene.display();
        });

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20, 20, 20, 20));
        layout.getChildren().addAll(tagInput, goBack, done);
        //Add buttons
//        layout.getChildren().addAll(label, done, goBack);
        layout.setAlignment(Pos.CENTER);
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.show();
//        window.showAndWait();


//    public void setFile(File file){
//        this.inputFile = file;
//    }
    }
}
