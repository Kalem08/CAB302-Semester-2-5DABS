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
    int buttonWidth = 150;
    int buttonHeight = 40;

    //constructor
    public EditPage(Plant plant)
    {
        Header header = new Header();
        Label testLabel = new Label("Text");
        Label label2 = new Label("Text2");

        Region bottomSpacer = new Region();
        VBox.setVgrow(bottomSpacer, Priority.ALWAYS);

        //MARK: Bottom buttons
        CustomButton backButton = new CustomButton("Quit without saving", buttonWidth, buttonHeight);
        backButton.setTextColour(Design.white);
        backButton.setTextHoverColour(Design.grey90);
        backButton.setBackgroundColor(Design.red);
        backButton.setBackgroundHoverColor(Design.darkRed);
        backButton.setOnMouseClicked(event -> Navigation.openPlantPage(plant));

        CustomButton saveButton = new CustomButton("Submit changes", buttonWidth, buttonHeight);
        saveButton.setTextColour(Design.white);
        saveButton.setTextHoverColour(Design.grey80);
        saveButton.setBackgroundColor(Design.softGreen);
        saveButton.setBackgroundHoverColor(Design.darkSoftGreen);
        saveButton.setOnMouseClicked(event -> saveEdits());

        HBox bottomRow = new HBox(
            backButton,
            saveButton
        );
        bottomRow.setSpacing(Design.spacing);

        VBox content = new VBox(
            testLabel,
            label2,
            bottomSpacer,
            bottomRow
        );
        content.setPadding(new Insets(Design.padding));
        content.setFillWidth(false); //stop nodes in this container from stetching to their maximum horizontally
        VBox.setVgrow(content, Priority.ALWAYS);

        getChildren().addAll(
            header,
            // testLabel
            content
        );
    }

    void saveEdits()
    {

    }
}