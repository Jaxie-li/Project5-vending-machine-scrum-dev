package vendingmachine.utils;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * @version v1.0
 * @author: Katherine Xu
 * @date: Created in 5/10/2022 1:56 am
 * @description: This is used for card stuff
 */
public class Card {

    private static JSONArray data;
    private String name;
    private String number;

    public Card(String name, String number) {
        this.name = name;
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }

    static {
        try {
            data = (JSONArray)new JSONParser().parse(new FileReader("src/main/resources/vendingmachine/data/credit_cards.json"));
        } catch (ParseException | FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean checkCreditCardValid(String name,String number){
        for(Object each: data){
            JSONObject obj = (JSONObject)each;
            if(obj.get("name").toString().equals(name) && obj.get("number").toString().equals(number)){
                return true;
            }
        }
        return false;
    }

    public static boolean checkCreditCardValid(String name,String number,User currentUser){
        for(Object each: data){
            JSONObject obj = (JSONObject)each;
            if(obj.get("name").toString().equals(name) && obj.get("number").toString().equals(number)){
                currentUser.setSavedCard(new Card(name,number));
                return true;
            }
        }
        return false;
    }





}
