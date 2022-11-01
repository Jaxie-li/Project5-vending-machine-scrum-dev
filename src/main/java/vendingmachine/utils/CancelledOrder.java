package vendingmachine.utils;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;


public class CancelledOrder extends DBModel {

    private static JSONArray data;

    public static final String cancelledOrderPath = "src/main/resources/vendingmachine/data/cancelled_transactions.json";

    public static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-YYYY HH:mm:ss");
    private int id;

    private String username;

    private String reason;

    private LocalDateTime time;

    public static JSONArray getData() {
        CancelledOrder.data = CancelledOrder.read(cancelledOrderPath);
        return data;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return this.username;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getReason() {
        return reason;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public int getNextId() {
        return CancelledOrder.getData().size() + 1;
    }

    /**
     * get all information of cancelled order
     * @return JSONObject of order
     */
    @Override
    public JSONObject serialise() {
        JSONObject order = new JSONObject();

        Date date = new Date();
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");

        order.put("id", this.id);
        order.put("username", this.username);
        order.put("reason", this.reason);
        order.put("time", dateFormat.format(date));

        return order;
    }

    /**
     * add the cancel order into cancelled_transactions.json file
     */
    public void addCancelOrder() {
        CancelledOrder.create(CancelledOrder.getData(), serialise(), cancelledOrderPath);
    }
}
