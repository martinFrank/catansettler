package com.github.martinfrank.catansettler.map;

import com.github.martinfrank.catansettler.model.FieldResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

import static com.github.martinfrank.catansettler.model.FieldResource.*;

public class GameMapGenerator {

    private static final Logger LOGGER = LoggerFactory.getLogger(GameMapGenerator.class);
    private static  final List<FieldResource> FIELD_RESOURCES = Arrays.asList(
            GRAIN, GRAIN, GRAIN, GRAIN,
            SHEEP, SHEEP, SHEEP, SHEEP,
            WOOD, WOOD, WOOD, WOOD,
            ROCK, ROCK, ROCK,
            CLAY, CLAY, CLAY);

    private static  final List<Integer> FIELD_DIE = Arrays.asList(
            2, 3, 3, 4, 4, 5, 5 ,6, 6, 8, 8, 9, 9, 10, 10, 11, 11, 12);

    private GameMapGenerator() {

    }

    public static void generate(GameMap map) {
        double start = System.currentTimeMillis();

        map.clearMapData();

        generateFieldRessources(map);
        generateFieldDie(map);

        getWaterFields(map).forEach(f -> f.getData().setFieldResource(WATER));
        getCenterField(map).getData().setFieldResource(DESERT);

        double delta = System.currentTimeMillis() - start;
        LOGGER.debug("time to create map in Millis: {}", delta);
    }

    private static void generateFieldDie(GameMap map) {
        List<GameMapField> resourceFields = getResourceFields(map);
        do {
            setRandomDice(resourceFields);
        } while (!isDiceEvenDistributed(resourceFields));

    }

    private static void setRandomDice(List<GameMapField> resourceFields) {
        Collections.shuffle(FIELD_DIE);
        IntStream.range(0, resourceFields.size()).
                forEach(i -> resourceFields.get(i).getData().setFieldDice(FIELD_DIE.get(i)));
    }

    private static boolean isDiceEvenDistributed(List<GameMapField> resourceFields) {
        for (GameMapField field: resourceFields){
            int center = field.getData().getFieldDice();
            for (GameMapField nbg: field.getFields()){
                if (!isValidDiceCombination(center, nbg.getData().getFieldDice())){
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean isValidDiceCombination(int candidate, int center) {
        if (center == 6 && candidate == 8){
            return false;
        }
        if (center == 6 && candidate == 6) {
            return false;
        }
        if (center == 8 && candidate == 6){
            return false;
        }
        if (center == 8 && candidate == 8) {
            return false;
        }
        if (center == 12 && candidate == 2){
            return false;
        }
        if (center == 2 && candidate == 12){
            return false;
        }
        return true;
    }

    private static void generateFieldRessources(GameMap map) {
        List<GameMapField> resourceFields = getResourceFields(map);
        do {
            setRandomFields(resourceFields);
        } while (!isResourceEvenDistributed(resourceFields));


    }

    private static boolean isResourceEvenDistributed(List<GameMapField> resourceFields) {
        for (GameMapField field: resourceFields){
            FieldResource center = field.getData().getFieldResource();
            for (GameMapField nbg: field.getFields()){
                if (nbg.getData().getFieldResource() == center){
                    return false;
                }
            }
        }
        return true;
    }

    private static void setRandomFields(List<GameMapField> resourceFields) {
        Collections.shuffle(FIELD_RESOURCES);
        IntStream.range(0, resourceFields.size()).
                forEach(i -> resourceFields.get(i).getData().setFieldResource(FIELD_RESOURCES.get(i)));

    }

    public static List<GameMapField> getResourceFields(GameMap map) {
        List<GameMapField> list = new ArrayList<>();
        list.add(map.getField(1, 2));
        list.add(map.getField(1, 3));
        list.add(map.getField(1, 4));

        list.add(map.getField(2, 2));
        list.add(map.getField(2, 3));
        list.add(map.getField(2, 4));
        list.add(map.getField(2, 5));

        list.add(map.getField(3, 1));
        list.add(map.getField(3, 2));

        list.add(map.getField(3, 4));
        list.add(map.getField(3, 5));

        list.add(map.getField(4, 2));
        list.add(map.getField(4, 3));
        list.add(map.getField(4, 4));
        list.add(map.getField(4, 5));

        list.add(map.getField(5, 2));
        list.add(map.getField(5, 3));
        list.add(map.getField(5, 4));
        return list;
    }

    private static List<GameMapField> getWaterFields(GameMap map) {
        List<GameMapField> list = new ArrayList<>();
        list.add(map.getField(0, 2));
        list.add(map.getField(0, 3));
        list.add(map.getField(0, 4));
        list.add(map.getField(0, 5));

        list.add(map.getField(1, 1));
        list.add(map.getField(1, 5));

        list.add(map.getField(2, 1));
        list.add(map.getField(2, 6));

        list.add(map.getField(3, 0));
        list.add(map.getField(3, 6));

        list.add(map.getField(4, 1));
        list.add(map.getField(4, 6));

        list.add(map.getField(5, 1));
        list.add(map.getField(5, 5));

        list.add(map.getField(6, 2));
        list.add(map.getField(6, 3));
        list.add(map.getField(6, 4));
        list.add(map.getField(6, 5));
        return list;
    }

    private static GameMapField getCenterField(GameMap map) {
        return map.getField(3, 3);
    }

}
