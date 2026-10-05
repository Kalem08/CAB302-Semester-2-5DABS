package app.components;

//MARK: Imports
import app.Design;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.*;
// import javafx.geometry.Insets;
// import javafx.geometry.Pos;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
// import javafx.scene.text.FontWeight;
import javafx.scene.text.FontWeight;

//this node is added to the EditPage for plants, and  contains a label and field for each property of a plant
public class PlantEditField extends StackPane
{
    //variables
    Color backgroundColour = Color.LIGHTGREY;
    public static float defaultHeight = 60;

    //MARK: Constructors
    public PlantEditField(String title, String defaultText)
    {
        this(title, defaultText, defaultHeight, false);
    }

    public PlantEditField(String title, String defaultText, boolean multilined)
    {
        this(title, defaultText, defaultHeight, multilined);
    }

    public PlantEditField(String title, String defaultText, float height, boolean multilined)
    {
        //MARK: Background
        // background = new Rectangle(width, height);
        Rectangle background = new Rectangle();
        background.setFill(backgroundColour);
        background.setArcWidth(Design.cornerRadius);
        background.setArcHeight(Design.cornerRadius);
        background.widthProperty().bind(this.widthProperty());
        background.heightProperty().bind(this.heightProperty());

        //MARK: Body
        Label label = new Label(title);
        // label.setTextFill(textColour);
        label.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        
        //use either a TextField or TextArea
        TextInputControl field;
        if (multilined)
        {
            field = new TextArea(defaultText);
            field.setPrefWidth(800);

            if (field instanceof TextArea textArea) {
                textArea.setWrapText(true);
            }
        }
        else
        {
            field = new TextField(defaultText);
            field.setPrefWidth(400);
        }
        // field.setPrefWidth(400);
        
        VBox body = new VBox(
            label,
            field
        );
        body.setPadding(new Insets(Design.padding));
        body.setFillWidth(false);

        getChildren().addAll(
            background,
            body
        );

        // setPrefWidth(1000);
        setPrefHeight(height);
    }
}