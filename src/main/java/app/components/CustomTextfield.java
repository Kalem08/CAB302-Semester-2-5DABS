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

/**
 * A custom textfield element.
 */
public class CustomTextfield extends StackPane
{
    public Rectangle background;
    public TextField textfield;

    int width;
    int height;
    String placeholderString = "";

    //MARK: Constructors
    //textfield constructor
    /**
     * Creates a custom textfield.
     * @param placeholder The placeholder text
     * @param w The width of the textfield
     * @param h The height of the textfields
     */
    public CustomTextfield(String placeholder, int w, int h)
    {
        this(placeholder, w, h, false);
    }

    //password constructor
    /**
     * Creates a custom textfield.
     * @param placeholder The placeholder text
     * @param w The width of the textfield
     * @param h The height of the textfields
     * @param usePasswordField If true, uses a password field with hidden characters instead of a regular textfield
     */
    public CustomTextfield(String placeholder, int w, int h, boolean usePasswordField) //overrides original constructor if the boolean is present
    {
        width = w;
        height = h;
        placeholderString = placeholder;

        createRectangle();
        
        if (usePasswordField)
            textfield = new PasswordField();
        else
            textfield = new TextField();
        formatTextfield();

        getChildren().addAll(background, textfield);
    }

    //MARK: Rectangle
    /**
     * Creates a rectangle with rounded corners, and a light grey default colour.
     */
    void createRectangle()
    {
        background = new Rectangle(width, height);
        background.setFill(Color.LIGHTGREY);
        background.setArcWidth(height);
        background.setArcHeight(height);
    }

    //MARK: Background colour
    /**
     * Sets the background colour of the textfield element.
     * @param colour The colour for the background
     */
    public void setBackgroundColor(Color colour)
    {
        background.setFill(colour);
    }

    //MARK: Get text
    /**
     * Gets the value from the internal textfield variable.
     * @return String value from the textfield element
     */
    public String getText()
    {
        return textfield.getText();
    }
    
    /**
     * Clears the textfield element.
     */
    public void clear()
    {
        textfield.clear();
    }

    /**
     * Sets the font, colour, placeholder string and padding of the textfield element.
     */
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
