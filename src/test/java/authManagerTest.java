import User.authManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class authManagerTest {

    private authManager authManagerObject;

    @BeforeEach
    public void setUp() {
        authManager.signOut();

        authManagerObject = new authManager("5DABS", "5Dabs!1");
    }

    @Test
    public void testIsSignedInInitiallyFalse() {
        assertFalse(authManager.isSignedIn());
    }

    @Test
    public void testGetUsernameWhenNotSignedIn() {
        assertEquals("", authManager.getUsername());
    }

    @Test
    public void testAuthenticateWithInvalidCredentials() {
        authManagerObject = new authManager("InvalidUser", "WrongPassword");

        assertFalse(authManagerObject.authenticate());
        assertFalse(authManager.isSignedIn());
    }

    @Test
    public void testSignOut() {
        authManager.signOut();

        assertFalse(authManager.isSignedIn());
        assertEquals("", authManager.getUsername());
    }
    @Test
    public void testCheckNullPassword()
    {
        authManager.signOut();
        authManagerObject = new authManager("5DABS", "");
        assertFalse(authManagerObject.Registration());
    }
    @Test
    public void testCheckEmptyPassword()
    {
        authManager.signOut();
        authManagerObject = new authManager("5DABS", null);
        assertFalse(authManagerObject.Registration());
    }
    @Test
    public void testCheckPasswordMinimumLength()
    {
        authManager.signOut();
        authManagerObject = new authManager("5DABS", "Aa!1");
        assertFalse(authManagerObject.Registration());
    }
    @Test
    public void testCheckNoLowerCaseInPassword()
    {
        authManager.signOut();
        authManagerObject = new authManager("5DABS", "DABSDABSDABS");
        assertFalse(authManagerObject.Registration());
    }
    @Test
    public void testCheckNoUpperCaseInPassword()
    {
        authManager.signOut();
        authManagerObject = new authManager("5DABS", "dabsdabsdabs");
        assertFalse(authManagerObject.Registration());
    }
    @Test
    public void testCheckNoSpecialCharactersInPassword()
    {
        authManager.signOut();
        authManagerObject = new authManager("5DABS", "DabsDabsDabs");
        assertFalse(authManagerObject.Registration());
    }
}