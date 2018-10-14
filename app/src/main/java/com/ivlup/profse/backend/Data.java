package com.ivlup.profse.backend;

import java.util.ArrayList;

public class Data {
    private static Contractor contractors[];
    private static Links      links[];
    private static Category   categories[];

    public static Contractor[] getContractors() {
        return contractors;
    }

    public static void setContractors(Contractor[] contractors) {
        Data.contractors = contractors;
    }

    public static Links[] getLinks() {
        return links;
    }

    public static void setLinks(Links[] links) {
        Data.links = links;
    }

    public static Category[] getCategories() {
        return categories;
    }

    public static void setCategories(Category[] categories) {
        Data.categories = categories;
    }
}
