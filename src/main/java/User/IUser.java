package User;

public interface IUser {
    String getUsername();
    String getPassword();

    void changeAccountInformation();
    void updateUsername();
    void updatePassword();

    boolean loggedIn();
    void logOut();


}
