package com.github.martinfrank.catansettler;

import com.github.martinfrank.boardgamelib.BasePlayer;
import com.github.martinfrank.catansettler.gui.Console;
import com.github.martinfrank.cli.CommandList;
import com.github.martinfrank.cli.DefaultCommandList;

public class Player extends BasePlayer<Game>  {

    private CatanSettlerTurn currentTurn;
    private boolean isTradeOfferOpen;
    private CatanSettlerCardStack cardsOnHand;
    private Console console;

    public Player(String name, int color, boolean isHuman) {
        super(name, color, isHuman);
    }

    public void init(Console console) {
        this.console = console;
    }

    @Override
    public void performAiTurn() {
        console.writeToConsole("[" + getName() + "]: correct, it's my turn now");


        console.writeToConsole("[" + getName() + "]: ok, my turn is done....");
        getBoardGame().endPlayersTurn();
    }

    public int getScore(){
        return 0;
    }

    public void resetTurn() {
        this.currentTurn = new CatanSettlerTurn();
    }

    public CatanSettlerTurn getCurrentTurn(){
        return currentTurn;
    }

    public CommandList getCommands() {
        DefaultCommandList commandList = new DefaultCommandList();

        if(isActionBlocked() ){

        }else{
//            if(canBuySettlement() ){
//                commandList.add(new BuildSettlementCommand(getBoardGame()));
//            }
//            if(canBuyCity() ){
//                commandList.add(new BuildCityCommand(getBoardGame()));
//            }
//            if(canBuyRoad()){
//                commandList.add(new BuildRoadCommand(getBoardGame()));
//            }
//            if(canBuyAdvancementCard() ){
//                commandList.add(new BuyAdvancementCardCommad(getBoardGame()));
//            }
//            if(canPlayAdvancementCard()) {
//                commandList.add(new PlayAdvancementCards(getBoardGame()));
//            }
//            commandList.add(new OpenTradeOfferCommand(getBoardGame()));
        }
        return commandList;
    }

    private boolean isActionBlocked() {
        if (! currentTurn.haveDieBeenRolled()){
            return false;
        }
        if (! currentTurn.hasRobberBeenSet()){
            return false;
        }

        if(isTradeOfferOpen){
            return false;
        }


        return true;
    }

    @Override
    public String toString() {
        return getName();
    }
}
