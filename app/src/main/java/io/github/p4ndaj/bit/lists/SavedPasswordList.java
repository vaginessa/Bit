package io.github.p4ndaj.bit.lists;

/**
 * Created by P4ndaJ on 7/26/17.
 */

public class SavedPasswordList {
    private String Title, Password, Tag;

    private int Icon;

    private boolean Favorite;

    public SavedPasswordList(String title, String Password, String Tag, int Icon, boolean Favorite) {
        this.Title = title;
        this.Password = Password;
        this.Tag = Tag;

        this.Icon = Icon;

        this.Favorite = Favorite;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String name) {
        this.Title = name;
    }

    public String getTag() {
        return Tag;
    }

    public void setTag(String tag) {
        this.Tag = tag;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        this.Password = password;
    }

    public int getIcon() {
        return Icon;
    }

    public void setIcon(int Icon) {
        this.Icon = Icon;
    }

    public boolean getFavorite() {
        return Favorite;
    }

    public void setFavorite(boolean Favorite) {
        this.Favorite = Favorite;
    }
}