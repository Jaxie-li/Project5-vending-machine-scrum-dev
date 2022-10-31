package vendingmachine.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class TransactionReportTest {

    private ArrayList<Order> ordersList;
    private Order order1;
    private Order order2;
    private User user1;
    private TransactionReport report;

    @BeforeEach
    public void init(){
        this.ordersList = new ArrayList<>();
        this.user1 = new User("testcustomer1", "123456", "customer");
        this.order1 = new Order(user1);
        this.ordersList.add(order1);
        this.report = new TransactionReport(ordersList);
    }

    @Test
    public void getContentTest() {
        Order.setData(Order.getData());
        assertNotNull(this.report.getContent());
    }

    @Test
    public void writeTest() throws IOException {
        report.write();
        File f = new File(TransactionReport.path);
        Scanner sc = new Scanner(f);
        ArrayList<String> outcome = new ArrayList<>();
        while (sc.hasNextLine()){
            outcome.add(sc.nextLine());
        }
        assertNotNull(outcome.toString());

    }
}