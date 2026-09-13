package User;

public class userDB {
    private String username;
    private String password;
    public userDB(String username, String password) {
        this.username = username;
        this.password = password;

    }
    //public void registerUser()
    //{
    //}
    public String getUsername()
    {
      return this.username;
    }
    public String getPassword()
    {
        return this.password;
    }
}
