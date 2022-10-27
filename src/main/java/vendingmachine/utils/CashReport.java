package vendingmachine.utils;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class CashReport {
    private final String content;
    public static String path = "./CashReport.txt";

    public CashReport(ArrayList<Cash> cashes) {
        StringBuilder sb1 = new StringBuilder();
        cashes.forEach(cash -> {
            sb1.append(String.format("VALUE: %6.2f QUANTITY: %6d\n", (double) cash.getValue() / 100, cash.getQuantity()));
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