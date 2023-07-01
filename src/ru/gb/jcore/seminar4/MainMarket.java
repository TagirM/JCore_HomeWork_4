package ru.gb.jcore.seminar4;

public class MainMarket {
    static Customer[] customers;
    static Product[] products;
    static Order[] orders;

    public static void main(String[] args) {
//        создаем массивы покупателей, товаров и заказов
        customers = new Customer[]{new Customer("Bob", 30, "856788", Customer.Gender.MAN),
                new Customer("Janу", 40, "334455", Customer.Gender.WOMAN)};
        products = new Product[]{new Product("bread", 40, Product.Category.Premium),
                new Product("milk", 60, Product.Category.Standard),
                new Product("water", 100, Product.Category.Premium),
                new Product("sugar", 120, Product.Category.Standard),
                new Product("salt", 50, Product.Category.Premium)};
        orders = new Order[5];
        //        создаем счетчик успешных заказов
        int countSuccessOrder=0;
//        создаем массив входных данных для заказов
        String[] phoneCustomer= {customers[0].getPhone(),customers[1].getPhone(),customers[0].getPhone(),customers[1].getPhone(),customers[1].getPhone()};
        String[] titleProduct = {products[0].getTitle(),products[1].getTitle(),products[2].getTitle(),"tea",products[4].getTitle()};
        int[] amountProduct = {2,3,100,1,5};
//        создаем заказы
        for (int i = 0; i < orders.length; i++) {
            try {
                orders[i] = makePurchase(phoneCustomer[i],titleProduct[i], amountProduct[i]);
            } catch (AmountException e) {
            } catch (CustomerException e) {
                System.out.println(e.getMessage());
                return;
            } catch (ProductException e) {
                System.out.println(e.getMessage());
            }
            finally {
                if (orders[i]!=null){
                    countSuccessOrder++;
                    System.out.println("Success orders number "+ countSuccessOrder);
                    orders[i].printOrder();
                }
            }
        }
//      применяем случайные скидки к заказам
        System.out.println();
        for (int i = 0; i < orders.length; i++) {
            if (orders[i]!=null){
                try {
                    orders[i].discountCost();
                } catch (TooMuchSaleException e) {
                    System.out.println(e.getMessage());
                }finally {
                    orders[i].printOrder();
                }
            }
        }
    }

//    метод по созданию заказа
    public static Order makePurchase(String phone, String title, int amount) throws AmountException, CustomerException, ProductException {
        Customer customer = null;
        Product product = null;

        for (Product p: products) {
            if (p.getTitle().equals(title)){
                product = p;
            }
        }
        if (product == null){
            throw new ProductException("Unsuccessful orders. Product is absent, order is impossible");
        }
        if (amount>=100 || amount<1){
            try {
                throw new AmountException();
            }
            catch (Exception e){
                amount = 1;
                System.out.println("Excess quantities, amount set 1");
                makePurchase(phone, title, amount);
            }
        }
        for (Customer c: customers) {
            if (c.getPhone().equals(phone)){
                customer = c;
            }
        }
        if (customer == null){
            throw new CustomerException("Unsuccessful orders. Customer is uncorrected, application has completed");
        }
        return new Order(customer, product, amount, product.getPrice()*amount);
    }
}
