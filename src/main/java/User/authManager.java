package User;

public class authManager {
    private static final SQLiteUserDAO userDAO = new SQLiteUserDAO();
    private static boolean signedIn;
    private static String signedInUsername;

    private final String username;
    private final String password;
    private String saferUsername;
    private final int passwordMinLength = 8;

    public enum PasswordError {
        VALID,
        EMPTY,
        LENGTH_MIN,
        NO_SPECIAL,
        NO_CAPITAL,
        NO_LOWER
    }

    public authManager(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public boolean Registration() {
        if (!checkUsernameExistence(username) || checkValidPass() != PasswordError.VALID) {
            return false;
        }

        User userAccount = new User(saferUsername, password);
        return userDAO.registerUser(userAccount);
    }

    public boolean authenticate() {
        signedIn = false;
        signedInUsername = null;

        if (username == null || username.trim().isEmpty() || password == null) {
            return false;
        }

        saferUsername = username.trim().toLowerCase();
        signedIn = userDAO.authenticate(saferUsername, password);
        if (signedIn) {
            signedInUsername = saferUsername;
        }
        return signedIn;
    }

    public static boolean isSignedIn() {
        return signedIn;
    }

    public static void signOut() {
        signedIn = false;
        signedInUsername = null;
    }

    private boolean checkUsernameExistence(String usernameToCheck) {
        if (usernameToCheck == null || usernameToCheck.trim().isEmpty()) {
            return false;
        }

        saferUsername = usernameToCheck.trim().toLowerCase();
        return !userDAO.exists(saferUsername);
    }

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
