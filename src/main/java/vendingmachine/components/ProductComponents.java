package vendingmachine.components;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.util.Duration;
import vendingmachine.utils.Product;

import java.util.ArrayList;

public class ProductComponents {

    private Label productName = new Label();
    private Label productPrice = new Label();
    private Label productQuantity = new Label();
    private ArrayList<Node> elements = new ArrayList<>();
    private Product product;
    private final int GAP = 50;

    private Spinner<Integer> spinner;

    public ProductComponents(Product product, int x, int y) {
        this.product = product;
        productName.setText(product.getItemName());

        productName.setLayoutX(x);
        productName.setLayoutY(y);
        productName.setPrefWidth(2*GAP);

        productPrice.setText(String.format("$ %.2f",product.getItemPrice()/100.0));
        productPrice.setLayoutX(x+2*GAP);
        productPrice.setLayoutY(y);

        productQuantity.setText(String.format("%s left",product.getItemQuantity()));
        productQuantity.setLayoutX(x+3*GAP);
        productQuantity.setLayoutY(y);

        spinner = new Spinner<Integer>(0,15,0);
        spinner.setLayoutX(x+200);
        spinner.setLayoutY(y);
        spinner.setPrefWidth(50);

        elements.add(productName);
        elements.add(productPrice);
        elements.add(productQuantity);
        elements.add(spinner);
    }

    public ArrayList<Node> getElements() {
        return elements;
    }

    public Product getProduct() {
        return product;
    }

    public Spinner<Integer> getSpinner() {
        return spinner;
    }
}
