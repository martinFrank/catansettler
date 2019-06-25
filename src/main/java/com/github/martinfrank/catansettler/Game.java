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

import java.util.Collections;
import java.util.stream.Collectors;

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
            getPlayers().forEach(p -> p.init(rootController));
        }
        rootController.writeToConsole("Willkommen zur nächsten Runde Siedler " + getPlayers().stream().map(Player::getName).collect(Collectors.joining(",")) + "!");
        gamePhase = GamePhase.SET_START;
        rootController.writeToConsole("in der ersten Runde muss jeder Spieler eine Siedlung und eine angrenzende Strasse setzen");

        startPlayersTurn();

    }

    @Override
    public void endPlayersTurn() {
        super.endPlayersTurn();
        if (!checkWinCondition()) {
            checkPhase();
            startPlayersTurn();
        }
    }

    private boolean checkWinCondition() {
        if (gamePhase == GamePhase.NORMAL) {
            gamePhase = GamePhase.FINISHED;
            return true;
        }
        return false;
    }

    private void checkPhase() {
        if (isLastPlayer() && gamePhase == GamePhase.SET_START) {
            rootController.writeToConsole("Last player has made set - let's switch to phase " + GamePhase.SET_REVERSE);
            Collections.reverse(getPlayers());
            gamePhase = GamePhase.SET_REVERSE;
            return;
        }
        if (isLastPlayer() && gamePhase == GamePhase.SET_REVERSE) {
            rootController.writeToConsole("Last player has made set (backwards)- let's switch to phase " + GamePhase.NORMAL);
            Collections.reverse(getPlayers());
            gamePhase = GamePhase.NORMAL;
        }
    }

    @Override
    public void startPlayersTurn() {
        super.startPlayersTurn();
        rootController.writeToConsole("Spieler " + getCurrentPlayer().getName() + " ist dran. (Phase=" + gamePhase + ")");
        if (getCurrentPlayer().isAi()) {
            getCurrentPlayer().performAiTurn();
        } else {
            //TEST - wird neu ab GUI gemacht!
            endPlayersTurn();
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
