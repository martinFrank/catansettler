package com.github.martinfrank.catansettler;

import com.github.martinfrank.boardgamelib.BoardGameSetup;

import java.util.ArrayList;
import java.util.List;

public class GameSetup implements BoardGameSetup<Player> {

    @Override
    public List<Player> getPlayers() {
        ArrayList<Player> players = new ArrayList<>();
        players.add(new Player("YOU", 0xFFFF00, true));
//        players.add(new Player("CPU", 0x0000FF, false));
        players.add(new Player("TST", 0x0000FF, true));
        return players;
    }

    @Override
    public int getMaximumRounds() {
        return 0;
    }
}
