package app.pages;

//MARK: Imports
import javafx.scene.control.*;
import javafx.scene.layout.*;
import app.AuthenticationPanel;
import app.Design;
import app.AuthenticationPanel.AuthMode;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.*;
import javafx.scene.image.*;
import javafx.scene.effect.*; //for image colouring

//could also be called 'AccountPanel' or 'AuthenticationPanel', as this encompasses both log in AND registration
public class LogInPage extends StackPane
{
    //MARK: Variables
    // boolean registrationMode; //defines whether the page shows 'log in' or 'register'
    int boxWidth = 300;
    int boxHeight = 400;
    int elementWidth = boxWidth - (Design.padding * 2);
    Color darkTextColour = Design.grey20;

    //empty constructor
    public LogInPage()
    {
        // this(false); //default to 'log in' mode
        this(AuthMode.Login);
    }

    //MARK: Constructor
    public LogInPage(AuthMode authenticationMode)
    {
        // this.registrationMode = registrationMode;

        //background
        Rectangle background = new Rectangle();
        background.setFill(Color.GREEN);
        background.widthProperty().bind(this.widthProperty());
        background.heightProperty().bind(this.heightProperty());

        //logo
        ImageView logo = new ImageView(Design.getLogo());
        logo.setFitWidth(400);
        logo.setPreserveRatio(true);

        AuthenticationPanel authPanel = new AuthenticationPanel(authenticationMode);

        Region topSpacer = new Region();
        VBox.setVgrow(topSpacer, Priority.ALWAYS);
        topSpacer.setMaxHeight(180);
        Region bottomSpacer = new Region();
        VBox.setVgrow(bottomSpacer, Priority.ALWAYS);

        //the label at the very bottom of the screen
        Label footingLabel = new Label("Access millions of Australian native plants and make changes to pages with a free account.");
        footingLabel.setTextFill(Design.grey50);
        // footingLabel.setWrapText(true);
        footingLabel.maxWidth(boxWidth);
        footingLabel.setWrapText(true);
        // footingLabel.setAlignment(Pos.CENTER);
        footingLabel.setTextAlignment(TextAlignment.CENTER);

        VBox middleColumn = new VBox(
            topSpacer,
            logo,
            authPanel,
            bottomSpacer,
            footingLabel
        );
        middleColumn.setAlignment(Pos.CENTER);
        // middleColumn.setSpacing(Design.spacing);
        middleColumn.setPadding(new Insets(Design.padding));
        VBox.setMargin(logo, new Insets(0, 0, 50, 0)); //bottom padding
        
        //leaves
        int imageHeight = 600;
        Image leftLeafImage = Design.getImage("Leaf Right.png");
        Image rightLeafImage = Design.getImage("Leaf Left.png");

        ImageView leftLeaf = new ImageView(leftLeafImage);
        leftLeaf.setPreserveRatio(true);
        leftLeaf.setFitHeight(imageHeight);
        // leftLeaf.fitHeightProperty().bind(this.heightProperty());

        ImageView rightLeaf = new ImageView(rightLeafImage);
        rightLeaf.setPreserveRatio(true);
        rightLeaf.setFitHeight(imageHeight);
        // rightLeaf.fitHeightProperty().bind(this.heightProperty());

        ColorInput colour = new ColorInput(
            0, 0,
            // leftLeafImage.getWidth(),
            // leftLeafImage.getHeight(),
            imageHeight / 2,
            imageHeight,
            Design.superLightGrey
        );
        Blend blend = new Blend(
            BlendMode.SRC_ATOP,
            null,
            colour
        );
        leftLeaf.setEffect(blend);
        rightLeaf.setEffect(blend);

        Region leftSpacer = new Region();
        HBox.setHgrow(leftSpacer, Priority.ALWAYS);
        Region rightSpacer = new Region();
        HBox.setHgrow(rightSpacer, Priority.ALWAYS);

        HBox hBox = new HBox(
            leftLeaf,
            leftSpacer,
            middleColumn,
            rightSpacer,
            rightLeaf
        );
        hBox.setAlignment(Pos.CENTER);
        
        getChildren().addAll(
            // background,
            hBox
        );

        //set background colour
        setStyle("-fx-background-color: #FFFFFF;");
    }
}