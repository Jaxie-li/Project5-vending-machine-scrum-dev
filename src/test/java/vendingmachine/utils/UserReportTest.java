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
        this.testUser = new ArrayList<>();
        this.user1 = new User("Karen", "karen", "customer");
        this.user2 = new User("Phone","phone", "customer");
        this.testUser.add(user1);
        this.testUser.add(user2);
        this.report = new UserReport(testUser);
    }

    @Test
    public void getContentTest() {
        assertNotNull(this.report.getContent());
    }

    @Test
    public void writeTest() throws IOException {
        this.report.write();
        File f = new File(UserReport.path);
        Scanner sc = new Scanner(f);
        ArrayList<String> outcome = new ArrayList<>();
        while (sc.hasNextLine()){
            outcome.add(sc.nextLine());
        }
        assertNotNull(outcome.toString());
    }
}