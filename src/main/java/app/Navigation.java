package app;

//MARK: Imports
import app.pages.EntryPage;
import app.pages.ResultsPage;
import app.pages.PlantPage;
import app.pages.AccountPage;
import app.pages.LogInPage;
import app.model.Plant;
import app.components.AuthenticationPanel.AuthMode;

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