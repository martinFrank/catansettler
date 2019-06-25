package com.github.martinfrank.catansettler.resource.image;

import com.github.martinfrank.catansettler.resource.ResourceManager;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.net.URL;

public class ResourceImagesLoader {

    private final ResourceManager resourceManager;
    public ResourceImagesLoader(ResourceManager resourceManager) {
        this.resourceManager = resourceManager;
    }

    public ResourceImages load(URL resource) throws JAXBException, NullPointerException, MalformedURLException {
        JAXBContext jaxbContext = JAXBContext.newInstance(ResourceImages.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        ResourceImages images = (ResourceImages) unmarshaller.unmarshal(resource);
        images.loadImages(resourceManager);
        return images;
    }
}
