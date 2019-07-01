package com.github.martinfrank.catansettler.workflow;

import com.github.martinfrank.catansettler.Game;
import com.github.martinfrank.catansettler.Player;
import com.github.martinfrank.catansettler.gui.MouseSelection;
import com.github.martinfrank.catansettler.map.GameMapNode;
import com.github.martinfrank.catansettler.model.Settlement;

public abstract class WorkFlow {

    private String workDescription;

    private final Game game;
    private final Player humanPlayer;
    private final String workflowName;

    public WorkFlow(Game game, Player humanPlayer, String workflowName) {
        this.game = game;
        this.humanPlayer = humanPlayer;
        this.workflowName = workflowName;
    }

    public Game getGame(){
        return game;
    }


    public Player getPlayer(){
        return humanPlayer;
    }
    public String getWorkflowName(){
        return workflowName;
    }

    public String getWorkDescription(){
        return workDescription == null?"":workDescription;
    }

    public abstract boolean isComplete();

    public void setWorkDescription(String workDescription){
        this.workDescription = workDescription;
    }

    public abstract boolean canAbort();

    public abstract void handleNode(GameMapNode node);
}
