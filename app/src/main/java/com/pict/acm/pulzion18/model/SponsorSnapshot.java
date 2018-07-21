package com.pict.acm.pulzion18.model;

public class SponsorSnapshot {
    private String name;
    private String imageUrl;
    private String website;
    private String sponsorType;

    public SponsorSnapshot() {

    }

    public SponsorSnapshot(String name, String imageUrl, String website, String sponsorType) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.website = website;
        this.sponsorType = sponsorType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getSponsorType() {
        return sponsorType;
    }

    public void setSponsorType(String sponsorType) {
        this.sponsorType = sponsorType;
    }
}
