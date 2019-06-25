package com.github.martinfrank.catansettler.resource.image;

import com.github.martinfrank.catansettler.model.CardResource;
import com.github.martinfrank.catansettler.model.FieldResource;
import com.github.martinfrank.catansettler.resource.ResourceManager;
import javafx.scene.image.Image;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.net.MalformedURLException;
import java.util.List;
import java.util.Optional;

@XmlRootElement (name="resource_images")
public class ResourceImages {

    @XmlElementWrapper(name="heximages")
    @XmlElement(name= "heximage")
    List<ResourceImage> hexImages;

    @XmlElementWrapper(name="cardimages")
    @XmlElement(name= "cardimage")
    List<ResourceImage> cardImages;

    public Image getHexImage(FieldResource fieldResource) {
        return getImage(hexImages, fieldResource.getId());
    }

    public Image getCardImage(CardResource cardResource) {
        return getImage(cardImages, cardResource.getId());
    }


    void loadImages(ResourceManager resourceManager) throws MalformedURLException {
        for (ResourceImage img: hexImages){
            img.loadHexImage(resourceManager);
        }
        for (ResourceImage img: cardImages){
            img.loadCardImage(resourceManager);
        }
    }

    private Image getImage(List<ResourceImage> images, String id) {
        Optional<ResourceImage> resourceImage =
                images.stream().filter(ri -> ri.isId(id)).findAny();
        return resourceImage.map(ResourceImage::getImage).orElse(null);
    }

}
