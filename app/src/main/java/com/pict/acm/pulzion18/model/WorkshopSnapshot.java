package com.pict.acm.pulzion18.model;

public class WorkshopSnapshot {
    String name;
    String description;
    String tagline;
    String date;
    String fees;
    String contact;

    public WorkshopSnapshot() {
    }

    public WorkshopSnapshot(String name, String description, String tagline, String date, String fees, String contact) {
        this.name = name;
        this.description = description;
        this.tagline = tagline;
        this.date = date;
        this.fees = fees;
        this.contact = contact;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTagline() {
        return tagline;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getFees() {
        return fees;
    }

    public void setFees(String fees) {
        this.fees = fees;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
}
