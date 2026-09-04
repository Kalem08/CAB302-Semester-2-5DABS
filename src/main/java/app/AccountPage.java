package app;

import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class AccountPage extends BorderPane
{
    public AccountPage()
    {
        Header header = new Header();
        Label label1 = new Label("This is the account page");
        Button settingsButton = new Button("Edit account");
        settingsButton.setOnAction(event -> openAccountSettingsPage());

        VBox content = new VBox(
            label1,
            settingsButton
        );
        content.setSpacing(10);
        content.setAlignment(Pos.CENTER);

        setTop(header);
        setCenter(content);
    }

    void openAccountSettingsPage()
    {
        HelloApplication.scene.setRoot(new AccountSettingsPage());
    }
}
