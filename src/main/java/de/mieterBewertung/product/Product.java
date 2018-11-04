package de.mieterBewertung.product;

public class Product {

    private int id;
    private String title;
    private String description;
    private ProductType productType;
    private Payment payment;

    public Product(int id, String title, String description, ProductType productType, Payment payment) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.productType = productType;
        this.payment = payment;
    }

    public Product(int id, String title, String description, ProductType productType, double costs, PaymentRate paymentRate) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.productType = productType;
        this.payment = new Payment(costs, paymentRate);
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

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }
}
