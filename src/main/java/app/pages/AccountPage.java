package app.pages;

//MARK: Imports
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import app.Design;
import app.HelloApplication;
import app.Navigation;
import app.components.*;
import User.*;

public class AccountPage extends VBox
{
    int textfieldWidth = 200;
    int textfieldHeight = 30;
    int buttonWidth = 120;

    /**
     * Creates an account page to view account details.
     */
    public AccountPage()
    {
        Header header = new Header();

        Label titleLabel = new Label("Account");
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        titleLabel.setUnderline(true);

        //display name
        // Label displayNameLabel = new Label("Display name:");
        // CustomTextfield displayNameTextField = new CustomTextfield("Display name...", textfieldWidth, textfieldHeight);
        // HBox displayNameRow = new HBox(
        //     displayNameLabel,
        //     displayNameTextField
        // );
        // displayNameRow.setSpacing(Design.spacing);
        // displayNameRow.setAlignment(Pos.CENTER_LEFT);

        //username
        Label usernameLabel = new Label("Username: " + authManager.getUsername());
        // CustomTextfield usernameTextfield = new CustomTextfield("Username...", textfieldWidth, textfieldHeight);
        HBox usernameRow = new HBox(
            usernameLabel
            // usernameTextfield
        );
        usernameRow.setSpacing(Design.spacing);
        usernameRow.setAlignment(Pos.CENTER_LEFT);

        //password
        // Label passwordLabel = new Label("Password:");
        // CustomTextfield passwordTextField = new CustomTextfield("Password...", textfieldWidth, textfieldHeight);
        // HBox passwordRow = new HBox(
        //     passwordLabel,
        //     passwordTextField
        // );
        // passwordRow.setSpacing(Design.spacing);
        // passwordRow.setAlignment(Pos.CENTER_LEFT);

        // CustomButton saveButton = new CustomButton("Save changes", buttonWidth, textfieldHeight);
        // saveButton.setBackgroundColor(Design.grey80);
        // saveButton.setBackgroundHoverColor(Design.grey70);
        // saveButton.setOnMouseClicked(event -> saveAccountDetails());

        //logout button
        CustomButton logoutButton = new CustomButton("Log out", buttonWidth, textfieldHeight);
        logoutButton.setTextColour(Design.white);
        logoutButton.setTextHoverColour(Design.white);
        logoutButton.setBackgroundColor(Color.RED);
        logoutButton.setBackgroundHoverColor(Design.darkRed);
        logoutButton.setOnMouseClicked(event -> logout());

        Region verticalSpacer = new Region();
        VBox.setVgrow(verticalSpacer, Priority.ALWAYS);

        VBox leftColumn = new VBox(
            titleLabel,
            // displayNameRow,
            usernameRow,
            // passwordRow,
            // saveButton,
            verticalSpacer,
            logoutButton
        );
        leftColumn.setSpacing(Design.spacing);
        leftColumn.setAlignment(Pos.TOP_LEFT);
        // content.setPrefWidth(400);
        leftColumn.setFillWidth(false);

        Region horizontalSpacer = new Region();
        HBox.setHgrow(horizontalSpacer, Priority.ALWAYS);

        //MARK: Right column
        //account icon
        int diameter = 400;
        ImageButton accountIcon = new ImageButton("Leaf.png", diameter / 2, diameter, diameter);
        accountIcon.background.setArcWidth(diameter);
        accountIcon.background.setArcHeight(diameter);
        accountIcon.setBackgroundColor(Design.mediumGreen);
        accountIcon.setBackgroundHoverColor(Design.mediumGreen);

        VBox rightColumn = new VBox(
            accountIcon
        );

        HBox horizontalStack = new HBox(
            leftColumn,
            horizontalSpacer,
            rightColumn
        );
        horizontalStack.setPadding(new Insets(Design.padding));

        getChildren().addAll(
            header,
            horizontalStack
        );
        VBox.setVgrow(horizontalStack, Priority.ALWAYS);
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
        Navigation.openEntryPage();
    }
}
