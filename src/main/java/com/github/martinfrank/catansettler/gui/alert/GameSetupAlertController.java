package com.github.martinfrank.catansettler.gui.alert;

import com.github.martinfrank.catansettler.GameSetup;
import com.github.martinfrank.catansettler.Player;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.paint.Color;

import java.util.*;

public class GameSetupAlertController {

    private final Random random = new Random();
    private final List<String> names = Arrays.asList("Hans", "Norbert", "Josef", "Karl", "Alex", "Heinrich", "Oliver", "David");

    @FXML
    private TextArea infotext;

    private List<PlayerSlotController> playerSlotControllers = new ArrayList<>();

    void init() {
        List<String> namesCopy = new ArrayList<>(names);
        Collections.shuffle(namesCopy);
        for (PlayerSlotController playerSlotController : playerSlotControllers) {
            playerSlotController.setColor(random.nextInt(0xFFFFFF));
            playerSlotController.setName(namesCopy.remove(0));
        }
    }

    void addPlayerSlotController(PlayerSlotController playerSlotController) {
        playerSlotControllers.add(playerSlotController);
    }

    boolean isInputValid() {
        if (!isPlayerSizeValid()) {
            infotext.setText("not enough players (3...4 are required)!");
            return false;
        }
        if (!areNamesLongEnough()) {
            infotext.setText("names are not long (4++ signs) enough!");
            return false;
        }
        if (!areNameUnique()) {
            infotext.setText("names are not unique!");
            return false;
        }
        if (!areColorsUnique()) {
            infotext.setText("colors are not unique!");
            return false;
        }
        if (!checkAi()) {
            infotext.setText("only (and at least) one human player allowed");
            return false;
        }
        return true;
    }

    private boolean areNamesLongEnough() {
        for (PlayerSlotController playerSlotController : playerSlotControllers) {
            if (playerSlotController.isParticipant()) {
                if (playerSlotController.getName().trim().length() < 4) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean checkAi() {
        int humanCount = 0;
        for (PlayerSlotController playerSlotController : playerSlotControllers) {
            if (playerSlotController.isParticipant()) {
                if (!playerSlotController.isAi()) {
                    humanCount = humanCount + 1;
                }
            }
        }
        return humanCount == 1;
    }

    private boolean areColorsUnique() {
        List<Color> colors = new ArrayList<>();
        for (PlayerSlotController playerSlotController : playerSlotControllers) {
            if (playerSlotController.isParticipant()) {
                Color newColor = playerSlotController.getColor();
                if (colors.contains(newColor)) {
                    return false;
                }
                colors.add(newColor);
            }
        }
        return true;
    }

    private boolean isPlayerSizeValid() {
        int amount = 0;
        for (PlayerSlotController playerSlotController : playerSlotControllers) {
            if (playerSlotController.isParticipant()) {
                amount = amount + 1;
            }
        }
        return amount == 3 || amount == 4;
    }

    private boolean areNameUnique() {
        List<String> names = new ArrayList<>();
        for (PlayerSlotController playerSlotController : playerSlotControllers) {
            if (playerSlotController.isParticipant()) {
                String newName = playerSlotController.getName().trim().toLowerCase();
                if (names.contains(newName)) {
                    return false;
                }
                names.add(newName);
            }
        }
        return true;
    }

    GameSetup createGameSetup() {

        final List<Player> players = new ArrayList<>();
        for (PlayerSlotController playerSlotController : playerSlotControllers) {
            if (playerSlotController.isParticipant()) {
                players.add(new Player(
                        playerSlotController.getName(),
                        playerSlotController.getColorRgb(),
                        !playerSlotController.isAi()));
            }
        }
//        Collections.shuffle(players);
        return new GameSetup() {

            @Override
            public List<Player> getPlayers() {
                return players;
            }

        };
    }
}
