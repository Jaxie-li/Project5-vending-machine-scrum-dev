package vendingmachine.utils;

import org.json.simple.JSONArray;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Json2Txt {

    /**
     * JsonArray to txt file
     * @param path txt file path
     * @param array Jsonarray which is put into txt file
     * @throws IOException throws IOExceptions
     */
    public void generateTXT(String path, JSONArray array) throws IOException {

        File writeName = new File(path);
        writeName.createNewFile();
        BufferedWriter out = new BufferedWriter(new FileWriter(writeName));

        for (Object cancelledOrder : array) {
            String eachOrder = cancelledOrder.toString() + "\r\n";
            out.write(eachOrder);
        }
        out.flush();
        out.close();
    }
}
