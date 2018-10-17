package com.ivlup.profse.backend;


public class Category {
    private int id;
    private String name;
    private String photo;
    private int parent_id;
    private int type;

    public Category(int id, String name, String photo, int parent_id, int type) {
        this.id        = id;
        this.name      = name;
        this.photo     = photo;
        this.parent_id = parent_id;
        this.type      = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public int getParent_id() {
        return parent_id;
    }

    public void setParent_id(int parent_id) {
        this.parent_id = parent_id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
