package app;

//MARK: imports
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class AccountPage extends VBox
{
    int textfieldWidth = 200;
    int textfieldHeight = 30;

    public AccountPage()
    {
        Header header = new Header();

        Label titleLabel = new Label("Account");
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        titleLabel.setUnderline(true);

        //display name
        Label displayNameLabel = new Label("Display name:");
        CustomTextfield displayNameTextField = new CustomTextfield("Display name...", textfieldWidth, textfieldHeight);
        HBox displayNameRow = new HBox(
            displayNameLabel,
            displayNameTextField
        );
        displayNameRow.setSpacing(Design.spacing);
        displayNameRow.setAlignment(Pos.CENTER_LEFT);

        //username
        Label usernameLabel = new Label("Username:");
        CustomTextfield usernameTextfield = new CustomTextfield("Username...", textfieldWidth, textfieldHeight);
        HBox usernameRow = new HBox(
            usernameLabel,
            usernameTextfield
        );
        usernameRow.setSpacing(Design.spacing);
        usernameRow.setAlignment(Pos.CENTER_LEFT);

        //password
        Label passwordLabel = new Label("Password:");
        CustomTextfield passwordTextField = new CustomTextfield("Password...", textfieldWidth, textfieldHeight);
        HBox passwordRow = new HBox(
            passwordLabel,
            passwordTextField
        );
        passwordRow.setSpacing(Design.spacing);
        passwordRow.setAlignment(Pos.CENTER_LEFT);

        // Button saveButton = new Button("Save changes");
        CustomButton saveButton = new CustomButton("Save changes", 120, textfieldHeight);
        saveButton.setOnMouseClicked(event -> saveAccountDetails());
        saveButton.setPrefHeight(30);

        // Region horizontalSpacer = new Region();
        // HBox.setHgrow(horizontalSpacer, Priority.ALWAYS);

        VBox verticalStack = new VBox(
            titleLabel,
            displayNameRow,
            usernameRow,
            passwordRow,
            saveButton
        );
        verticalStack.setSpacing(Design.spacing);
        verticalStack.setPadding(new Insets(Design.padding));
        verticalStack.setAlignment(Pos.TOP_LEFT);
        // content.setPrefWidth(400);
        // VBox.setVgrow(content, Priority.ALWAYS);
        verticalStack.setFillWidth(false);

        getChildren().addAll(
            header,
            verticalStack
        );
    }

    //MARK: Methods
    void saveAccountDetails()
    {

    }
    
    // void openAccountSettingsPage()
    // {
    //     HelloApplication.scene.setRoot(new AccountSettingsPage());
    // }
}
