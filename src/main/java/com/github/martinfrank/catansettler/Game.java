package com.github.martinfrank.catansettler;

import com.github.martinfrank.boardgamelib.BaseBoardGame;
import com.github.martinfrank.catansettler.gui.*;
import com.github.martinfrank.catansettler.gui.alert.AlertManager;
import com.github.martinfrank.catansettler.gui.alert.AlertResult;
import com.github.martinfrank.catansettler.map.GameMap;
import com.github.martinfrank.catansettler.map.GameMapGenerator;
import com.github.martinfrank.catansettler.map.MapUtil;
import com.github.martinfrank.catansettler.resource.ResourceManager;
import com.github.martinfrank.catansettler.resource.image.ResourceImages;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class Game extends BaseBoardGame<Player> implements GuiEventListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(Game.class);

    private GameMap map;
    private final AlertManager alertManager;
    private CardStackController cardStackController;
    private GamePhase gamePhase;

    private RootController rootController;

    Game(ResourceManager resourceManager) {
        gamePhase = GamePhase.NEW;
        alertManager = new AlertManager(resourceManager);
    }


    @Override
    public void initGame() {
        AlertResult<GameSetup> dialogResult = alertManager.showGameSetupDialog();
        if (dialogResult.wasConfirmed()) {
            setup(dialogResult.getResult());
            GameMapGenerator.generate(map);
            rootController.setMap(map);
        }
        rootController.writeToConsole("Willkommen zur nächsten Runde Siedler!");
        gamePhase = GamePhase.SET_START;
        rootController.writeToConsole("in der ersten Runde muss jeder Spieler eine Siedlung und eine angrenzende Strasse setzen");
        startPlayersTurn();
    }

    @Override
    public void endPlayersTurn() {

    }

    @Override
    public void startPlayersTurn() {
        rootController.writeToConsole("Spieler "+getCurrentPlayer().getName()+" ist dran. (Phase="+gamePhase+")");
        if (getCurrentPlayer().isAi()){
            getCurrentPlayer().performAiTurn();
        }
    }


    public void init(ResourceImages resourceImages) {
        map = MapUtil.createMap(resourceImages);
        GameMapGenerator.generate(map);
        rootController.init();
        rootController.setMap(map);
        rootController.setGuiEventListener(this);
        cardStackController.init(resourceImages);
    }

    @Override
    public void mouseSelect(MouseSelection selection) {
        LOGGER.debug("mouseSelect: {}", selection);
        if (selection.getField() != null) {
            LOGGER.debug("mousefield resource: {}", selection.getField().getData().getFieldResource());
        }
    }

    public void setCardStackController(CardStackController cardStackController) {
        this.cardStackController = cardStackController;
    }

    public void setRootController(RootController rootController) {
        this.rootController = rootController;
    }

    public void addCardSlotController(CardSlotController cardSlotController) {
        cardStackController.add(cardSlotController);
    }
}
