package vendingmachine.utils;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import vendingmachine.utils.DBModel;

/**
 * @version v1.0
 * @author: Katherine Xu
 * @date: Created in 5/10/2022 2:41 am
 * @description: This is the ultils for the user
 */
public class User extends DBModel {


    private static JSONArray data;
    public static final String path = "src/main/resources/vendingmachine/data/user.json";
    private String userName;
    private String userType;
    private String passWord;
    private Card savedCard;

    public static JSONArray getData() {
        return data;
    }

    public static void setData(JSONArray data) {
        User.data = data;
    }

    public JSONObject serialize() {
        JSONObject user = new JSONObject();
        user.put("username", this.userName);
        user.put("password", this.passWord);
        user.put("user_type", this.userType);

        if (this.savedCard != null) {
            user.put("saved_card_account", this.savedCard.getName());
            user.put("saved_card_number", this.savedCard.getNumber());
        } else {
            user.put("saved_card_account", "");
            user.put("saved_card_number", "");
        }

        return user;
    }

    public void create_user(String userName,String passWord, String userType) {
        // if this is owner
        // if is owner
        if (this.userType.equals("owner")) {
            User u = new User(userName, passWord, userType);
            data = User.create(data, u.serialize(), path);
        }
    }

    public void delete_user(String userName) {
        // if is owner
        if (this.userType.equals("owner")) {
            for (Object o : data){
                JSONObject each = (JSONObject) o;
                String realUserName = each.get("username").toString();

                if (realUserName.equals(userName)) {
                    data = User.delete(data, each, path, "username");
                    return;
                }
            }

        }

    }

    public static User register(String userName,String passWord) throws RuntimeException {

        for (Object o : data){
            JSONObject each = (JSONObject) o;
            String realUserName = each.get("username").toString();

            // if userName exist throw UserNameExistExceotion
            if (realUserName.equals(userName)) {

            }
        }
        // construct new user
        User u = new User(userName, passWord, "customer");

        // create user in database
        data = User.create(data, u.serialize(), path);

        // return created user for login
        return u;
    }

    public static User isValidUser(String userName, String passWord){
        System.out.println(User.data);
        for (Object o : User.data){
            JSONObject each = (JSONObject) o;
            String realUserName = each.get("username").toString();
            String realPassWord = each.get("password").toString();

            if (realUserName.equals(userName) && realPassWord.equals(passWord)) {
                return new User(each);
            }
        }
        return null;
    }

    public User(JSONObject data) {
        /*
        Parse the JSON into instance
         */
        this.userName = data.get("username").toString();
        this.userType = data.get("user_type").toString();
        this.passWord = data.get("password").toString();
        this.savedCard = new Card(data.get("saved_card_account").toString(), data.get("saved_card_number").toString());
    }

    public User(String userName, String passWord, String userType) {
        this.userName = userName;
        this.userType = passWord;
        this.passWord = userType;
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
