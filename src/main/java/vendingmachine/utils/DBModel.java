package vendingmachine.utils;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.io.FileWriter;

public abstract class DBModel {



    public static JSONArray create(JSONArray arr, JSONObject obj, String path) {
        // append JSONObject to JSONArray
        arr.add(obj);

        // write new JSONArray to path
        try (FileWriter file = new FileWriter(path)) {
            file.write(arr.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return arr;
    }

    public static JSONArray read(String path) throws RuntimeException {
        try {
            return (JSONArray) new JSONParser().parse(new FileReader(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static JSONArray update(JSONArray arr, JSONObject obj, String path, String pk) throws RuntimeException {
        JSONArray temp = new JSONArray();
        for (Object o: arr) {
            JSONObject each = (JSONObject) o;
            if (obj.get(pk).toString().equals(each.get(pk).toString())) {
                temp.add(obj);
            } else {
                temp.add(each);
            }
        }

        // write new JSONArray to path
        try (FileWriter file = new FileWriter(path)) {
            file.write(temp.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return temp;
    }

    public static JSONArray delete(JSONArray arr, JSONObject obj, String path, String pk) {
        JSONArray temp = new JSONArray();
        for (Object o: arr) {
            JSONObject each = (JSONObject) o;
            if (obj.get(pk).toString().equals(each.get(pk).toString())) {
                assert true;
            } else {
                temp.add(each);
            }
        }

        // write new JSONArray to users.json
        try (FileWriter file = new FileWriter(path)) {
            file.write(temp.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return temp;
    }

    public abstract JSONObject serialise();
}