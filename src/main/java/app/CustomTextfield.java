package app;

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

    public CustomTextfield(String placeholderString, int width, int height)
    {
        //MARK: Background
        background = new Rectangle(width, height);
        background.setFill(Color.LIGHTGREY);
        background.setArcWidth(height);
        background.setArcHeight(height);

        TextField textfield = new TextField();
        textfield.setPromptText(placeholderString);
        textfield.setPadding(new Insets(0, height / 2, 0, height / 2));
        textfield.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 16));
        textfield.setStyle(
            "-fx-background-color: transparent;" +
            "-fx-text-fill: #333333;" +
            "-fx-prompt-text-fill: GREY;"
        );

        getChildren().addAll(background, textfield);
    }

    public void setBackgroundColor(Color colour)
    {
        background.setFill(colour);
    }
}
