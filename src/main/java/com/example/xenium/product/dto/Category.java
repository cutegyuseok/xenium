package com.example.xenium.product.dto;

public class Category {
    int id;
    String cateName;
    String cateType;
    Object inDate;

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", cateName='" + cateName + '\'' +
                ", cateType='" + cateType + '\'' +
                ", inDate=" + inDate +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCateName() {
        return cateName;
    }

    public void setCateName(String cateName) {
        this.cateName = cateName;
    }

    public String getCateType() {
        return cateType;
    }

    public void setCateType(String cateType) {
        this.cateType = cateType;
    }

    public Object getInDate() {
        return inDate;
    }

    public void setInDate(Object inDate) {
        this.inDate = inDate;
    }
}

