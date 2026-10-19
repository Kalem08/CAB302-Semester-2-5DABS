import User.Role;
import User.SQLiteUserDAO;
import User.User;
import database.DatabaseConnection;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Path;
import java.sql.Connection;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;

public class SQLiteUserDAOTest {

    @TempDir
    private Path tempDir;

    private SQLiteUserDAO userDAO;

    @BeforeEach
    void setUp() throws Exception {
        Path databasePath = tempDir.resolve("users-test.db");
        DatabaseConnection.setDatabaseUrl("jdbc:sqlite:" + databasePath.toAbsolutePath());

        try (
                Connection connection = DatabaseConnection.getConnection();
                Statement statement = connection.createStatement()
        ) {
            statement.execute("""
                    CREATE TABLE Users (
                        UserId INTEGER PRIMARY KEY AUTOINCREMENT,
                        Username TEXT NOT NULL UNIQUE,
                        Hash TEXT NOT NULL,
                        Role TEXT
                    )
                    """);
        }

        userDAO = new SQLiteUserDAO();
    }

    @AfterEach
    void tearDown() {
        DatabaseConnection.resetDatabaseUrl();
    }

    @Test
    void registerUserShouldStoreUserAndCredentials() {
        User user = new User("test-user", "TestPassword1!");

        assertTrue(userDAO.registerUser(user));
        assertTrue(userDAO.exists(user.username()));
        assertTrue(userDAO.authenticate(user.username(), user.password()));
        assertTrue(userDAO.getUserId(user.username()) > 0);
        assertEquals(Role.STANDARD, userDAO.getUserRole(user.username()));
    }

    @Test
    void registerUserShouldRejectDuplicateUsername() {
        User user = new User("duplicate-user", "TestPassword1!");

        assertTrue(userDAO.registerUser(user));
        assertFalse(userDAO.registerUser(new User(user.username(), "AnotherPassword1!")));
    }

    @Test
    void existsShouldReturnFalseForUnknownUsername() {
        assertFalse(userDAO.exists("unknown-user"));
    }

    @Test
    void authenticateShouldRejectIncorrectCredentials() {
        userDAO.registerUser(new User("login-user", "CorrectPassword1!"));

        assertFalse(userDAO.authenticate("login-user", "IncorrectPassword1!"));
        assertFalse(userDAO.authenticate("unknown-user", "CorrectPassword1!"));
    }

    @Test
    void getUserIdShouldReturnMinusOneForUnknownUsername() {
        assertEquals(-1, userDAO.getUserId("unknown-user"));
    }

    @Test
    void getUserRoleShouldReturnStoredRoleAndDefaultForUnknownUser() {
        userDAO.registerUser(new User("admin-user", "TestPassword1!", Role.ADMINISTRATOR));

        assertEquals(Role.ADMINISTRATOR, userDAO.getUserRole("admin-user"));
        assertEquals(Role.STANDARD, userDAO.getUserRole("unknown-user"));
    }
}
