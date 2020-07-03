package rs.ac.uns.ftn.sbnz.models.drools;

import java.util.Date;

public class FinancialReport {

    private int count;
    private double totalPrice;
    private double minPrice;
    private double maxPrice;
    private double avgPrice;
    private Date from;
    private Date to;

    public FinancialReport(Date from, Date to) {
        this.from = from;
        this.to = to;
    }

    public FinancialReport(int count, double totalPrice, double minPrice, double maxPrice, double avgPrice, Date from, Date to) {
        this.count = count;
        this.totalPrice = totalPrice;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
        this.avgPrice = avgPrice;
        this.from = from;
        this.to = to;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public double getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(double minPrice) {
        this.minPrice = minPrice;
    }

    public double getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(double maxPrice) {
        this.maxPrice = maxPrice;
    }

    public double getAvgPrice() {
        return avgPrice;
    }

    public void setAvgPrice(double avgPrice) {
        this.avgPrice = avgPrice;
    }

    public Date getFrom() {
        return from;
    }

    public void setFrom(Date from) {
        this.from = from;
    }

    public Date getTo() {
        return to;
    }

    public void setTo(Date to) {
        this.to = to;
    }

    @Override
    public String toString() {
        return "FinancialReport{" +
                "count=" + count +
                ", totalPrice=" + totalPrice +
                ", minPrice=" + minPrice +
                ", maxPrice=" + maxPrice +
                ", avgPrice=" + avgPrice +
                ", from=" + from +
                ", to=" + to +
                '}';
    }
}
