package app;

//MARK: Imports
import javafx.scene.control.*;
import javafx.scene.layout.*;
// import javafx.geometry.Insets;
// import javafx.geometry.Pos;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class CustomButton extends StackPane
{
    Color frameColour = Color.LIGHTGREY;
    Color frameHoverColour = Color.GREY;
    Color textColour = Color.BLACK;
    Color textHoverColor = Color.BLACK;

    Label label;
    Rectangle background;

    //default values
    int width = 100;
    int height = 40;

    public CustomButton(String text, int width, int height)
    {
        //MARK: Background
        background = new Rectangle(width, height);
        background.setFill(frameColour);
        background.setArcWidth(Design.cornerRadius);
        background.setArcHeight(Design.cornerRadius);
        // background.widthProperty().bind(this.widthProperty());
        // background.heightProperty().bind(this.heightProperty());

        label = new Label(text);
        label.setTextFill(textColour);
        label.setFont(Font.font(16));
        // label.setFont(Font.font("Arial", 24));
        // label.setFont(Font.font("Calibri", 50));
        // label.setFont(Font.font("Arial", FontWeight.BOLD, 16));

        updateHoverColours();

        getChildren().addAll(background, label);
        setPrefHeight(50);
        setMinWidth(0);

        // setOnMouseClicked(event -> clicked());
    }

    //MARK: Methods
    public void setTextSize(int size)
    {
        label.setFont(Font.font(size));
    }
    
    void clicked()
    {
        HelloApplication.print("Button clicked");
    }

    void setBackgroundColor(Color colourParameter)
    {
        frameColour = colourParameter;
        updateHoverColours();
        background.setFill(colourParameter);
    }
    void setBackgroundHoverColor(Color colourParameter)
    {
        frameHoverColour = colourParameter;
        updateHoverColours();
    }

    void setTextColour(Color colourParameter)
    {
        textColour = colourParameter;
        updateHoverColours();
        label.setTextFill(colourParameter);
    }
    void setTextHoverColour(Color colourParameter)
    {
        textHoverColor = colourParameter;
        updateHoverColours();
    }

    void updateHoverColours()
    {
        //mouse over
        setOnMouseEntered(event -> {
            label.setTextFill(textHoverColor);
            background.setFill(frameHoverColour);
        });
        
        //mouse exit
        setOnMouseExited(event -> {
            label.setTextFill(textColour);
            background.setFill(frameColour);
        });
    }
}
