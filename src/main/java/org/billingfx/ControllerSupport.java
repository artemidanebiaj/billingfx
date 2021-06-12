package org.billingfx;

import javafx.fxml.FXML;

import java.io.IOException;

public class ControllerSupport {

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("main");
    }
}