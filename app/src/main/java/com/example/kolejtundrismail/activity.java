package com.example.kolejtundrismail;

public class activity {
    public String id;
    public String Date;
    public String Issued_by;
    public String Link;
    public String Merit;
    public String Name;
    public String PIC;
    public String Phone_no;
    public String Place;

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getIssued_by() {
        return Issued_by;
    }

    public void setIssued_by(String issued_by) {
        Issued_by = issued_by;
    }

    public String getLink() {
        return Link;
    }

    public void setLink(String link) {
        Link = link;
    }

    public String getMerit() {
        return Merit;
    }

    public void setMerit(String merit) {
        Merit = merit;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPIC() {
        return PIC;
    }

    public void setPIC(String PIC) {
        this.PIC = PIC;
    }

    public String getPhone_no() {
        return Phone_no;
    }

    public void setPhone_no(String phone_no) {
        Phone_no = phone_no;
    }

    public String getPlace() {
        return Place;
    }

    public void setPlace(String place) {
        Place = place;
    }

    public activity(String id, String date, String issued_by, String link, String merit, String name, String PIC, String phone_no, String place) {
        this.id = id;
        Date = date;
        Issued_by = issued_by;
        Link = link;
        Merit = merit;
        Name = name;
        this.PIC = PIC;
        Phone_no = phone_no;
        Place = place;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public activity() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

}
