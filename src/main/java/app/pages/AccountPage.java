package app.pages;

//MARK: imports
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import app.Design;
import app.HelloApplication;
import app.components.*;
import User.*;

public class AccountPage extends VBox
{
    int textfieldWidth = 200;
    int textfieldHeight = 30;
    int buttonWidth = 120;

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
        CustomButton saveButton = new CustomButton("Save changes", buttonWidth, textfieldHeight);
        // saveButton.setPrefHeight(30);
        saveButton.setBackgroundColor(Design.grey80);
        saveButton.setBackgroundHoverColor(Design.grey70);
        saveButton.setOnMouseClicked(event -> saveAccountDetails());

        CustomButton logoutButton = new CustomButton("Log out", buttonWidth, textfieldHeight);
        logoutButton.setTextColour(Design.white);
        logoutButton.setTextHoverColour(Design.white);
        logoutButton.setBackgroundColor(Color.RED);
        logoutButton.setBackgroundHoverColor(Design.darkRed);
        logoutButton.setOnMouseClicked(event -> logout());

        // Region horizontalSpacer = new Region();
        // HBox.setHgrow(horizontalSpacer, Priority.ALWAYS);

        VBox verticalStack = new VBox(
            titleLabel,
            displayNameRow,
            usernameRow,
            passwordRow,
            saveButton,
            logoutButton
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
        HelloApplication.print("saveAccountDetails is not implemented yet.");
    }

    //sign out and load new page
    void logout()
    {
        authManager.signOut();
        HelloApplication.setPage(new EntryPage());
    }
    
    // void openAccountSettingsPage()
    // {
    //     HelloApplication.scene.setRoot(new AccountSettingsPage());
    // }
}
