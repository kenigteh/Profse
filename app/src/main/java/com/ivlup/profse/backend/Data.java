package com.ivlup.profse.backend;

import java.util.ArrayList;

public class Data {
    private static ArrayList<Contractor> contractors;
    private static ArrayList<Link> links;
    private static ArrayList<Category> categories;


    public static ArrayList<Contractor> getContractors() {
        return contractors;
    }

    public static void setContractors(ArrayList<Contractor> contractors) {
        Data.contractors = contractors;
    }

    public static ArrayList<Link> getLinks() {
        return links;
    }

    public static void setLinks(ArrayList<Link> links) {
        Data.links = links;
    }

    public static ArrayList<Category> getCategories() {
        return categories;
    }

    public static void setCategories(ArrayList<Category> categories) {
        Data.categories = categories;
    }
}
