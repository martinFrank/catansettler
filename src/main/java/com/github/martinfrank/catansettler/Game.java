package com.github.martinfrank.catansettler;

import com.github.martinfrank.boardgamelib.BaseBoardGame;
import com.github.martinfrank.boardgamelib.BasePlayer;
import com.github.martinfrank.catansettler.gui.CardSlotController;
import com.github.martinfrank.catansettler.gui.CardStackController;
import com.github.martinfrank.catansettler.gui.PlayerDeckController;
import com.github.martinfrank.catansettler.gui.RootController;
import com.github.martinfrank.catansettler.gui.alert.AlertManager;
import com.github.martinfrank.catansettler.gui.alert.AlertResult;
import com.github.martinfrank.catansettler.map.GameMap;
import com.github.martinfrank.catansettler.map.GameMapEdge;
import com.github.martinfrank.catansettler.map.GameMapGenerator;
import com.github.martinfrank.catansettler.map.MapUtil;
import com.github.martinfrank.catansettler.model.Road;
import com.github.martinfrank.catansettler.resource.ResourceManager;
import com.github.martinfrank.catansettler.resource.image.ResourceImages;
import com.github.martinfrank.catansettler.workflow.SetStartWorkFlow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.stream.Collectors;

public class Game extends BaseBoardGame<Player> {//implements GuiEventListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(Game.class);

    private GameMap map;
    private final AlertManager alertManager;
    private CardStackController cardStackController;
    private GamePhase gamePhase;
    private RootController rootController;
    private PlayerDeckController playerDeckController;


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
        if (playerDeckController.hasCurrentWorkFlow()) {
            return;
        }
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
            } else {
                if (getGamePhase() == GamePhase.SET_START) {
                    playerDeckController.setWorkflow(new SetStartWorkFlow(this, getHumanPlayer()));
                }
            }
        }
    }


    public void init(ResourceImages resourceImages) {
        map = MapUtil.createMap(resourceImages);
        GameMapGenerator.generate(map);
        rootController.init();
        rootController.setMap(map);
        rootController.setGuiEventListener(playerDeckController);
        cardStackController.init(resourceImages);
        playerDeckController.setConsole(rootController);
        playerDeckController.setPlayer(getHumanPlayer());
    }


    public void setCardStackController(CardStackController cardStackController) {
        this.cardStackController = cardStackController;
    }

    public void setRootController(RootController rootController) {
        this.rootController = rootController;
    }

    public void setPlayerDeckController(PlayerDeckController playerDeckController) {
        this.playerDeckController = playerDeckController;
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


    public void redrawMap() {
        rootController.redrawMap();
    }

    public void placeRoad(Road road, GameMapEdge edge) {
        road.setLocation(edge);
        edge.getData().setRoad(road);
        rootController.redrawMap();
    }

    public boolean isInGame() {
        return (gamePhase == GamePhase.NORMAL ||
                gamePhase == GamePhase.SET_START ||
                gamePhase == GamePhase.SET_REVERSE);
    }

    private Player getHumanPlayer() {
        if (getPlayers() != null && getPlayers().size() > 0) {
            return getPlayers().stream().filter(BasePlayer::isHuman).findAny().orElse(null);
        }
        return null;
    }


}
