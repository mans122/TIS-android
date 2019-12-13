package com.cookandroid.miniproject;

public class ListViewItem {
    private String Title;
    private String Date;
    private String Weather;
    private String Content;

    public void setTitle(String title) {
        Title = title;
    }

    public void setDate(String date) {
        Date = date;
    }

    public void setWeather(String weather) {
        Weather = weather;
    }

    public void setContent(String content) {
        Content = content;
    }

    public String getTitle() {
        return Title;
    }

    public String getDate() {
        return Date;
    }

    public String getWeather() {
        return Weather;
    }

    public String getContent() {
        return Content;
    }
}
