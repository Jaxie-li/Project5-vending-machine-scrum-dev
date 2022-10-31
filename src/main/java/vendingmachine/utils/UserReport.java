package vendingmachine.utils;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class UserReport {
    private final String content;
    public static String path = "./UserReport.txt";

    public UserReport(ArrayList<User> users) {
        StringBuilder sb1 = new StringBuilder("User_name, Role\n");
        users
                .forEach(user -> {
                    sb1.append("{");
                    StringBuilder sb2 = new StringBuilder();
                    sb2
                            .append(user.getUsername())
                            .append(' ');

                    sb2
                            .append('[').append(user.getUserType()).append(']').append(' ');

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