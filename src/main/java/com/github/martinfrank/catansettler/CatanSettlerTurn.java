package com.github.martinfrank.catansettler;

import java.util.Random;

public class CatanSettlerTurn {

    private final Random random;
    private int firstDice;
    private int secondDice;
    private int dieSum;
    private boolean hasBeenRolled = false;
    private boolean hasRobberBeenSet = false;

    public CatanSettlerTurn(Random random){
        this.random = random;
    }

    public CatanSettlerTurn(){
        this(new Random());
    }


    public boolean haveDieBeenRolled(){
        return hasBeenRolled;
    }

    public boolean hasRobberBeenSet(){
        return hasRobberBeenSet;
    }

    public void rollDie(){
        firstDice = 1 + random.nextInt(6);
        secondDice = 1 + random.nextInt(6);
        hasBeenRolled = true;
        dieSum = firstDice+secondDice;
        if(dieSum != 7){
            hasRobberBeenSet = true;
        }
    }

    public void setRobber(){
        hasRobberBeenSet = true;
    }

}
