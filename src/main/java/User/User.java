package User;

public class User implements IUser {
    private final String username;
    private final String password;

    public User(String username, String password) {
        if (username == null || username.trim().isEmpty()) {
            throw new IllegalArgumentException("Username cannot be empty");
        }
        if (password == null || password.isEmpty()) {
            throw new IllegalArgumentException("Password cannot be empty");
        }

        this.username = username;
        this.password = password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public void changeAccountInformation() {
    }

    @Override
    public void updateUsername() {
    }

    @Override
    public void updatePassword() {
    }

    @Override
    public boolean loggedIn() {
        return authManager.isSignedIn();
    }

    @Override
    public void logOut() {
    }
}