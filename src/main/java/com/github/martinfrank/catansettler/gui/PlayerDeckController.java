package com.github.martinfrank.catansettler.gui;

import com.github.martinfrank.catansettler.Game;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class PlayerDeckController {

    private final Game game;
    @FXML
    private Button endRound;


    public PlayerDeckController(Game game) {
        this.game = game;
    }

    public void endRound(ActionEvent event) {
        game.endPlayersTurn();
    }
}
