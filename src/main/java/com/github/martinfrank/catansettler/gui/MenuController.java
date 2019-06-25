package com.github.martinfrank.catansettler.gui;

import com.github.martinfrank.catansettler.Game;
import javafx.event.ActionEvent;
import javafx.scene.control.MenuItem;


public class MenuController {

    private final Game game;

    public MenuController(Game game) {
        this.game = game;
    }


    public void fileAction(ActionEvent event) {
        MenuItem item = (MenuItem) event.getSource();
        switch (item.getId()){
            case "newGame": {
                game.initGame();
                break;
            }
        }
        System.out.println("fileMenu action from: "+item.getId());

    }
}
