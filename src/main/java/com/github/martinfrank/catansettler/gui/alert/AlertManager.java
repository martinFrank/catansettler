package com.github.martinfrank.catansettler.gui.alert;

import com.github.martinfrank.catansettler.GameSetup;
import com.github.martinfrank.catansettler.resource.ResourceManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

import java.io.IOException;
import java.util.function.Supplier;

public class AlertManager {

    private final ResourceManager resourceManager;

    public AlertManager(ResourceManager resourceManager) {
        this.resourceManager = resourceManager;
    }

    public AlertResult<GameSetup> showGameSetupDialog() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(resourceManager.getGuiSetupDialog());
            SetupDialogControllerFactory factory = new SetupDialogControllerFactory();
            fxmlLoader.setControllerFactory(factory);
            VBox root = fxmlLoader.load();

            GameSetupAlertController setupAlertController = factory.getSetupAlertController();
            setupAlertController.init();

            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Delete selection  ?", ButtonType.OK, ButtonType.CANCEL);
            setInputValidation(alert, ButtonType.OK, setupAlertController::isInputValid);

            alert.getDialogPane().setContent(root);
            alert.getDialogPane().setHeaderText("Auswahl Spieler");
            alert.setTitle("Spielerauswahl");

            alert.showAndWait();


            if (alert.getResult() == ButtonType.OK) {
                GameSetupAlertResult result = new GameSetupAlertResult();
                result.setGameSetup(setupAlertController.createGameSetup());
                return result;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        //empty (not confirmed) result
        return new GameSetupAlertResult();
    }

    private <T> void setInputValidation(Alert alert, ButtonType type, Supplier<Boolean> f) {
        alert.getDialogPane().lookupButton(type).addEventFilter(
                ActionEvent.ACTION,
                event -> {
                    if (!f.get()) {
                        event.consume();
                    }
                }
        );

    }

    private class SetupDialogControllerFactory implements Callback<Class<?>, Object> {

        private final GameSetupAlertController setupAlertController = new GameSetupAlertController();

        @Override
        public Object call(Class<?> type) {
            if (type == GameSetupAlertController.class) {
                return setupAlertController;
            } else if (type == PlayerSlotController.class) {
                PlayerSlotController playerSlotController = new PlayerSlotController();
                setupAlertController.addPlayerSlotController(playerSlotController);
                return playerSlotController;
            } else {
                // default behavior for controllerFactory:
                try {
                    return type.newInstance();
                } catch (Exception exc) {
                    exc.printStackTrace();
                    throw new RuntimeException(exc); // fatal, just bail...
                }
            }
        }

        private GameSetupAlertController getSetupAlertController() {
            return setupAlertController;
        }
    }
}
