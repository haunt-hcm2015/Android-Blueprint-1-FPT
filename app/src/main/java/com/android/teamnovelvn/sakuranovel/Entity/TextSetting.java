package com.android.teamnovelvn.sakuranovel.Entity;

/**
 * Created by Thanh on 11/25/2016.
 */

public class TextSetting {
    private String Name;
    private String Title;

    public TextSetting(String name, String title) {
        Name = name;
        Title = title;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }
}
