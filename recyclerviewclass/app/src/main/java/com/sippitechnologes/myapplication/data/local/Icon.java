package com.sippitechnologes.myapplication.data.local;

public class Icon {

    int icon;
    String name;

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Icon(int id, String name) {
        this.icon = id;
        this.name = name;
    }
}
