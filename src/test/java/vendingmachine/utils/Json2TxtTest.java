package vendingmachine.utils;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class Json2TxtTest {

    private String path = "src/test/resources/vendingmachine/data/Json2TxtTest.txt";
    private Json2Txt convertor;

    JSONArray jsonArray;
    JSONObject jsonObject1;
    JSONObject jsonObject2;

    @BeforeEach
    public void init(){
        this.convertor = new Json2Txt();
        this.jsonArray = new JSONArray();
        this.jsonObject1 = new JSONObject();
        this.jsonObject2 = new JSONObject();
        this.jsonObject1.put("name", "Mineral Water");
        jsonObject2.put("name", "Mineral Juice");
        jsonArray.add(jsonObject1);
        jsonArray.add(jsonObject2);
    }

    @Test
    public void generateTXTTest() throws IOException {
        convertor.generateTXT(this.path,this.jsonArray);
        File f = new File(this.path);
        Scanner sc = new Scanner(f);
        ArrayList<String> outcome = new ArrayList<>();
        while (sc.hasNextLine()){
            outcome.add(sc.nextLine());
        }
        assertNotNull(outcome.toString());
    }
}