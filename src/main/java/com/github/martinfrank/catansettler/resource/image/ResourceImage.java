package com.github.martinfrank.catansettler.resource.image;

import com.github.martinfrank.catansettler.resource.ResourceManager;
import javafx.scene.image.Image;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.net.MalformedURLException;

@XmlRootElement
public class ResourceImage {

    @XmlAttribute(name = "id")
    private String id;

    @XmlAttribute(name = "source")
    private String source;

    private Image image;

    void loadHexImage(ResourceManager resourceManager) throws MalformedURLException {
        loadImageWithTransparency(resourceManager, source, 96, 96);
    }

    boolean isId(String id) {
        return this.id.equals(id);
    }

    public Image getImage() {
        return image;
    }

    public void loadCardImage(ResourceManager resourceManager) throws MalformedURLException {
//        loadImageWithTransparency(resourceManager, source, 187, 274);
        loadImageWithTransparency(resourceManager, source, 96, 137);
    }

    private void loadImageWithTransparency(ResourceManager resourceManager, String source, int width, int height) throws MalformedURLException {
        Image image = new Image(resourceManager.getResourceURL(source).toString());
        Image transparency = TransparencyFilter.filter(image, 0x00FF00FF);
        this.image = ScaleFilter.scale(transparency, width, height);
    }
}
