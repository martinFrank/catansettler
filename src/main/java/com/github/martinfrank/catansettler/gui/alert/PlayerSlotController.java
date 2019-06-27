package com.github.martinfrank.catansettler.gui.alert;

import com.github.martinfrank.catansettler.gui.PaintUtil;
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
        colorpicker.setValue(PaintUtil.fromRgb(col));
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

    int getColorRgb() {
        return PaintUtil.toRgb(colorpicker.getValue());
    }

}
