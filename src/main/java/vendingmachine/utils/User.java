package vendingmachine.utils;

import exceptions.UserNameExistException;
import javafx.scene.control.Alert;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import vendingmachine.controller.AppController;

/**
 * @version v1.0
 * @author: Katherine Xu
 * @date: Created in 5/10/2022 2:41 am
 * @description: This is the ultils for the user
 */

public class User extends DBModel {
    private static JSONArray data;
    public static final String path = "src/main/resources/vendingmachine/data/user.json";
    private String username;
    private String userType;
    private String password;
    private Card savedCard;

    public static JSONArray getData() {
        return data;
    }

    public static void setData(JSONArray data) {
        User.data = data;
    }

    public JSONObject serialise() {
        JSONObject user = new JSONObject();
        user.put("username", this.username);
        user.put("password", this.password);
        user.put("userType", this.userType);

        if (this.savedCard != null) {
            user.put("savedCardAccount", this.savedCard.getName());
            user.put("savedCardNumber", this.savedCard.getNumber());
        } else {
            user.put("savedCardAccount", "");
            user.put("savedCardNumber", "");
        }

        return user;
    }

//    public void createUser(String username, String password, String userType) {
//        // if is owner
//        if (this.userType.equals("owner")) {
//        User u = new User(username, password, userType);
//        data = User.create(data, u.serialise(), path);
//        }
//    }
//
//    public void deleteUser(String username) {
//        // if is owner
//        if (this.userType.equals("owner")) {
//            for (Object o : data){
//                JSONObject each = (JSONObject) o;
//                String realUserName = each.get("username").toString();
//
//                if (realUserName.equals(username)) {
//                    data = User.delete(data, each, path, "username");
//                    return;
//                }
//            }
//        }
//    }

    public static User register(String username, String password) throws RuntimeException, UserNameExistException {

        for (Object o : data){
            JSONObject each = (JSONObject) o;
            String realUserName = each.get("username").toString();

            // if username exist throw RuntimeException
            if (realUserName.equals(username)) {
                throw new RuntimeException();
            }
        }
        // construct new user
        User u = new User(username, password, "customer");

        // create user in database
        User.create(read(User.path), u.serialise(), path);

        // return created user for login
        return u;
    }

    public static User register(String username, String password,String userType) throws RuntimeException, UserNameExistException {

        for (Object o : data){
            JSONObject each = (JSONObject) o;
            String realUserName = each.get("username").toString();

            // if username exist throw RuntimeException
            if (realUserName.equals(username)) {
                throw new RuntimeException();
            }
        }
        // construct new user
        User u = new User(username, password, userType);

        // create user in database
        User.create(read(User.path), u.serialise(), path);

        // return created user for login
        return u;
    }





    public static User isValidUser(String username, String password){
        for (Object o : User.data){
            JSONObject each = (JSONObject) o;
            String realUsername = each.get("username").toString();
            String realPassword = each.get("password").toString();

            if (realUsername.equals(username) && realPassword.equals(password)) {
                return new User(each);
            }
        }
        return null;
    }

    public User(JSONObject data) {
        /*
        Parse the JSON into instance
         */
        this.username = data.get("username").toString();
        this.userType = data.get("userType").toString();
        this.password = data.get("password").toString();
        this.savedCard = new Card(data.get("savedCardAccount").toString(), data.get("savedCardNumber").toString());
    }

    public User(String username, String password, String userType) {
        this.username = username;
        this.userType = userType;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getUserType() {
        return userType;
    }

    public String getPassword() {
        return password;
    }

    public Card getSavedCard() {
        return savedCard;
    }

    public void setSavedCard(Card savedCard) {
        this.savedCard = savedCard;
    }

    @Override
    public String toString() {
        return "User{" +
                "userType='" + userType + '\'' +
                ", savedCard=" + savedCard +
                '}';
    }
}
