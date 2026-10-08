package User;

public record User(String username, String password, Role role) {
    public User(String username, String password) {
        this(username, password, Role.STANDARD);
    }

    public User {
        if (username == null || username.trim().isEmpty()) {
            throw new IllegalArgumentException("Username cannot be empty");
        }
        if (password == null || password.isEmpty()) {
            throw new IllegalArgumentException("Password cannot be empty");
        }
        if (role == null) {
            throw new IllegalArgumentException("Role cannot be null");
        }

    }
}