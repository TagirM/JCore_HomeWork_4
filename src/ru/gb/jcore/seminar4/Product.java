package ru.gb.jcore.seminar4;

public class Product {
    enum Category{Standard, Premium}
    Category category;
    private String title;
    private int price;
    public Product(String title, int price, Category category){
        this.title=title;
        this.price=price;
        this.category=category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
