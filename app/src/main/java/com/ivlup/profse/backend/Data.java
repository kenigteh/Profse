package com.ivlup.profse.backend;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Data {
    private static Contractor []сontractors;
    private static Links []links;
    private static Category []categories;

    public static Contractor[] getContractors() {
        return сontractors;
    }

    public static void setContractors(Contractor[] contractors) {
        Data.сontractors = contractors;
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
