package com.github.martinfrank.catansettler;

import com.github.martinfrank.boardgamelib.BaseBoardGame;
import com.github.martinfrank.catansettler.gui.*;
import com.github.martinfrank.catansettler.gui.alert.AlertManager;
import com.github.martinfrank.catansettler.gui.alert.AlertResult;
import com.github.martinfrank.catansettler.map.*;
import com.github.martinfrank.catansettler.model.Road;
import com.github.martinfrank.catansettler.model.Settlement;
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
            rootController.clearConsole();
            rootController.writeToConsole("Willkommen zur nächsten Runde Siedler " + getPlayers().stream().map(Player::getName).collect(Collectors.joining(",")) + "!");
            gamePhase = GamePhase.SET_START;
            rootController.writeToConsole("in der ersten Runde muss jeder Spieler eine Siedlung und eine angrenzende Strasse setzen");

            startPlayersTurn();
        }
    }

    @Override
    public void endPlayersTurn() {
        if (gamePhase.isInGame()) {
            if (!checkWinCondition()) {
                checkPhase();
                super.endPlayersTurn();
                startPlayersTurn();
            }
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
        if (gamePhase.isInGame()) {
            super.startPlayersTurn();
            rootController.writeToConsole("Spieler " + getCurrentPlayer().getName() + " ist dran. (Phase=" + gamePhase + ")");
            if (getCurrentPlayer().isAi()) {
                getCurrentPlayer().performAiTurn();
            }
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

    public GamePhase getGamePhase() {
        return gamePhase;
    }

    public GameMap getMap() {
        return map;
    }


    //ingame methods
    public void placeSettlement(Settlement settlement, GameMapNode node) {
        settlement.setLocation(node);
        node.getData().setSettlement(settlement);
        rootController.redrawMap();
    }

    public void placeRoad(Road road, GameMapEdge edge) {
        road.setLocation(edge);
        edge.getData().setRoad(road);
        rootController.redrawMap();
    }
}
