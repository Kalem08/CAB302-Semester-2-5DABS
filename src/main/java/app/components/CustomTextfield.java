package app.components;

//MARK: Imports
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.geometry.Insets;
// import javafx.geometry.Pos;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
// import javafx.scene.textfield;

public class CustomTextfield extends StackPane
{
    Rectangle background;
    TextField textfield;

    int width;
    int height;
    String placeholderString = "";

    //MARK: Constructors
    //textfield constructor
    public CustomTextfield(String placeholder, int w, int h)
    {
        width = w;
        height = h;
        placeholderString = placeholder;

        createRectangle();

        textfield = new TextField();
        formatTextfield();

        getChildren().addAll(background, textfield);
    }

    //password constructor
    public CustomTextfield(String placeholder, int w, int h, boolean usePasswordField) //overrides original constructor if the boolean is present
    {
        width = w;
        height = h;
        placeholderString = placeholder;

        createRectangle();

        textfield = new PasswordField();
        formatTextfield();

        getChildren().addAll(background, textfield);
    }

    //MARK: Background
    void createRectangle()
    {
        background = new Rectangle(width, height);
        background.setFill(Color.LIGHTGREY);
        background.setArcWidth(height);
        background.setArcHeight(height);
    }

    //MARK: Methods
    public void setBackgroundColor(Color colour)
    {
        background.setFill(colour);
    }

    public String getText()
    {
        return textfield.getText();
    }

    public void clear()
    {
        textfield.clear();
    }

    public void usePasswordField()
    {
        textfield = new PasswordField();
        formatTextfield();
    }

    public void formatTextfield()
    {
        textfield.setPromptText(placeholderString);
        textfield.setPadding(new Insets(0, height / 2, 0, height / 2));
        textfield.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 16));
        textfield.setStyle(
            "-fx-background-color: transparent;" +
            "-fx-text-fill: #333333;" +
            "-fx-prompt-text-fill: GREY;"
        );
    }
}
