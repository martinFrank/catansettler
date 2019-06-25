package com.github.martinfrank.catansettler.gui;

import com.github.martinfrank.catansettler.Game;
import javafx.util.Callback;

public class ControllerFactory implements Callback<Class<?>, Object> {

    private final Game game;

    public ControllerFactory(Game game) {
        this.game = game;
    }

    @Override
    public Object call(Class<?> type) {
        if (type == RootController.class) {
            RootController rootController = new RootController(game);
            game.setRootController(rootController);
            return rootController;
        } else if (type == CardStackController.class) {
            CardStackController cardStackController = new CardStackController(game);
            game.setCardStackController(cardStackController);
            return cardStackController;
        }else if (type == MenuController.class) {
            return new MenuController(game);
        } else if (type == CardSlotController.class) {
            CardSlotController cardStackController = new CardSlotController(game);
            game.addCardSlotController(cardStackController);
            return cardStackController;
        }else {
            // default behavior for controllerFactory:
            try {
                return type.newInstance();
            } catch (Exception exc) {
                exc.printStackTrace();
                throw new RuntimeException(exc); // fatal, just bail...
            }
        }
    }

}
