//module com.example.cab302_jfx_project
module app
{
    requires javafx.controls;
    requires javafx.fxml;

    opens com.example.cab302_jfx_project to javafx.fxml;
    exports com.example.cab302_jfx_project;
}