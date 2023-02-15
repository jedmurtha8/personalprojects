package com.techelevator.model;

public class Product {
    private int id;

    private String sku;

    private String name;

    private String description;

    private double price;

    private String imageName;

    public Product() { }


    public Product(int id, String sku, String name, String description, double price, String imageName) {
        this.id = id;
        this.sku = sku;
        this.name = name;
        this.description = description;
        this.price = price;
        this.imageName = imageName;
    }
    public Product(String sku, String name, String description, double price, String imageName) {
        this(0,sku,name,description,10.5,imageName);
    }


    public int getId() { return id; }

    public void setId(int id) {this.id = id;}

    public String getSku() {return sku;}

    public void setSku(String sku) {this.sku = sku;}

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    public String getDescription() {return description;}

    public void setDescription(String description) {this.description = description;}

    public double getPrice() {return price;}

    public void setPrice(double price) {this.price = price;}

    public String getImageName() {return imageName;}

    public void setImageName(String imageName) {this.imageName = imageName;}

}
