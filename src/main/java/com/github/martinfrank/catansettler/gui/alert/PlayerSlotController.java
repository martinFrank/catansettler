package com.github.martinfrank.catansettler.gui.alert;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

public class PlayerSlotController {

    @FXML
    private TextField nametext;

    @FXML
    private ColorPicker colorpicker;

    @FXML
    private CheckBox participantCheckBox;

    @FXML
    CheckBox aiCheckBox;

    void setColor(int col) {
        colorpicker.setValue(fromRgb(col));
    }

    void setName(String name) {
        nametext.setText(name);
    }

    String getName() {
        return nametext.getText();
    }

    boolean isParticipant() {
        return participantCheckBox.isSelected();
    }

    Color getColor() {
        return colorpicker.getValue();
    }

    boolean isAi() {
        return aiCheckBox.isSelected();
    }

    public int getColorRgb() {
        return toRgb(colorpicker.getValue());
    }

    private static Color fromRgb(int col) {
        int r = (0xFF0000 & col) >>16;
        int g = (0xFF00 & col) >>8;
        int b = (0xFF & col) ;
        return Color.rgb(r,g,b);
    }

    private static int toRgb(Color col) {
        int r = (int)col.getRed() << 16;
        int g = (int)col.getGreen() << 8;
        int b = (int)col.getBlue();
        return r & g & b;
    }
}
