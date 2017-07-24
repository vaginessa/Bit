package io.github.p4ndaj.bit.db;

import io.realm.RealmObject;

/**
 * Created by P4ndaJ on 7/24/17.
 */

public class User extends RealmObject {
    public String Email;
    public String Password;

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }
}
