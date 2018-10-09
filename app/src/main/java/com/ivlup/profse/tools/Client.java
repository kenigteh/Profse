package com.ivlup.profse.tools;

import java.util.HashMap;
import java.util.Map;

public class Client {

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

    public Client(String name, String category, String phone, String address, String site, String vk, String twitter, String facebook, String instagram, String discount) {
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
        return name;
    }

    public String getCategory() {
        return category;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public String getSite() {
        return site;
    }

    public String getVk() {
        return vk;
    }

    public String getTwitter() {
        return twitter;
    }

    public String getFacebook() {
        return facebook;
    }

    public String getInstagram() {
        return instagram;
    }

    public String getDiscount() {
        return discount;
    }
}
