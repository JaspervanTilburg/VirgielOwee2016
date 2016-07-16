package com.example.jasper.virgielowee2016;

/**
 * Created by Jasper on 14-7-2016.
 */
public class ProgrammaItem {

    private String time;
    private String title;
    private String subtitle;

    public ProgrammaItem(String time, String title, String subtitle){
        this.time = time;
        this.title = title;
        this.subtitle = subtitle;
    }

    public String getTime() {
        return time;
    }

    public String getTitle() {
        return title;
    }

    public String getSubtitle() {
        return subtitle;
    }
}
