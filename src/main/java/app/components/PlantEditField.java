package app.components;

//MARK: Imports
import app.Design;
import javafx.geometry.Insets;
// import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 * A label and textfield for each property of a plant, in the edit page of plants. 
 */
public class PlantEditField extends StackPane
{
    //MARK: Variables
    Color backgroundColour = Color.LIGHTGREY;
    public static float defaultHeight = 60;
    private final TextInputControl inputField;

    //MARK: Constructors

    /**
     * Creates an element for one of properties of a plant.
     * @param title The title of the element
     * @param defaultText The inital text in the textfield
     */
    public PlantEditField(String title, String defaultText)
    {
        this(title, defaultText, defaultHeight, false);
    }

    /**
     * Creates an element for one of properties of a plant.
     * @param title The title of the element
     * @param defaultText The inital text in the textfield
     * @param multilined If true, the element will use a TextArea rather than a TextField
     */
    public PlantEditField(String title, String defaultText, boolean multilined)
    {
        this(title, defaultText, defaultHeight, multilined);
    }

    /**
     * Creates an element for one of properties of a plant.
     * @param title The title of the element
     * @param defaultText The inital text in the textfield
     * @param height The height of the element
     * @param multilined If true, the element will use a TextArea rather than a TextField
     */
    public PlantEditField(String title, String defaultText, float height, boolean multilined)
    {
        //MARK: Background
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
        if (multilined)
        {
            inputField = new TextArea(defaultText);
            inputField.setPrefWidth(800);

            if (inputField instanceof TextArea textArea) {
                textArea.setWrapText(true);
            }
        }
        else
        {
            inputField = new TextField(defaultText);
            inputField.setPrefWidth(400);
        }
        // field.setPrefWidth(400);
        
        VBox body = new VBox(
            label,
            inputField
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

    //MARK: Get Text
    /**
     * Gets the value from the internal textfield variable.
     * @return String value from the textfield element
     */
    public String getText()
    {
        return inputField.getText();
    }
}