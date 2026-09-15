package app;

//MARK: Imports
import javafx.scene.paint.Color;

import java.net.URL;
import javafx.scene.image.*;

//Design class is a set of static variables
public class Design
{
    //MARK: Sizing
    public static int cornerRadius = 20;
    public static int horizontalSpacing = 10;
    public static int verticalSpacing = 10;
    public static int spacing = 10;
    public static int padding = 10;

    //MARK: Colours
    //greys
    public static Color superLightGrey = Color.web("#f0f0f0");
    public static Color superDarkGrey = Color.web("#0f0f0f");
    //greys with a brightness from 0->100
    public static Color grey95 = Color.web("#f2f2f2");
    public static Color grey90 = Color.web("#e6e6e6");
    public static Color grey80 = Color.web("#cccccc");
    public static Color grey70 = Color.web("#bfbfbf");
    public static Color grey30 = Color.web("#4d4d4d");
    public static Color grey20 = Color.web("#333333");
    public static Color grey10 = Color.web("#1a1a1a");

    //greens
    public static Color green = Color.web("#00ff00"); //lime
    public static Color lightGreen = Color.web("#0fff0f");
    public static Color mediumGreen = Color.web("#008000"); //50%
    public static Color darkGreen = Color.web("#007000");

    public static Color hyperlinkColour = Color.web("#0066CC"); 

    public static Image getImage(String localPath)
    {
        URL url = Design.class.getResource("/images/" + localPath);

        if (url != null)
        {
            return new Image(url.toExternalForm());
        }
        else
        {
            System.out.println("Could not find image in resources folder.");
            System.out.println("localPath: " + localPath);
            System.out.println("URL: " + url);
            return null;
        }
    }

    public static Image getLogo()
    {
        return getImage("Logo.png");
    }
}