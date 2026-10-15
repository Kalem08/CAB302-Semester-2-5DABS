package app.components;

//MARK: Imports
import javafx.scene.paint.Color;

import app.Navigation;

/**
 * A button for opening either the log in page or register page.
 */
public class AuthButton extends CustomButton
{
    /**
     * Creates a button for opening either the log in page or register page.
     * @param text The text on the button
     * @param width The width of the button
     * @param height The height of the button
     * @param defaultColour The background colour
     * @param hoverColour The background colour when the mouse is hovering over the button
     * @param registerMode When true, the button will open the register page. Otherwise, will open the log in page
     */
    public AuthButton(String text, int width, int height, Color defaultColour, Color hoverColour, Boolean registerMode)
    {
        //call the parent class constructor
        super(text, width, height);

        setBackgroundColor(defaultColour);
        setBackgroundHoverColor(hoverColour);

        //on-click event
        if (registerMode)
            setOnMouseClicked(event -> Navigation.openRegisterPage());
        else
            setOnMouseClicked(event -> Navigation.openLogInPage());
    }
}
