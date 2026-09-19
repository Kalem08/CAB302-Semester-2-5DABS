module app
{
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.xerial.sqlitejdbc;

    opens app to javafx.fxml;

    exports app;
    exports app.model;
    exports database;
    exports User;
    exports app.pages;
    opens app.pages to javafx.fxml;
    exports app.components;
    opens app.components to javafx.fxml;
}