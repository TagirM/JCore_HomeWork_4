package ru.gb.jcore.seminar4;

import java.util.Random;

public class Order {
    int[] discount = {0, 5, 10, 15, 20};
    Customer customer;
    Product product;
    int amount;
    int cost;

    public Order(Customer customer, Product product, int amount, int cost){
        this.customer=customer;
        this.product=product;
        this.amount=amount;
        this.cost=cost;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

//    печать заказа (2 метода)
    @Override
    public String toString() {
        return customer.getName() + " buy " + product.getTitle() + " amount of " + amount + " cost of " + this.getCost();
    }

    public void printOrder(){
        System.out.println(this.toString());
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
    //    метод по созданию скидок
    public void discountCost() throws TooMuchSaleException {
        Random random = new Random();
        int discountPrice = discount[random.nextInt(discount.length)];
        System.out.println("Discount is " + discountPrice);
        if (discountPrice>15 && product.getCategory()== Product.Category.Premium){
            throw new TooMuchSaleException("Discount for this category product inapplicable");
        }
        this.setCost(this.getCost()-(this.getCost()*discountPrice)/100);
    }
}
