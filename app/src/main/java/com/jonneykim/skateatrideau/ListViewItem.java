package com.jonneykim.skateatrideau;

public class ListViewItem {
    String title, date;
    public ListViewItem(String title, String date) {
        this.title = title;
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public String getDate() {
        return date;
    }


    public void setTitle(String title) {
        this.title = title;
    }

    public void setDate(String date) {
        this.date = date;
    }



    @Override
    public String toString() {
        return "ListViewItem{" +
                "title='" + title + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
