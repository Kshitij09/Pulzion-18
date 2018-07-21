package com.pict.acm.pulzion18.model;

import java.util.HashMap;

public class EventSnapshot {
    String name;
    String description;
    String fees;
    Long index;
    String type;
    String status;
    String rules;
    String tagline;
    String teams;
    HashMap<String, Long> contact;

    public EventSnapshot() {
    }

    public EventSnapshot(String name, String description, String fees, Long index, String type, String status, String rules, String tagline, String teams, HashMap<String, Long> contact) {
        this.name = name;
        this.description = description;
        this.fees = fees;
        this.index = index;
        this.type = type;
        this.status = status;
        this.rules = rules;
        this.tagline = tagline;
        this.teams = teams;
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

    public String getFees() {
        return fees;
    }

    public void setFees(String fees) {
        this.fees = fees;
    }

    public Long getIndex() {
        return index;
    }

    public void setIndex(Long index) {
        this.index = index;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRules() {
        return rules;
    }

    public void setRules(String rules) {
        this.rules = rules;
    }

    public String getTagline() {
        return tagline;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    public String getTeams() {
        return teams;
    }

    public void setTeams(String teams) {
        this.teams = teams;
    }

    public HashMap<String, Long> getContact() {
        return contact;
    }

    public void setContact(HashMap<String, Long> contact) {
        this.contact = contact;
    }
}
