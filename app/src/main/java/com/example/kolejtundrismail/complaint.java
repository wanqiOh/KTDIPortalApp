package com.example.kolejtundrismail;

public class complaint {
    String Name, Phone, Building, Floor, Room, Problem, Desc, Date, Status;

    public complaint(String name, String phone, String building, String floor, String room, String problem, String desc, String date, String status) {
        Name = name;
        Phone = phone;
        Building = building;
        Floor = floor;
        Room = room;
        Problem = problem;
        Desc = desc;
        Date = date;
        Status = status;
    }

    public complaint() {

    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getBuilding() {
        return Building;
    }

    public void setBuilding(String building) {
        Building = building;
    }

    public String getFloor() {
        return Floor;
    }

    public void setFloor(String floor) {
        Floor = floor;
    }

    public String getRoom() {
        return Room;
    }

    public void setRoom(String room) {
        Room = room;
    }

    public String getProblem() {
        return Problem;
    }

    public void setProblem(String problem) {
        Problem = problem;
    }

    public String getDesc() {
        return Desc;
    }

    public void setDesc(String desc) {
        Desc = desc;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }
}
