package User;

import java.util.Map;
import  java.util.HashMap;

public class authManager
{
    private String username;
    private String password;
    private String saferUsername;
    private int passwordMinLength = 8;
    private boolean validUsername;
    // create array for errors[];
    private Map<String, userDB> userTrack = new HashMap<>();

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
    public void Registration()
    {
        //input wanted username
        validUsername = checkUsernameExistence(username);
        if(validUsername)
        {
            createPassword();
        }
        else
        {
            //make user input username again
            System.out.println("Invalid Username.");
        }


    }
    public void signIN()
    {
        //get username
        saferUsername = this.username.toLowerCase();
        //use key to find the account
        if(userTrack.containsKey(saferUsername))
        {
            System.out.println("Username found.");
            enterPassword();

        }
        else
        {
            System.out.println("Username does not exist.");
        }
    }

    //public void setPassword(String password)
    {
        this.password = password;
    }
    // For registration
    private boolean checkUsernameExistence(String Username)
    {
        saferUsername = this.username.toLowerCase();
        if(!userTrack.containsKey(this.saferUsername))
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
    // might need to give the original value in the ()
    private void createPassword()
    {
        //get input of password
        if (checkValidPass() == PasswordError.VALID)
        {
            userDB userAccount = new userDB(this.username,this.password);
            userTrack.put(saferUsername,userAccount);
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
    private void enterPassword()
    {
        //get password
        userDB userAccount = userTrack.get(saferUsername);
        //check if the password is the same as the existing account
        if(this.password == userAccount.getPassword())
        {
            System.out.println("Logged In???");
        }
        else
        {
            System.out.println("Wrong Password.");
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
