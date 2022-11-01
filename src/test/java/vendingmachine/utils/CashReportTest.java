package vendingmachine.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class CashReportTest {

    private ArrayList<Cash> cashList;

    private Cash cash1;

    private Cash cash2;

    private CashReport report;


    @BeforeEach
    public void init(){
        this.cashList = new ArrayList<>();
        this.cash1 = new Cash(50, 2);
        this.cash2 = new Cash(10, 2);
        this.cashList.add(cash1);
        this.cashList.add(cash2);
        this.report = new CashReport(cashList);
    }

    @Test
    public void getContentTest() {
        assertNotNull(this.report.getContent());
    }

    @Test
    public void writeTest() throws IOException {
        report.write();
        File f = new File(CashReport.path);
        Scanner sc = new Scanner(f);
        ArrayList<String> outcome = new ArrayList<>();
        while (sc.hasNextLine()){
            outcome.add(sc.nextLine());
        }
        assertNotNull(outcome.toString());
    }
}