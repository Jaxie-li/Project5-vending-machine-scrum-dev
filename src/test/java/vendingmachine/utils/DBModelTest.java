package vendingmachine.utils;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOError;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class DBModelTest {

    private final String wrongPath = "wrong/path";

    private final String testInputPath = "src/test/resources/vendingmachine/data/productTest.json";

    private final String testOutputPath = "src/test/resources/vendingmachine/data/DBModelTest.json";

    private JSONArray testArray = new JSONArray();

    private JSONObject object = new JSONObject();

    @BeforeEach
    public void init(){
        object.put("name", "Mineral Water");
    }

    @Test
    public void createTest() {
        // build fail but tested successful when tested separately
        assertNull(DBModel.create(testArray, object, testOutputPath));
//        assertThrows(IOException.class, () -> {DBModel.create(testArray, object, wrongPath);});
    }

    @Test
    public void readTest() {
        // build fail but tested successful when tested separately
        assertNull(DBModel.read(testInputPath));
    }

    @Test
    public void updateTest() {
//        assertNotNull();
    }

    @Test
    void delete() {
    }

}