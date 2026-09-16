package User;

//authentication manager
public class authManager
{
    private static boolean signedIn;
    
    private String username;
    private String password;
    private String saferUsername;
    private int passwordMinLength = 8;
    private boolean validUsername;
    public enum PasswordError
    {
        VALID,
        EMPTY,
        LENGTH_MIN,
        NO_SPECIAL,
        NO_CAPITAL,
        NO_LOWER


    }


    // add private list/dictionary/map being the user database

    //Constructor getting the username and password to compare with the existing user database
    public authManager(String username, String password)
    {
        this.username = username;
        this.password = password;
    }
    public boolean Registration()
    {
        validUsername = checkUsernameExistence(username);
        if (!validUsername || checkValidPass() != PasswordError.VALID) {
            return false;
        }

        userDB userAccount = new userDB(saferUsername, password);
        return userAccount.registerUser();
    }
    public void signIN()
    {
        if (authenticate()) {
            System.out.println("Logged In???");
        } else {
            System.out.println("Invalid username or password.");
        }
    }

    public boolean authenticate()
    {
        signedIn = false;
        if (username == null || username.trim().isEmpty() || password == null) {
            return false;
        }

        saferUsername = username.trim().toLowerCase();
        signedIn = userDB.authenticate(saferUsername, password);
        return signedIn;
    }

    //returns signedIn variable, so it can remain private
    public static boolean isSignedIn()
    {
        return signedIn;
    }

    public static void signOut()
    {
        //set flag to false
        signedIn = false;

        //clear other variables (these are not static, hence are commented out)
        // username = null;
        // password = null;
        // saferUsername = null;
        // validUsername = false;
    }

    // For registration
    private boolean checkUsernameExistence(String Username)
    {
        if (Username == null || Username.trim().isEmpty()) {
            return false;
        }

        saferUsername = Username.trim().toLowerCase();
        if(!userDB.exists(this.saferUsername))
        {
            System.out.println("Valid username");
            return true;
        }
        else
        {
            System.out.println("Username already exists.");
            return false;
        }
    }
    private PasswordError checkValidPass()
    {
        if (this.password == null) {
            return PasswordError.EMPTY;
        } else if (this.password.length() < passwordMinLength) {
            return PasswordError.LENGTH_MIN;
        } else if (!this.password.matches(".*[\\W_].*")) {
            return PasswordError.NO_SPECIAL;
        } else if (!this.password.matches(".*[A-Z].*")) {
            return PasswordError.NO_CAPITAL;
        } else if (!this.password.matches(".*[a-z].*")) {
            return PasswordError.NO_LOWER;
        } else {
            return PasswordError.VALID;
        }
    }
//    private boolean checkValidUsername()
//    {
//
//    }

    //signing in
//    public void checkUsername()
//    {
//
//    }
}
