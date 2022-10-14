package vendingmachine.utils;

/**
 * @version v1.0
 * @author: Katherine Xu
 * @date: Created in 5/10/2022 1:55 am
 * @description: This is the instance for the cash
 */
public class Cash {
    private double value;
    private int quantity;

    public Cash(double value, int quantity) {
        this.value = value;
        this.quantity = quantity;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
