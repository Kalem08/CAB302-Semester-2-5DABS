package app.components;

//MARK: Imports
import javafx.scene.control.*;
import javafx.scene.layout.*;
// import javafx.geometry.Insets;
// import javafx.geometry.Pos;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
// import javafx.scene.text.FontWeight;

import app.Design;

/**
 * A custom button element that includes a background. Has methods for changing the colour when the mouse is hovering over.
 */
public class CustomButton extends StackPane
{
    public HoverState shouldUnderlineLabel = HoverState.never;

    Color frameColour = Color.LIGHTGREY;
    Color frameHoverColour = Color.GREY;
    Color textColour = Color.BLACK;
    Color textHoverColor = Color.BLACK;

    public Label label;
    public Rectangle background;

    /**
     * Creates a custom button with a background.
     * @param text The text on the button
     * @param width The width of the button/background
     * @param height The height of the button/background
     */
    public CustomButton(String text, int width, int height)
    {
        //a red frame to show the size of the button's true area
        Rectangle frame = new Rectangle();
        frame.setFill(Color.RED);
        frame.widthProperty().bind(this.widthProperty());
        frame.heightProperty().bind(this.heightProperty());

        //MARK: Background
        // background = new Rectangle(width, height);
        background = new Rectangle();
        background.setFill(frameColour);
        setCornerRadius(Design.cornerRadius);
        background.widthProperty().bind(this.widthProperty());
        background.heightProperty().bind(this.heightProperty());

        label = new Label(text);
        label.setTextFill(textColour);
        label.setFont(Font.font(16));
        // label.setFont(Font.font("Arial", 24));
        // label.setFont(Font.font("Calibri", 50));
        // label.setFont(Font.font("Arial", FontWeight.BOLD, 16));

        updateHoverColours();

        getChildren().addAll(
            // frame,
            background,
            label
        );
        setMinWidth(width);
        setPrefWidth(width);
        setPrefHeight(height);
    }

    //MARK: Set corner radius
    /**
     * Sets both the arc-width and arc-height of the button's corners.
     * @param radius The radius of the corners (in pixels)
     */
    public void setCornerRadius(int radius)
    {
        background.setArcWidth(radius);
        background.setArcHeight(radius);
    }

    //MARK: Text properties
    /**
     * Sets the text of the button.
     * @param text The text on the button
     */
    public void setText(String text)
    {
        label.setText(text);
    }

    /**
     * Sets the text size of the button.
     * @param size The text size of the button
     */
    public void setTextSize(int size)
    {
        label.setFont(Font.font(size));
    }

    /**
     * Sets the default colour of the text when the cursor is not over the button.
     * @param colourParameter The text colour 
     */
    public void setTextColour(Color colourParameter)
    {
        textColour = colourParameter;
        updateHoverColours();
        label.setTextFill(colourParameter);
    }

    /**
     * Sets the colour of the text when the cursor is hovering over the button.
     * @param colourParameter The text colour
     */
    public void setTextHoverColour(Color colourParameter)
    {
        textHoverColor = colourParameter;
        updateHoverColours();
    }

    //MARK: Background properties
    /**
     * Sets the default colour of the background when the cursor is not over the button.
     * @param colourParameter The background colour 
     */
    public void setBackgroundColor(Color colourParameter)
    {
        frameColour = colourParameter;
        updateHoverColours();
        background.setFill(colourParameter);
    }

    /**
     * Sets the colour of the background when the cursor is hovering over the button.
     * @param colourParameter The background colour 
     */
    public void setBackgroundHoverColor(Color colourParameter)
    {
        frameHoverColour = colourParameter;
        updateHoverColours();
    }

    /**
     * Sets whether the background of the button is visible.
     * @param state When true, the background is visible. 
     */
    public void setBackgroundVisibility(Boolean state)
    {
        background.setVisible(state);
        //might need 'background.setManaged(state);' here
    }

    /**
     * Update the methods that are called when the mouse enters/exits the custom button.
     */
    void updateHoverColours()
    {
        //mouse over
        setOnMouseEntered(event -> {
            label.setTextFill(textHoverColor);
            background.setFill(frameHoverColour);
            label.setUnderline(shouldUnderlineLabel != HoverState.never);
        });
        
        //mouse exit
        setOnMouseExited(event -> {
            label.setTextFill(textColour);
            background.setFill(frameColour);
            label.setUnderline(shouldUnderlineLabel == HoverState.always);
        });
    }

    //MARK: Enum
    /**
     * HoverState describes the conditions under which graphical details should be present. An example is text should only be underlined when it is set to 'onHover'.
     */
    public enum HoverState
    {
        never,
        always,
        onHover
    }
}
