package vendingmachine.components;

import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.util.Duration;
import vendingmachine.utils.Product;

public class ProductComponents {
    private Label productLabel = new Label();
    private Product product;

    private Spinner<Integer> spinner;

    public ProductComponents(Product product, int x, int y) {
//        System.out.println(String.format("x:%s\ty:%s".formatted(x,y)));
        this.product = product;
        productLabel.setText(product.getItemName() + "\t\t$" + product.getItemPrice());

        productLabel.setLayoutX(x);
        productLabel.setLayoutY(y);
        productLabel.setPrefHeight(30);
        productLabel.setPrefWidth(150);
        spinner = new Spinner<Integer>(0,15,0);
        spinner.setLayoutX(x+150);
        spinner.setLayoutY(y);
        spinner.setPrefWidth(50);
        spinner.setPrefHeight(30);
    }
    public Label getProductLabel() {
        return productLabel;
    }

    public Spinner<Integer> getSpinner() {
        return spinner;
    }
}
