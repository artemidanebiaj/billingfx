package org.billingfx;

import javafx.fxml.FXML;

import java.io.IOException;

public class TableActions {

    @FXML
    private void generateBills() throws IOException {
        App.setRoot("secondary");
    }

    @FXML
    private void addToList() throws IOException {
        System.out.println("switch to sec");
        App.setRoot("secondary");
    }

    @FXML
    private void updateId() throws IOException {
        System.out.println("switch to sec");
        App.setRoot("secondary");
    }

}
