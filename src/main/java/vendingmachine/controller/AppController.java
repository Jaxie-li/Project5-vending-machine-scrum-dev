package vendingmachine.controller;

import org.json.simple.parser.ParseException;
import vendingmachine.model.VendingMachineModel;

import java.io.IOException;

/**
 * @version v1.0
 * @author: Katherine Xu
 * @date: Created in 5/10/2022 1:53 am
 * @description: This is the controller for the App class, and it will take the responsibility of GUI Action & Response
 */
public class AppController {
    private VendingMachineModel model = new VendingMachineModel("src/main/resources/vendingmachine/vending_machine_initial_state.json");


    public AppController() throws IOException, ParseException {

    }
}
