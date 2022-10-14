package vendingmachine.utils;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.io.FileWriter;

public abstract class DBModel {

    static JSONArray data;

    public static void create(JSONObject obj, String path) {
        // append JSONObject to JSONArray
        data.add(obj);

        // write new JSONArray to users.json
        try (FileWriter file = new FileWriter(path)) {
            file.write(data.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void read(String path) throws RuntimeException {
        try {
            data = (JSONArray) new JSONParser().parse(new FileReader(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static void update(JSONObject obj, String path, String pk) throws RuntimeException {
        JSONArray temp = new JSONArray();
        for (Object o: data) {
            JSONObject each = (JSONObject) o;
            if (obj.get(pk).toString().equals(each.get(pk).toString())) {
                temp.add(obj);
            } else {
                temp.add(each);
            }
        }
        data = temp;

        // write new JSONArray to users.json
        try (FileWriter file = new FileWriter(path)) {
            file.write(data.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void delete(JSONObject obj, String path, String pk) {
        JSONArray temp = new JSONArray();
        for (Object o: data) {
            JSONObject each = (JSONObject) o;
            if (obj.get(pk).toString().equals(each.get(pk).toString())) {
                assert true;
            } else {
                temp.add(each);
            }
        }
        data = temp;

        // write new JSONArray to users.json
        try (FileWriter file = new FileWriter(path)) {
            file.write(data.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public abstract JSONObject serialize();
}