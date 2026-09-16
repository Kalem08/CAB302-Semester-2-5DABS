package app.components;

import app.Design;
import app.HelloApplication;
import app.pages.AccountPage;
//MARK: Imports
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
// import javafx.scene.layout.*;
// import javafx.geometry.Insets;
// import javafx.geometry.Pos;
// import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
// import javafx.scene.effect.*; //for image colouring
// import javafx.scene.control.*;

//MARK: Class
public class ImageButton extends CustomButton
{
    Circle circle;
    ImageView imageView;

    public ImageButton(String imageName, int imageWidth, int width, int height)
    {
        //must call the parent class constructor first
        super("", width, height); //no text
        
        // int diameter = cornerRadius;

        // int radius = diameter / 2;
        // super.background.setArcWidth(cornerRadius);
        // super.background.setArcHeight(cornerRadius);

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
            // circle,
            imageView
        );

        setOnMouseClicked(event -> openAccountPage());
    }

    //MARK: Methods
    void openAccountPage()
    {
        HelloApplication.scene.setRoot(new AccountPage());
    }

    public void setImageWidth(int width)
    {
        imageView.setFitWidth(width);
    }
}
