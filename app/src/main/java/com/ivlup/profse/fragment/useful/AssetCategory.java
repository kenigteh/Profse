package com.ivlup.profse.fragment.useful;

public class AssetCategory {
    private int id;
    private String name;
    private String photo;
    private int parent_id;
    private int type;

    public AssetCategory(int id, String name, String photo, int parent_id, int type) {
        this.id = id;
        this.name = name;
        this.photo = photo;
        this.parent_id = parent_id;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhoto() {
        return photo;
    }

    public int getParent_id() {
        return parent_id;
    }

    public int getType() {
        return type;
    }
}
