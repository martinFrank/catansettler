package com.github.martinfrank.catansettler.ai;

import com.github.martinfrank.catansettler.Player;
import com.github.martinfrank.catansettler.map.*;
import com.github.martinfrank.catansettler.model.FieldResource;

import java.util.*;
import java.util.stream.Collectors;

public class MapAnalyzer {

    private final GameMap gameMap;
    private final Player player;
    private final List<Player> players;

    public MapAnalyzer(GameMap gameMap, Player player, List<Player> players) {
        this.gameMap = gameMap;
        this.player = player;
        this.players = players;
    }

    public List<SettlementPlacingValue> getSettlementPlacingValues() {
        List<GameMapField> allFields = GameMapGenerator.getResourceFields(gameMap);
        Set<GameMapNode> candidates = new HashSet<>();
        allFields.forEach(f -> candidates.addAll(f.getNodes()));
        Set<GameMapNode> occupied = getAlreadyOccupied();
        candidates.removeAll(occupied);
        List<SettlementPlacingValue> result = new ArrayList<>();
        candidates.forEach(n -> result.add(createSettlementPlacingValue(n)));
        Collections.sort(result);
        System.out.println("placing values list = "+result.size());
        return result;
    }

    private SettlementPlacingValue createSettlementPlacingValue(GameMapNode node) {
        return new SettlementPlacingValue(node, fieldValues(node.getFields()), getResources(node.getFields()));
    }

    private Set<GameMapNode> getAlreadyOccupied() {
        Set<GameMapNode> nodes = new HashSet<>();
        players.forEach(p -> p.getPlacedSettlements().forEach(s -> nodes.add(s.getLocation())));
        players.forEach(p -> p.getPlacedCities().forEach(s -> nodes.add(s.getLocation())));
        Set<GameMapNode> result = new HashSet<>(nodes);
        for (GameMapNode node: nodes){
            node.getEdges().forEach(e -> result.addAll(e.getNodes()));
            result.add(node);
        }
        return result;
    }

    private static int fieldValues(List<GameMapField> fields){
        return fields.stream().mapToInt(f -> MapAnalyzer.getValueForDice(f.getData().getFieldDice())).sum();
    }

    private static List<FieldResource> getResources(List<GameMapField> fields){
        return fields.stream().map(f->f.getData().getFieldResource()).collect(Collectors.toList());
    }

    public static int getValueForDice(int dice){
        switch (dice){
            case 2: return 1;
            case 3: return 2;
            case 4: return 3;
            case 5: return 4;
            case 6: return 5;
            case 8: return 5;
            case 9: return 4;
            case 10: return 3;
            case 11: return 2;
            case 12: return 1;
            default:return 0;

        }
    }

    public List<RoadPlacingValue> getRoadPlacingValues(GameMapNode node) {
        List<RoadPlacingValue> result = new ArrayList<>();
        for (GameMapEdge edge: node.getEdges()){
            int value = fieldValues(edge.getFields());
            List<FieldResource> resources = getResources(node.getFields());
            result.add(new RoadPlacingValue(edge, value, resources));
        }
        Collections.sort(result);
        return result;
    }
}
