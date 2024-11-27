package unitconverter;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

public class FXMLDocumentController implements Initializable {
    String types[] = { "Length", "Temperature", "Weight" };
    
    String lengthUnits[] =      { "Centimeters", "Meters", "Kilometers", "Inches", "Feet", "Miles" };
    String temperatureUnits[] = { "Celsius", "Fahrenheit", "Kelvin" };
    String weightUnits[] =      { "Grams", "Kilograms", "Pounds" };
    
    @FXML
    private TextField fromUnitTF;
    @FXML
    private TextField toUnitTF;
    @FXML
    private ChoiceBox<String> fromUnitPickerCB;
    @FXML
    private ChoiceBox<String> toUnitPickerCB;
    @FXML
    private ChoiceBox<String> typePickerCB;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        typePickerCB.getItems().addAll(types);
        typePickerCB.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            change();
        });
        
        fromUnitPickerCB.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            update();
        });
        
        toUnitPickerCB.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            update();
        });
        
        fromUnitTF.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            update();
        });
    }
    
    private void change() {
        fromUnitPickerCB.getItems().clear();
        fromUnitTF.clear();
        toUnitPickerCB.getItems().clear();
        toUnitTF.clear();
        switch(typePickerCB.getValue()) {
            case "Length":
                fromUnitPickerCB.setItems(FXCollections.observableArrayList(lengthUnits));
                toUnitPickerCB.setItems(FXCollections.observableArrayList(lengthUnits));
                break;
            case "Temperature":
                fromUnitPickerCB.setItems(FXCollections.observableArrayList(temperatureUnits));
                toUnitPickerCB.setItems(FXCollections.observableArrayList(temperatureUnits));
                break;
            case "Weight":
                fromUnitPickerCB.setItems(FXCollections.observableArrayList(weightUnits));
                toUnitPickerCB.setItems(FXCollections.observableArrayList(weightUnits));
                break;
        }
    }
    
    private void update() {
        if(!(fromUnitPickerCB.getValue() == null || toUnitPickerCB.getValue() == null || fromUnitTF.getText().isEmpty()) && isNumeric(fromUnitTF.getText())) {
            convert();
        }
        if(fromUnitTF.getText().isEmpty() && !toUnitTF.getText().isEmpty()) toUnitTF.clear();
    }
    
    private void convert() {
        toUnitTF.clear();
        double value = Converter.convert(typePickerCB.getValue(), fromUnitPickerCB.getValue(), toUnitPickerCB.getValue(), Double.parseDouble(fromUnitTF.getText()));
        toUnitTF.setText(String.valueOf(value));
    }
    
    boolean isNumeric(String num) { //Helper method
        try {
            Double.valueOf(num);
            return true;
        } catch(NumberFormatException e) {
            return false;
        }
    }
    
}
