package io.github.p4ndaj.bit.lists;

/**
 * Created by P4ndaJ on 7/27/17.
 */

public class Password {
    String Title;
    String Password;
    String Tag;

    int Icon;
    int Id;
    int Favorite;

    // L.O.E.C: Low orbit Empty Constructor
    public Password() {

    }

    // Constructor
    public Password(String Title, String Password, String Tag, int Icon, int Id, int Favorite) {
        this.Title = Title;
        this.Password = Password;
        this.Tag = Tag;
        this.Icon = Icon;
        this.Id = Id;
        this.Favorite = Favorite;
    }

    // Constructor
    public Password(String Title, String Password, int Favorite, String Tag, int Icon) {
        this.Title = Title;
        this.Password = Password;
        this.Favorite = Favorite;
        this.Tag = Tag;
        this.Icon = Icon;
    }

    // Getting Title
    public String getTitle() {
        return this.Title;
    }

    // Setting Title
    public void setTitle(String Title) {
        this.Title = Title;
    }

    // Getting Password
    public String getPassword() {
        return this.Password;
    }

    // Setting Password
    public void setPassword(String Password) {
        this.Password = Password;
    }

    // Getting Tag
    public String getTag() {
        return this.Tag;
    }

    // Setting Tag
    public void setTag(String Tag) {
        this.Tag = Tag;
    }

    // Getting Icon
    public int getIcon() {
        return this.Icon;
    }

    // Setting Icon
    public void setIcon(int Icon) {
        this.Icon = Icon;
    }

    public int getId() {
        return this.Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public int getFavorite() {
        return this.Favorite;
    }

    public void setFavorite(int Favorite) {
        this.Favorite = Favorite;
    }
}

