package com.github.martinfrank.catansettler.gui;

import com.github.martinfrank.catansettler.Game;
import com.github.martinfrank.catansettler.model.CardResource;
import com.github.martinfrank.catansettler.resource.image.ResourceImages;

import java.util.ArrayList;
import java.util.List;

public class CardStackController {

    private final List<CardSlotController> cardSlotControllers = new ArrayList<>();


    public CardStackController(Game game) {

    }

    public void init(ResourceImages resourceImages) {
        getCardSlotController(CardResource.CLAY).setup("CLAY", resourceImages.getCardImage(CardResource.CLAY), 1);
        getCardSlotController(CardResource.GRAIN).setup("GRAIN", resourceImages.getCardImage(CardResource.GRAIN), 1);
        getCardSlotController(CardResource.ROCK).setup("ROCK", resourceImages.getCardImage(CardResource.ROCK), 1);
        getCardSlotController(CardResource.SHEEP).setup("SHEEP", resourceImages.getCardImage(CardResource.SHEEP), 1);
        getCardSlotController(CardResource.WOOD).setup("WOOD", resourceImages.getCardImage(CardResource.WOOD), 1);
        getCardSlotController(CardResource.PROGRESS).setup("PROGRESS", resourceImages.getCardImage(CardResource.WOOD), 1);
    }

    private CardSlotController getCardSlotController(CardResource resource) {
        switch (resource){
            case CLAY: return cardSlotControllers.get(0);
            case GRAIN: return cardSlotControllers.get(1);
            case ROCK: return cardSlotControllers.get(2);
            case SHEEP: return cardSlotControllers.get(3);
            case WOOD: return cardSlotControllers.get(4);
            case PROGRESS: return cardSlotControllers.get(5);
        }
        throw new IllegalArgumentException("resource "+resource +" is not a valid card stack");
    }

    public void add(CardSlotController cardSlotController) {
        cardSlotControllers.add(cardSlotController);


    }
}
