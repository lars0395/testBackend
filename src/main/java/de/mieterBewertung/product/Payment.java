package de.mieterBewertung.product;

public class Payment {

    private double costs;
    private PaymentRate rate;

    public Payment(double costs, PaymentRate rate) {
        this.costs = costs;
        this.rate = rate;
    }

    public double getCosts() {
        return costs;
    }

    public void setCosts(double costs) {
        this.costs = costs;
    }

    public PaymentRate getRate() {
        return rate;
    }

    public void setRate(PaymentRate rate) {
        this.rate = rate;
    }
}
