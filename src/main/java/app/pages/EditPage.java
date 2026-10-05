package app.pages;

//MARK: Imports
import javafx.geometry.Insets;
// import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
// import javafx.scene.paint.Color;
// import javafx.scene.text.Font;
// import javafx.scene.text.FontWeight;

import app.Design;
import app.Navigation;
import app.components.CustomButton;
import app.components.Header;
import app.model.Plant;

//this page allows users to make a request to change details of a Plant entry
public class EditPage extends VBox
{
    //constructor
    // public EditPage()
    // {
    //     return 
    // }

    public EditPage(Plant plant)
    {
        Header header = new Header();
        Label testLabel = new Label("Text");
        Label label2 = new Label("Text2");

        CustomButton backButton = new CustomButton("Quit without saving", 200, 40);
        backButton.setOnMouseClicked(event -> Navigation.openPlantPage(plant));

        VBox content = new VBox(
            testLabel,
            label2,
            backButton
        );
        content.setPadding(new Insets(Design.padding));

        getChildren().addAll(
            header,
            // testLabel
            content
        );
    }
}