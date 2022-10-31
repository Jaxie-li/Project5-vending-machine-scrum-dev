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
        cashList = new ArrayList<>();
        cash1 = new Cash(50, 2);
        cash2 = new Cash(10, 2);
        cashList.add(cash1);
        cashList.add(cash2);
        report = new CashReport(cashList);
    }

    @Test
    public void getContentTest() {
        assertNotNull(report.getContent());
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