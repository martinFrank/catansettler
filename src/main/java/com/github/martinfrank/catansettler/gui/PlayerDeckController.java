package com.github.martinfrank.catansettler.gui;

import com.github.martinfrank.catansettler.Game;
import com.github.martinfrank.catansettler.Player;
import com.github.martinfrank.catansettler.workflow.WorkFlow;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PlayerDeckController implements GuiEventListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(PlayerDeckController.class);

    private final Game game;

    private Player humanPlayer;

    @FXML
    private Label actionName;

    private WorkFlow currentWorkFlow;

    private Console console;


    public PlayerDeckController(Game game) {
        this.game = game;
    }

    public void endRound(ActionEvent event) {
        if (game.isInGame()) {
            if (hasCurrentWorkFlow()) {
                console.writeToConsole(getCurrentWorkFlow().getWorkDescription());
            } else {
                game.endPlayersTurn();
            }
        }
    }

    public WorkFlow getCurrentWorkFlow() {
        return currentWorkFlow;
    }

    public boolean hasCurrentWorkFlow() {
        return currentWorkFlow != null;
    }

    public void setWorkflow(WorkFlow workFlow) {
        currentWorkFlow = workFlow;
        actionName.setText(currentWorkFlow.getWorkflowName());
    }

    public void setConsole(Console console) {
        this.console = console;
    }

    public void confirmWorkflow(ActionEvent event) {
        if (hasCurrentWorkFlow()) {
//            if (currentWorkFlow.isComplete() ){
            //abräumen
            currentWorkFlow = null;
//            }
        }
    }

    public void abortWorkflow(ActionEvent event) {
        if (hasCurrentWorkFlow()) {
            if (currentWorkFlow.canAbort()) {
                //TODO: abräumen
                currentWorkFlow = null;
            } else {
                console.writeToConsole(currentWorkFlow.getWorkDescription());
            }
        }
    }

    @Override
    public void mouseSelect(MouseSelection selection) {
        LOGGER.debug("mouseSelect: {}", selection);
        if (hasCurrentWorkFlow()) {
            if (selection.hasNode()) {
                currentWorkFlow.handleNode(selection.getNode());
                game.redrawMap();
            }

        }
    }

    public void setPlayer(Player humanPlayer) {
        this.humanPlayer = humanPlayer;
    }
}
