package com.athiaa.AA2_web.Model;

import jakarta.persistence.*;
import org.springframework.stereotype.Component;

@Component
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private  int price;
    private String name;

    public Product(int id, int price, String name, String imageName, String imageType, byte[] imageData) {
        this.id = id;
        this.price = price;
        this.name = name;
        this.imageName = imageName;
        this.imageType = imageType;
        this.imageData = imageData;
    }

    private String imageName;
    private String imageType;
    @Lob
    private byte[] imageData;


    // Constructor with @JsonCreator and @JsonProperty annotations
//    @JsonCreator
//    public Product(@JsonProperty("productName") String name,
//                   @JsonProperty("productPrice") int price) {
//        this.name = name;
//        this.price = price;
//    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getImageType() {
        return imageType;
    }

    public void setImageType(String imageType) {
        this.imageType = imageType;
    }

    public void getImageData(byte[] bytes) {
        this.imageData = bytes;
    }
    public byte[] getImageData() {
        return imageData;
    }

    public void setImageData(byte[] imageData) {
        this.imageData = imageData;
    }





    public Product(){}

    public Product(int id, String name, int price){
        this.id = id;
        this.name = name;
        this.price = price;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", price=" + price +
                ", name='" + name + '\'' +
                '}';
    }
}
