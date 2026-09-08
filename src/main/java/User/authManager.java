package User;

import java.util.Locale;
import java.util.Map;
import  java.util.HashMap;

public class authManager
{
    private String username;
    private String password;
    private String saferUsername;
    private int passwordMinLength = 8;
    private Map<String, userDB> userTrack = new HashMap<>();



    // add private list/dictionary/map being the user database

    //Constructor getting the username and password to compare with the existing user database
    public authManager(String username, String password)
    {
        this.username = username;
        this.password = password;
    }


    public void setPassword(String password)
    {
        this.password = password;
    }
    // For registration
    public void checkUsernameExistence()
    {
        saferUsername = this.username.toLowerCase();
        if(userTrack.containsKey(this.saferUsername))
        {
            System.out.println("Username already exists.");
        }
        else
        {
            System.out.println("Valid username");
            enterPassword();
        }
    }
    // might need to give the original value in the ()
    private void enterPassword()
    {
        boolean isValidPass = true;
        if (isValidPass)
        {
            userDB userAccount = new userDB(this.username,this.password);
            userTrack.put(saferUsername,userAccount);
        }

    }
    private boolean checkValidPass()
    {
        if (this.password == null) {
            return false;
        } else if (this.password.length() < passwordMinLength) {
            return false;
        } else if (!this.password.matches(".*[\\W_].*")) {
            return false;
        } else if (!this.password.matches(".*[A-Z].*")) {
            return false;
        } else if (!this.password.matches(".*[a-z].*")) {
            return false;
        } else {
            return true;
        }
    }
    private boolean checkValidUsername()
    {

    }

    //signing in
//    public void checkUsername()
//    {
//
//    }
}
