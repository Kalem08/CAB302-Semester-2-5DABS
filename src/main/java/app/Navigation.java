package app;

import app.pages.AccountPage;
import app.pages.EditPage;
import app.pages.EntryPage;
import app.pages.LogInPage;
import app.pages.PlantPage;
import app.pages.ResultsPage;
import app.AuthenticationPanel.AuthMode;
import app.model.Plant;

//MARK: Class
//this class standardises a bunch of functions across the project for navigation and loading different pages
public class Navigation
{
    public static void openEntryPage()
    {
        HelloApplication.setPage(new EntryPage());
    }

    public static void openResultsPage(String query)
    {
        HelloApplication.setPage(new ResultsPage(query));
    }
    
    public static void openPlantPage(Plant plant, String searchQuery)
    {
        HelloApplication.setPage(new PlantPage(plant, searchQuery));
    }

    public static void openPlantPage(Plant plant)
    {
        HelloApplication.setPage(new PlantPage(plant));
    }
    //override with 2nd parameter
    public static void openPlantPage(Plant plant, String query)
    {
        HelloApplication.setPage(new PlantPage(plant, query));
    }

    public static void openEditPage(Plant plant)
    {
        HelloApplication.setPage(new EditPage(plant));
    }

    public static void openAccountPage()
    {
        HelloApplication.setPage(new AccountPage());
    }

    public static void openLogInPage()
    {
        HelloApplication.setPage(new LogInPage(AuthMode.Login));
    }

    public static void openRegisterPage()
    {
        HelloApplication.setPage(new LogInPage(AuthMode.Register));
    }
}