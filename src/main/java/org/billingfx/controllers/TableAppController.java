package org.billingfx.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.util.converter.DefaultStringConverter;
import org.billingfx.models.Product;
import org.billingfx.models.ProductTableData;
import org.billingfx.utils.EditCell;
import org.billingfx.utils.MyDoubleStringConverter;

import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class TableAppController implements Initializable {

    @FXML
    private TableView<ProductTableData> table;

    @FXML
    private TextField idTextField;

    @FXML
    private TextField quantityTextField;

    @FXML
    private TextField priceTextField;

    @FXML
    private TextField grandTotal;

    private Double total = 0.0;

    @FXML
    private Button submitButton;

    private ObservableList<ProductTableData> data = FXCollections
            .observableArrayList();


    @FXML
    private TableColumn<ProductTableData, String> id;

    @FXML
    private TableColumn<ProductTableData, Double> quantity;

    @FXML
    private TableColumn<ProductTableData, Double> price;

    @Override
    public void initialize(final URL url, final ResourceBundle rb) {
        table.setItems(data);
        populate(retrieveData());
        setupIdColumn();
        setupQuantityColumn();
        setupPriceColumn();
        setTableEditable();
        grandTotal.setText("0");

    }

    private List<Product> retrieveData() {
        return Arrays.asList(
                new Product("0", 0.0,
                        0.0));
    }

    private void populate(final List<Product> products) {
        products.forEach(p -> data.add(new ProductTableData(p)));
    }

    private void setupIdColumn() {
        //make it editable
        id.setCellFactory(
                EditCell.<ProductTableData, String>forTableColumn(new DefaultStringConverter()));
        // committed value
        id.setOnEditCommit(event -> {
            final String value = event.getNewValue() != null ? event.getNewValue() :
                    event.getOldValue();
            ((ProductTableData) event.getTableView().getItems()
                    .get(event.getTablePosition().getRow()))
                    .setId(value);
            System.out.println("updated" + value);
            table.refresh();
        });
    }

    private void setupQuantityColumn() {
        //make it editable
        quantity.setCellFactory(
                EditCell.<ProductTableData, Double>forTableColumn(
                        new MyDoubleStringConverter()));

        // updates the salary field on the PersonTableData object to the
        // committed value
        quantity.setOnEditCommit(event -> {
            final Double value = event.getNewValue() != null ?
                    event.getNewValue() : event.getOldValue();
            ((ProductTableData) event.getTableView().getItems()
                    .get(event.getTablePosition().getRow())).setQuantity(value);
            table.refresh();
        });
    }

    private void setupPriceColumn() {
        //make it editable
        price.setCellFactory(
                EditCell.<ProductTableData, Double>forTableColumn(
                        new MyDoubleStringConverter()));

        // updates the salary field on the PersonTableData object to the
        // committed value
        price.setOnEditCommit(event -> {
            final Double value = event.getNewValue() != null ?
                    event.getNewValue() : event.getOldValue();
            ((ProductTableData) event.getTableView().getItems()
                    .get(event.getTablePosition().getRow())).setPrice(value);
            table.refresh();
        });
    }

    private void setTableEditable() {
        table.setEditable(true);
        // allows the individual cells to be selected
        table.getSelectionModel().cellSelectionEnabledProperty().set(true);
        // when character or numbers pressed it will start edit in editable
        // fields
        table.setOnKeyPressed(event -> {
            if (event.getCode().isLetterKey() || event.getCode().isDigitKey()) {
                editFocusedCell();
            } else if (event.getCode() == KeyCode.RIGHT ||
                    event.getCode() == KeyCode.TAB) {
                table.getSelectionModel().selectNext();
                event.consume();
            } else if (event.getCode() == KeyCode.LEFT) {
                // work around due to
                // TableView.getSelectionModel().selectPrevious() due to a bug
                // stopping it from working on
                // the first column in the last row of the table
                selectPrevious();
                event.consume();
            }
            calculateTotal();
        });

    }

    @SuppressWarnings("unchecked")
    private void editFocusedCell() {
        final TablePosition<ProductTableData, ?> focusedCell = table
                .focusModelProperty().get().focusedCellProperty().get();
        table.edit(focusedCell.getRow(), focusedCell.getTableColumn());

    }

    @SuppressWarnings("unchecked")
    private void selectPrevious() {
        if (table.getSelectionModel().isCellSelectionEnabled()) {
            // in cell selection mode, we have to wrap around, going from
            // right-to-left, and then wrapping to the end of the previous line
            TablePosition<ProductTableData, ?> pos = table.getFocusModel()
                    .getFocusedCell();
            if (pos.getColumn() - 1 >= 0) {
                // go to previous row
                table.getSelectionModel().select(pos.getRow(),
                        getTableColumn(pos.getTableColumn(), -1));
            } else if (pos.getRow() < table.getItems().size()) {
                // wrap to end of previous row
                table.getSelectionModel().select(pos.getRow() - 1,
                        table.getVisibleLeafColumn(
                                table.getVisibleLeafColumns().size() - 1));
            }
        } else {
            int focusIndex = table.getFocusModel().getFocusedIndex();
            if (focusIndex == -1) {
                table.getSelectionModel().select(table.getItems().size() - 1);
            } else if (focusIndex > 0) {
                table.getSelectionModel().select(focusIndex - 1);
            }
        }
    }

    private TableColumn<ProductTableData, ?> getTableColumn(
            final TableColumn<ProductTableData, ?> column, int offset) {
        int columnIndex = table.getVisibleLeafIndex(column);
        int newColumnIndex = columnIndex + offset;
        return table.getVisibleLeafColumn(newColumnIndex);

    }

    @FXML
    private void submit(final ActionEvent event) {
        if (allFieldsValid()) {
            final String id = idTextField.getText();
            final Double quantity = Double.parseDouble(quantityTextField.getText());
            final Double price = Double.parseDouble(priceTextField.getText());
            data.add(new ProductTableData(id, quantity, price));
            calculateTotal();
        }
    }

    private boolean allFieldsValid() {
        return !idTextField.getText().isEmpty() &&
                !quantityTextField.getText().isEmpty() &&
                !priceTextField.getText().isEmpty();
    }

    @FXML
    private void calculateTotal() {
        total = 0.0;
        for(int i = 0; i < data.size(); i++){
            total += (data.get(i).getPrice() * data.get(i).getQuantity());
        }
        grandTotal.setText(total.toString());
    }


}