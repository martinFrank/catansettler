package com.github.martinfrank.catansettler;

public enum GamePhase {

    NEW, SET_START, SET_REVERSE, NORMAL, FINISHED;

    public boolean isInGame() {
        switch (this) {
            case SET_START: return true;
            case SET_REVERSE: return true;
            case NORMAL: return true;
            default: return false;
        }
    }
}
