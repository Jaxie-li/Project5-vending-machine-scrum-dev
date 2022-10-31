package vendingmachine.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class UserReportTest {

    private ArrayList<User> testUser;

    private User user1;

    private User user2;

    private UserReport report;

    @BeforeEach
    public void init(){
        testUser = new ArrayList<>();
        user1 = new User("Karen", "karen", "customer");
        user2 = new User("Phone","phone", "customer");
        testUser.add(user1);
        testUser.add(user2);
        report = new UserReport(testUser);
    }

    @Test
    public void getContentTest() {
        assertNotNull(report.getContent());
    }

    @Test
    public void writeTest() throws IOException {
        report.write();
        File f = new File(UserReport.path);
        Scanner sc = new Scanner(f);
        ArrayList<String> outcome = new ArrayList<>();
        while (sc.hasNextLine()){
            outcome.add(sc.nextLine());
        }
        assertNotNull(outcome.toString());
    }
}