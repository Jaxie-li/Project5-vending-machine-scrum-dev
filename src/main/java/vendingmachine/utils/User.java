package vendingmachine.utils;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

/**
 * @version v1.0
 * @author: Katherine Xu
 * @date: Created in 5/10/2022 2:41 am
 * @description: This is the ultils for the user
 */
public class User {
    private static JSONArray data;

    static {
        try {
            data = (JSONArray) new JSONParser().parse(new FileReader("src/main/resources/vendingmachine/user.json"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    private String userName;
    private String userType;
    private Card savedCard;

    public User(String userName, String userType) {
        this.userName = userName;
        this.userType = userType;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserType() {
        return userType;
    }

    public Card getSavedCard() {
        return savedCard;
    }

    public void setSavedCard(Card savedCard) {
        this.savedCard = savedCard;
    }
}
