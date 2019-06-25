package com.github.martinfrank.catansettler.gui;

import com.github.martinfrank.catansettler.Game;
import com.github.martinfrank.catansettler.map.GameMap;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class RootController {

    private static final Logger LOGGER = LoggerFactory.getLogger(Game.class);
    private GuiEventListener eventListener;

    @FXML
    private GameMapCanvas mapCanvas;

    @FXML
    private TextArea console;


    public RootController(Game game) {
    }

    public void init() {
        LOGGER.debug("mapCanvas: {}", mapCanvas);
        mapCanvas.addEventFilter(MouseEvent.MOUSE_PRESSED, mouseEvent -> {
            int x = (int) mouseEvent.getX();
            int y = (int) mouseEvent.getY();
            MouseSelection selection = mapCanvas.getSelectionAt(x, y);
            eventListener.mouseSelect(selection);
        });
    }

    public void setMap(GameMap map) {
        mapCanvas.setMap(map);
    }

    public void setGuiEventListener(GuiEventListener eventListener) {
        this.eventListener = eventListener;
    }

    public void writeToConsole(String s) {
        console.appendText(s+"\n");
    }
}
