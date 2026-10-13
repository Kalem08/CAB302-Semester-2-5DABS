package User;
/**
 * authManager is a class that manages user authentication and registration.
 * It provides methods for user registration, authentication, and role management.
 */
public class authManager {
    private static final SQLiteUserDAO userDAO = new SQLiteUserDAO();
    private static boolean signedIn;
    private static String signedInUsername;
    private static Role signedInRole;

    private final String username;
    private final String password;
    private String saferUsername;
    private final int passwordMinLength = 8;

    /**
     * PasswordError is an enumeration that represents the possible errors that can occur during password validation.
     */
    public enum PasswordError {
        VALID,
        EMPTY,
        LENGTH_MIN,
        NO_SPECIAL,
        NO_CAPITAL,
        NO_LOWER
    }

    /**
     * Constructs a new authManager object with the specified username and password.
     * @param username The username of the user
     * @param password The password of the user
     */
    public authManager(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * Registers a new user with the provided username and password.
     * @return true if registration is successful, false otherwise
     */
    public boolean Registration() {
        if (!checkUsernameExistence(username) || checkValidPass() != PasswordError.VALID) {
            return false;
        }

        User userAccount = new User(saferUsername, password, Role.STANDARD);
        return userDAO.registerUser(userAccount);
    }

    /**
     * Authenticates the user with the provided username and password.
     * @return true if authentication is successful, false otherwise
     */
    public boolean authenticate() {
        signedIn = false;
        signedInUsername = null;
        signedInRole = null;

        if (username == null || username.trim().isEmpty() || password == null) {
            return false;
        }

        saferUsername = username.trim().toLowerCase();
        signedIn = userDAO.authenticate(saferUsername, password);
        if (signedIn) {
            signedInUsername = saferUsername;
            signedInRole = userDAO.getUserRole(saferUsername);
        }
        return signedIn;
    }

    /**
     * Checks if a user is currently signed in.
     * @return true if a user is signed in, false otherwise
     */
    public static boolean isSignedIn() {
        return signedIn;
    }

    /**
     * Gets the role of the currently signed-in user.
     * @return the role of the signed-in user, or Role.STANDARD if no user is signed in
     */
    public static Role getRole() {
        if (!signedIn) {
            return Role.STANDARD;
        }
        return signedInRole;
    }

    /**
     * Checks if the currently signed-in user has the role of ADMINISTRATOR.
     * @return true if the signed-in user is an administrator, false otherwise
     */
    public static boolean canManagePlants() {
        return signedIn && signedInRole == Role.ADMINISTRATOR;
    }

    /**
     * Signs out the currently signed-in user.
     */
    public static void signOut() {
        signedIn = false;
        signedInUsername = null;
        signedInRole = null;
    }

    /**
     * Checks if the provided username already exists in the database.
     * @param usernameToCheck The username to check for existence
     * @return true if the username does not exist, false otherwise
     */
    private boolean checkUsernameExistence(String usernameToCheck) {
        if (usernameToCheck == null || usernameToCheck.trim().isEmpty()) {
            return false;
        }

        saferUsername = usernameToCheck.trim().toLowerCase();
        return !userDAO.exists(saferUsername);
    }

    /**
     * Validates the provided password against the defined password policy.
     * @return a PasswordError indicating the result of the validation
     */
    private PasswordError checkValidPass() {
        if (this.password == null || this.password.isEmpty()) {
            return PasswordError.EMPTY;
        } else if (this.password.length() < passwordMinLength) {
            return PasswordError.LENGTH_MIN;
        } else if (!this.password.matches(".*[a-z].*")) {
            return PasswordError.NO_LOWER;
        } else if (!this.password.matches(".*[A-Z].*")) {
            return PasswordError.NO_CAPITAL;
        } else if (!this.password.matches(".*[\\W_].*")) {
            return PasswordError.NO_SPECIAL;
        } else {
            return PasswordError.VALID;
        }
    }

    /**
     * Gets the username from authManager.
     *
     * @return the signed-in username, or an empty string when no user is signed in
     */
    public static String getUsername() {
        if (signedIn) {
            return signedInUsername;
        }
        return "";
    }
}
