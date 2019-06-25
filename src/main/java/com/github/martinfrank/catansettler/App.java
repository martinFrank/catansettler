package com.github.martinfrank.catansettler;

import com.github.martinfrank.catansettler.gui.ControllerFactory;
import com.github.martinfrank.catansettler.resource.ResourceManager;
import com.github.martinfrank.catansettler.resource.image.ResourceImages;
import com.github.martinfrank.catansettler.resource.image.ResourceImagesLoader;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.JAXBException;
import java.io.IOException;

public class App extends Application {

    public static void main(String[] args) {
        Application.launch(App.class);
    }

    private Pane root;
    private Game game;

    private ResourceImages images;

    private static final Logger LOGGER = LoggerFactory.getLogger(Game.class);

    @Override
    public void init() {
        ResourceManager resourceManager = new ResourceManager(getClass().getClassLoader());
        try {
            ResourceImagesLoader imageResourceLoader = new ResourceImagesLoader(resourceManager);
            images = imageResourceLoader.load(resourceManager.getImagesRoot());
        } catch (JAXBException | IOException e) {
            e.printStackTrace();
        }

        game = new Game(resourceManager);
        ControllerFactory controllerFactory = new ControllerFactory(game);

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(resourceManager.getGuiRoot());
            fxmlLoader.setControllerFactory(controllerFactory);
            root = fxmlLoader.load();
        } catch (IOException e) {
            LOGGER.debug("error: {}", e);
        }

        game.init(images);
    }

    @Override
    public void start(Stage stage) {
        if (wasInitSuccessFul()) {
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("tbd: set title");
            stage.show();
            game.init(images);
        } else {
            LOGGER.debug("error during init");
            Platform.exit();
            System.exit(0);
        }
    }

    private boolean wasInitSuccessFul() {
        boolean conditionOnRoot = root != null;
        LOGGER.debug("check root:{}, success={}", root, conditionOnRoot);
        return conditionOnRoot;
    }

    @Override
    public void stop() {
    }
}
