package app;

//MARK: Imports
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

//the header with logo, search bar and account button at the top of most pages
public class Header extends StackPane
{
    private Label titleLabel;
    private TextField searchTextfield;
    private Button accountButton;

    public Header()
    {
        // Rectangle background = new Rectangle(400, 50);
        Rectangle background = new Rectangle();
        background.setFill(Color.LIGHTGRAY);
        background.widthProperty().bind(this.widthProperty()); //assume the width and height of the header
        background.heightProperty().bind(this.heightProperty());

        HBox contents = new HBox();

        titleLabel = new Label("Plantopedia");
        searchTextfield = new TextField("Search...");
        accountButton = new Button("Account");
        accountButton.setOnAction(event -> openAccountPage());

        searchTextfield.setPrefWidth(400);
        searchTextfield.setMinWidth(0);

        //create spacers
        Region leftSpacer = new Region();
        Region rightSpacer = new Region();
        HBox.setHgrow(leftSpacer, Priority.ALWAYS);
        HBox.setHgrow(rightSpacer, Priority.ALWAYS);

        // HBox contents = new HBox();
        contents.getChildren().addAll(
            titleLabel,
            leftSpacer,
            searchTextfield,
            rightSpacer,
            accountButton
        );
        // System.out.println("content min width: " + contents.getMinWidth());
        // contents.setMinWidth(0);
        contents.setAlignment(Pos.CENTER);
        contents.setSpacing(10.0);
        contents.setPadding(new Insets(10));
        // HBox.setHgrow(searchTextfield, Priority.ALWAYS);
        // contents.prefWidthProperty().bind(this.widthProperty());
        
        //add everything to the header stack
        getChildren().addAll(background, contents);
        setPrefHeight(50);
        setMinWidth(0); //need to set min width so header can shrink when window shrinks
    }

    //MARK: Methods
    void openAccountPage()
    {
        System.out.println("Open account page.");
    }
}