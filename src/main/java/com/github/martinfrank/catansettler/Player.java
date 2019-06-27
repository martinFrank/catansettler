package com.github.martinfrank.catansettler;

import com.github.martinfrank.boardgamelib.BasePlayer;
import com.github.martinfrank.catansettler.ai.MapAnalyzer;
import com.github.martinfrank.catansettler.gui.Console;
import com.github.martinfrank.catansettler.map.GameMapEdge;
import com.github.martinfrank.catansettler.map.GameMapNode;
import com.github.martinfrank.catansettler.model.City;
import com.github.martinfrank.catansettler.model.Road;
import com.github.martinfrank.catansettler.model.Settlement;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Player extends BasePlayer<Game>  {

    private Console console;
    private MapAnalyzer mapAnalyzer;

    private List<Settlement> inStockSettlements = new ArrayList<>();
    private List<Settlement> placedSettlements = new ArrayList<>();
    private List<City> inStockCities = new ArrayList<>();
    private List<City> placedCities = new ArrayList<>();
    private List<Road> inStockRoad = new ArrayList<>();
    private List<Road> placedRoad = new ArrayList<>();


    public Player(String name, int color, boolean isHuman) {
        super(name, color, isHuman);

    }

    void init(Console console) {
        this.console = console;
        mapAnalyzer = new MapAnalyzer(getBoardGame().getMap(), this, getBoardGame().getPlayers());
        IntStream.range(0, 4).forEach(i -> inStockCities.add(new City(this)));
        IntStream.range(0, 5).forEach(i -> inStockSettlements.add(new Settlement(this)));
        IntStream.range(0, 15).forEach(i -> inStockRoad.add(new Road(this)));
    }

    @Override
    public void performAiTurn() {
        console.writeToConsole("[" + getName() + "]: correct, it's my turn now");

        GamePhase gamePhase = getBoardGame().getGamePhase();
        if (gamePhase == GamePhase.SET_START || gamePhase == GamePhase.SET_REVERSE) {
            GameMapNode node = mapAnalyzer.getSettlementPlacingValues().get(0).get();
            Settlement settlement = inStockSettlements.remove(0);
            placedSettlements.add(settlement);
            getBoardGame().placeSettlement(settlement, node);

            GameMapEdge edge = mapAnalyzer.getRoadPlacingValues(node).get(0).get();
            Road road = inStockRoad.remove(0);
            placedRoad.add(road);
            getBoardGame().placeRoad(road, edge);
        }

        console.writeToConsole("[" + getName() + "]: ok, my turn is done....");
        getBoardGame().endPlayersTurn();
    }


    @Override
    public String toString() {
        return getName();
    }

    public List<Settlement> getPlacedSettlements() {
        return placedSettlements;
    }

    public List<City> getPlacedCities() {
        return placedCities;
    }

    public List<Road> getPlacedRoads() {
        return placedRoad;
    }
}
