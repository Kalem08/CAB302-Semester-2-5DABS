package app.pages;

//MARK: Imports
import app.components.Header;
import app.HelloApplication;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;

//this page is currently unused, as changed can be made from the AccountPage
public class AccountSettingsPage extends BorderPane
{
    public AccountSettingsPage()
    {
        Header header = new Header();
        Label label1 = new Label("This is the account settings page");
        Button saveButton = new Button("Save changes");
        saveButton.setOnAction(event -> saveAccountSettings());
        Button discardButton = new Button("Discard changes");
        discardButton.setOnAction(event -> openAccountPage());

        HBox buttonsBox = new HBox(
            saveButton,
            discardButton
        );
        buttonsBox.setSpacing(10);
        buttonsBox.setAlignment(Pos.CENTER);

        VBox content = new VBox(
            label1,
            buttonsBox
        );
        content.setSpacing(10);
        content.setAlignment(Pos.CENTER);

        setTop(header);
        setCenter(content);
    }

    void saveAccountSettings()
    {
        //add some saving code here

        openAccountPage();
    }

    void openAccountPage()
    {
        HelloApplication.scene.setRoot(new AccountPage());
    }
}
