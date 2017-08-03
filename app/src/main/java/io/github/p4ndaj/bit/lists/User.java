package io.github.p4ndaj.bit.lists;

/**
 * Created by P4ndaJ on 04/08/17.
 */

public class User {
    String User;
    String Password;

    int Id;

    public User() {

    }

    public User(String User, String Password, int Id) {
        this.User = User;
        this.Password = Password;
        this.Id = Id;
    }

    public User(String User, String Password) {
        this.User = User;
        this.Password = Password;
    }

    public String getUser() {
        return this.User;
    }

    public void setUser(String User) {
        this.User = User;
    }

    public String getPassword() {
        return this.Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public int getId() {
        return this.Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }
}
