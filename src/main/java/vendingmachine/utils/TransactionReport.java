package vendingmachine.utils;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class TransactionReport {
    private final String content;
    public static String path = "./TransactionReport.txt";

    public TransactionReport(ArrayList<Order> orders) {
        StringBuilder sb1 = new StringBuilder("Time, Products, Quantity, Total_paid, Total_change, Payment_method\n");
        orders.stream()
                .filter(order -> order.getStatus().equals("closed"))
                .forEach(order -> {
                    sb1.append("{");
                    StringBuilder sb2 = new StringBuilder();

                    sb2
                            .append(order.getStartTime().format(Order.formatter))
                            .append(' ');

                    final String[] itemInfo = {"", ""};

                    order.getProducts()
                            .forEach(product -> {
                                itemInfo[0] += product.getItemName() + ",";
                                itemInfo[1] += product.getItemQuantity() + ",";
                            });

                    sb2
                            .append('[').append(itemInfo[0]).append(']').append(' ')
                            .append('[').append(itemInfo[1]).append(']').append(' ')
                            .append((double) order.getPaid() / 100).append(' ')
                            .append((double) order.getExchange() / 100).append(' ')
                            .append(order.getPaymentMethod());


                    sb1.append(sb2).append("}\n");
                });
        content = sb1.toString();
    }

    public String getContent() {
        return content;
    }

    public void write() throws IOException {
        FileWriter fileWriter = new FileWriter(path);
        PrintWriter printWriter = new PrintWriter(fileWriter);
        printWriter.print(content);
        printWriter.close();
    }
}