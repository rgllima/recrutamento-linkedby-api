package br.com.ecommerce.api.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Date;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "Please enter title")
    private String title;

    @NotEmpty(message = "Please enter description")
    private String description;

    @NotNull(message = "Please enter price")
    private double price;

    @NotEmpty(message = "Please enter cover")
    private String cover;

    @NotNull(message = "Please enter discount")
    private double discount;

    @NotNull(message = "Please enter stock")
    private int stock;

    private boolean available;

    private Date createdAt;

    public Product() {}

    public Product(int id, String title, String description, double price, String cover, double discount, int stock, boolean available, Date createdAt) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.price = price;
        this.cover = cover;
        this.discount = discount;
        this.stock = stock;
        this.available = available;
        this.createdAt = createdAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", cover='" + cover + '\'' +
                ", discount=" + discount +
                ", stock=" + stock +
                ", available=" + available +
                ", createdAt=" + createdAt +
                '}';
    }
}
