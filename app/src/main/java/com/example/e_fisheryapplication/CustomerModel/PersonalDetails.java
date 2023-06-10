package com.example.e_fisheryapplication.CustomerModel;

public class PersonalDetails {
    String fullname;
    long mob;

    public PersonalDetails() {
    }

    public PersonalDetails(String fullname, long mob) {
        this.fullname = fullname;
        this.mob = mob;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public long getMob() {
        return mob;
    }

    public void setMob(long mob) {
        this.mob = mob;
    }
}
