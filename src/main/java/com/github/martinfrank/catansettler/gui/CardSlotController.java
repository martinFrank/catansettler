package com.github.martinfrank.catansettler.gui;

import com.github.martinfrank.catansettler.Game;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class CardSlotController {

    @FXML
    private Label name;

    @FXML
    private Label amount;

    @FXML
    private ImageView image;

    public CardSlotController(Game game) {
    }

    public void setup(String description, Image img, int amount){
        name.setText(description);
        image.setImage(img);
        setAmount(amount);
    }

    private void setAmount(int a) {
        amount.setText("Amount "+a);
    }

}
