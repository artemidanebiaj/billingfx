package org.billingfx.controllers;

import javafx.fxml.FXML;

import java.io.IOException;

public class ControllerSupport {

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("main");
    }
}