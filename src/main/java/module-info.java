//module com.example.cab302_jfx_project
module app
{
    requires javafx.controls;
    requires javafx.fxml;

    opens app to javafx.fxml;
    exports app;
}