package com.pict.acm.pulzion18.model;

public class SponsorSnapshot {
    private String name;
    private String imageUrl;
    private String website;
    private String sponserType;
    private long index;

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

    public SponsorSnapshot(String name, String imageUrl, String website, String sponserType, long index) {

        this.name = name;
        this.imageUrl = imageUrl;
        this.website = website;
        this.sponserType = sponserType;
        this.index = index;
    }

    public SponsorSnapshot() {

    }

    public String getSponserType() {
        return sponserType;
    }

    public void setSponserType(String sponserType) {
        this.sponserType = sponserType;
    }

    public long getIndex() {
        return index;
    }

    public void setIndex(long index) {
        this.index = index;
    }
}
