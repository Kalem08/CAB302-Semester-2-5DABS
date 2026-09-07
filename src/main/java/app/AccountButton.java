package app;

//MARK: Imports
import javafx.scene.control.*;
import javafx.scene.layout.*;
// import javafx.geometry.Insets;
// import javafx.geometry.Pos;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

//MARK: Class
public class AccountButton extends StackPane
{
    // int radius = 50;

    public AccountButton(int diameter)
    {
        int radius = diameter / 2;
        Circle circle = new Circle(radius, Color.GREEN);
        Circle dot = new Circle(radius / 4, Color.WHITE);

        getChildren().addAll(circle, dot);

        setOnMouseClicked(event -> openAccountPage());
    }

    void openAccountPage()
    {
        HelloApplication.scene.setRoot(new AccountPage());
    }
}
