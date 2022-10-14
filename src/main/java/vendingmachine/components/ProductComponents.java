package vendingmachine.components;

import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import vendingmachine.utils.Product;

public class ProductComponents {
    private Label productLabel = new Label();
    private Product product;

    private Spinner<Integer> spinner = new Spinner<>();

    public ProductComponents(Product product, int x, int y) {
//        System.out.println(String.format("x:%s\ty:%s".formatted(x,y)));
        this.product = product;
        productLabel.setText(product.toString());

        SpinnerValueFactory<Integer> valueFactory =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(0,15);
        valueFactory.setValue(0);

        spinner.setValueFactory(valueFactory);

        productLabel.setLayoutX(x);
        productLabel.setLayoutY(y);
        productLabel.setPrefHeight(30);
        productLabel.setPrefWidth(150);

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
