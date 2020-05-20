package com.example.kolejtundrismail;

public class inform {
    String Date,Description, Note, PIC, Place, Time, Title;

    public inform(String date, String description, String note, String PIC, String place, String time, String title) {
        Date = date;
        Description = description;
        Note = note;
        this.PIC = PIC;
        Place = place;
        Time = time;
        Title = title;
    }

    public inform() {

    }

    public String getDate() {
        return Date;
    }

    public String getDescription() {
        return Description;
    }

    public String getNote() {
        return Note;
    }

    public String getPIC() {
        return PIC;
    }

    public String getPlace() {
        return Place;
    }

    public String getTime() {
        return Time;
    }

    public String getTitle() {
        return Title;
    }

    public void setDate(String date) {
        Date = date;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public void setNote(String note) {
        Note = note;
    }

    public void setPIC(String PIC) {
        this.PIC = PIC;
    }

    public void setPlace(String place) {
        Place = place;
    }

    public void setTime(String time) {
        Time = time;
    }

    public void setTitle(String title) {
        Title = title;
    }
}
