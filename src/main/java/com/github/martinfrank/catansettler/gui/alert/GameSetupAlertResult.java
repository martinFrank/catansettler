package com.github.martinfrank.catansettler.gui.alert;

import com.github.martinfrank.catansettler.GameSetup;

public class GameSetupAlertResult implements AlertResult<GameSetup> {


    private boolean wasConfirmed = false;
    private GameSetup gameSetup;

    void setGameSetup(GameSetup gameSetup){
        this.gameSetup = gameSetup;
        wasConfirmed = true;
    }

    @Override
    public boolean wasConfirmed() {
        return wasConfirmed;
    }

    @Override
    public GameSetup getResult() {
        return gameSetup;
    }
}
