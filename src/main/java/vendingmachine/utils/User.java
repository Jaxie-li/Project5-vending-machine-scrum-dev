package vendingmachine.utils;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
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
            data = (JSONArray) new JSONParser().parse(new FileReader("src/main/resources/vendingmachine/data/user.json"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static User isValidUser(String userName,String passWord){
        for(Object o : data){
            JSONObject each = (JSONObject) o;
            String realUserName = each.get("username").toString();
            String realPassWord = each.get("password").toString();

            if(realUserName.equals(userName) && realPassWord.equals(passWord)){
                return new User(each);
            }
        }
        return null;
    }

    private String userName;
    private String userType;
    private Card savedCard;

    public User(JSONObject data) {
        /*
        Parse the JSON into instance
         */
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
