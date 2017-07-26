package io.github.p4ndaj.bit.db;

import io.realm.RealmObject;

/**
 * Created by P4ndaJ on 7/25/17.
 */

public class Password extends RealmObject {
    public String Name;
    public String Password;
    public String Tag;

    public boolean Favorite;

    public int Logo;

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

    public String getTag() {
        return Tag;
    }

    public void setTag(String Tag) {
        this.Tag = Tag;
    }

    public boolean getFavorite() {
        return Favorite;
    }

    public void setFavorite(boolean Favorite) {
        this.Favorite = Favorite;
    }

    public int getLogo() {
        return Logo;
    }

    public void setLogo(int Logo) {
        this.Logo = Logo;
    }
}
