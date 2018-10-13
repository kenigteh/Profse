package com.ivlup.profse.contractor;

public class Contractor {

    private String name;
    private String category;
    private String phone;
    private String address;
    private String site;
    private String vk;
    private String twitter;
    private String facebook;
    private String instagram;
    private String discount;

    public Contractor(String name, String category, String phone, String address, String site, String vk, String twitter, String facebook, String instagram, String discount) {
        this.name = name;
        this.category = category;
        this.phone = phone;
        this.address = address;
        this.site = site;
        this.vk = vk;
        this.twitter = twitter;
        this.facebook = facebook;
        this.instagram = instagram;
        this.discount = discount;
    }

    public String getName() {
        if (!this.name.isEmpty())
        return name;
        else
            return "";
    }

    public String getCategory() {
        if (!this.category.isEmpty())
            return category;
        else
            return "";
    }

    public String getPhone() {
        if (!this.phone.isEmpty())
            return phone;
        else
            return "";
    }

    public String getAddress() {
        if (!this.address.isEmpty())
            return address;
        else
            return "";
    }

    public String getSite() {
        if (!this.site.isEmpty())
            return site;
        else
            return "";    }

    public String getVk() {
        if (!this.vk.isEmpty())
            return vk;
        else
            return "";
    }

    public String getTwitter() {
        if (!this.twitter.isEmpty())
            return twitter;
        else
            return "";
    }

    public String getFacebook() {
        if (!this.facebook.isEmpty())
            return facebook;
        else
            return "";
    }

    public String getInstagram() {
        if (!this.instagram.isEmpty())
            return instagram;
        else
            return "";
    }

    public String getDiscount() {
        if (!this.discount.isEmpty())
            return discount;
        else
            return "";
    }
}
