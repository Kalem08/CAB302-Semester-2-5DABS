package app;

//MARK: Imports
import javafx.scene.paint.Color;
import javafx.scene.image.*;

//Design class is a set of static variables
public class Design
{
    //MARK: Sizing
    public static int cornerRadius = 20;
    public static int horizontalSpacing = 10;
    public static int verticalSpacing = 10;

    //MARK: Colours
    public static Color superLightGrey = Color.web("#f0f0f0");
    public static Color superDarkGrey = Color.web("#0f0f0f");
    public static Color grey10 = Color.web("#1a1a1a");
    public static Color grey20 = Color.web("#333333");
    public static Color grey30 = Color.web("#4d4d4d");
    public static Color grey80 = Color.web("#cccccc");
    public static Color grey90 = Color.web("#e6e6e6");
    public static Color grey95 = Color.web("#f2f2f2");

    public static Image getLogo()
    {
        //when used in a non-static class, begin with 'getClass()' instead of 'Design.class'
        return new Image(Design.class.getResource("/Logo.png").toExternalForm()); //relative to "/resources/"
    }
}