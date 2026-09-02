package app;

import javafx.scene.control.*;
import javafx.scene.layout.*;

public class PlantPage extends BorderPane
{
    public PlantPage()
    {
        Header header = new Header();
        Label label1 = new Label("This is the plant page");

        setTop(header);
        setCenter(label1);
    }
}