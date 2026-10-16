package app;

//MARK: Imports
import app.pages.AccountPage;
import app.pages.EditPage;
import app.pages.EntryPage;
import app.pages.LogInPage;
import app.pages.PlantPage;
import app.pages.ResultsPage;
import app.components.AuthenticationPanel.AuthMode;
import app.model.Plant;

//MARK: Class
/**
 * A public class that contains all of the methods for loading different pages.
 */
public class Navigation
{
    /**
     * Show the page first present when the application is open. Consists of a centre-screen search bar and app logo.
     */
    public static void openEntryPage()
    {
        HelloApplication.setPage(new EntryPage());
    }

    /**
     * Show the results page of all the plants matching a given query.
     * @param searchQuery The term to use when searching
     */
    public static void openResultsPage(String searchQuery)
    {
        HelloApplication.setPage(new ResultsPage(searchQuery));
    }
    
    /**
     * Show the page of a plant and it's details.
     * @param plant The plant to be shown
     * @param searchQuery The term to use when returning to the search results page
     */
    public static void openPlantPage(Plant plant, String searchQuery)
    {
        HelloApplication.setPage(new PlantPage(plant, searchQuery));
    }

    /**
     * Show the page of a plant and it's details.
     * @param plant The plant to be shown
     */
    public static void openPlantPage(Plant plant)
    {
        HelloApplication.setPage(new PlantPage(plant));
    }

    /**
     * Show the page of a plant's details to be edited.
     * @param plant The plant to be edited
     */
    public static void openEditPage(Plant plant)
    {
        HelloApplication.setPage(new EditPage(plant, false));
    }

    /**
     * Show the page of a plant's details to be edited.
     * @param plant The plant to be edited.
     * @param searchQuery The search term to return to when exiting the page
     */
    public static void openEditPage(Plant plant, String searchQuery)
    {
        HelloApplication.setPage(new EditPage(plant, searchQuery, false));
    }

    public static void openAddPage(Plant plant)
    {
        HelloApplication.setPage(new EditPage(plant, true));
    }

    public static void openAddPage(Plant plant, String searchQuery)
    {
        HelloApplication.setPage(new EditPage(plant, searchQuery, true));
    }

    /**
     * Open the page of the logged in user.
     */
    public static void openAccountPage()
    {
        HelloApplication.setPage(new AccountPage());
    }

    /**
     * Open the page that users can sign in to an account from.
     */
    public static void openLogInPage()
    {
        HelloApplication.setPage(new LogInPage(AuthMode.Login));
    }

    /**
     * Open the page that users can register an account from.
     */
    public static void openRegisterPage()
    {
        HelloApplication.setPage(new LogInPage(AuthMode.Register));
    }
}