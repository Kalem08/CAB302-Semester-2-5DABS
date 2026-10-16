package app.components;

//MARK: Imports
// import javafx.scene.layout.*;
// import javafx.scene.control.*;
// import javafx.geometry.Insets;
// import javafx.geometry.Pos;
// import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
// import javafx.scene.effect.*; //for image colouring

import app.Design;
import app.Navigation;

//MARK: Class
/**
 * A sub-class of CustomButton that includes an image instead of text.
 */
public class ImageButton extends CustomButton
{
    Circle circle;
    ImageView imageView;

    /**
     * Creates an ImageButton element.
     * @param imageName The name of the image
     * @param imageWidth The target fit-width of the image
     * @param width The width of the button
     * @param height The height of the button
     */
    public ImageButton(String imageName, int imageWidth, int width, int height)
    {
        //must call the parent class constructor first
        super("", width, height); //no text
        
        // int diameter = cornerRadius;

        // int radius = diameter / 2;
        // super.background.setArcWidth(cornerRadius);
        // super.background.setArcHeight(cornerRadius);
        //'super' means the inherited parent object. Meaning you can directly access variables that this sub-class inherits 

        //leaf
        Image image = Design.getImage(imageName);
        imageView = new ImageView(image);
        imageView.setFitWidth(imageWidth);
        imageView.setPreserveRatio(true);

        //apply colour (if a mask)
        // ColorInput colour = new ColorInput(
        //     0, 0,
        //     image.getWidth(),
        //     image.getHeight(),
        //     Color.web("#FFFFFF")
        // );
        // Blend blend = new Blend(
        //     BlendMode.SRC_ATOP,
        //     null,
        //     colour
        // );
        // imageView.setEffect(blend);

        getChildren().addAll(
            imageView
        );

        setOnMouseClicked(event -> Navigation.openAccountPage()); //this has to be removed, as not all ImageButtons point towards the account page
    }

    /**
     * Sets the new fit-width of them image sub-element.
     * @param width The new width of the image
     */
    public void setImageWidth(int width)
    {
        imageView.setFitWidth(width);
    }
}
