package io.github.p4ndaj.bit.db;

import io.realm.RealmObject;

/**
 * Created by P4ndaJ on 7/25/17.
 */

public class Password extends RealmObject {
    public String Name;
    public String Password;

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

}
