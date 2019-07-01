package com.github.martinfrank.catansettler.workflow;

import com.github.martinfrank.catansettler.Game;
import com.github.martinfrank.catansettler.GamePhase;
import com.github.martinfrank.catansettler.Player;
import com.github.martinfrank.catansettler.map.GameMapGenerator;
import com.github.martinfrank.catansettler.map.GameMapNode;
import com.github.martinfrank.catansettler.model.Road;
import com.github.martinfrank.catansettler.model.Settlement;

public class SetStartWorkFlow extends WorkFlow {


    private Settlement startSettlement;
    private Road startRoad;

    public SetStartWorkFlow(Game game, Player humanPlayer) {
        super(game,humanPlayer, "Siedlung + Strasse setzen");
    }

    @Override
    public String getWorkDescription() {
        return "you have to complete 'set start'...";
    }

    @Override
    public boolean isComplete() {
        if (!checkVillageSize() ){
            return false;
        }
        return true;
    }


    private boolean checkVillageSize() {
        if (getGame().getCurrentPlayer().getPlacedSettlements().size() == 1 && getGame().getGamePhase()==GamePhase.SET_START){
            return true;
        }
        if (getGame().getCurrentPlayer().getPlacedSettlements().size() == 2 && getGame().getGamePhase()==GamePhase.SET_REVERSE){
            return true;
        }
        setWorkDescription("you must set a village!");
        return false;
    }

    public boolean canAbort(){
        return false;
    }

    @Override
    public void handleNode(GameMapNode node) {

        if(!isValidNode(node)){
            return;
        }

        if (startSettlement == null) {
            startSettlement = getPlayer().removeSettlementFromStock();
            startSettlement.setLocation(node);
        }
        startSettlement.getLocation().getData().clear();
        startSettlement.setLocation(node);
        node.getData().setSettlement(startSettlement);
    }

    private boolean isValidNode(GameMapNode node) {
        return GameMapGenerator.getResourceFields(getGame().getMap()).stream().anyMatch(f -> f.getNodes().contains(node));
    }


}
