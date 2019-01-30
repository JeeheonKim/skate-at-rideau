package com.jonneykim.skateatrideau;

public class ListViewItem {
    String fromTo, status, maintTime, maintType, title, date;
    public ListViewItem(String fromtTo, String status, String maintTime, String maintType) {
        this.fromTo = fromtTo;
        this.status = status;
        this.maintTime = maintTime;
        this.maintType = maintType;
    }

    public String getFromTo() {
        return fromTo;
    }

    public String getStatus() {
        return status;
    }

    public String getMaintTime() {
        return maintTime;
    }

    public String getMaintType() {return maintType;}

    public void setFromTo(String s) {
        this.fromTo=s;
    }

    public void setStatus(String s) {
        this.status = s;
    }

    public void setMaintTime(String s) {
        this.maintTime = s;
    }

    public void setMaintType(String s) {this.maintType = s;}

    @Override
    public String toString() {
        return "ListViewItem{" +
                "fromTo='" + fromTo + '\'' +
                ", status='" + status + '\'' +
                ", maintTime='" + maintTime + '\'' +
                ", maintType='" + maintType + '\'' +
                ", title='" + title + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
