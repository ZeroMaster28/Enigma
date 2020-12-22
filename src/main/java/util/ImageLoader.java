package util;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

/**
 * Class is responsible for wrapping up the images loading logic. There is only one instance
 * per application.
 */
public class ImageLoader {
    /** Images directory name */
    private static final String RESOURCES_IMG_DIR = "images";

    /** Class instance */
    private static ImageLoader instance;

    /** Class loader used for loading image resources */
    private final ClassLoader classLoader;


    /** Returns instance of ImageLoader */
    public static ImageLoader getInstance() {
        if(instance == null) {
            instance = new ImageLoader();
        }
        return instance;
    }

    private ImageLoader() {
        classLoader = this.getClass().getClassLoader();
    }

    /**
     * Loads image from classpath by this image's filename
     * @param imageName Name of the image that needs to be loaded
     * @return <code>BufferedImage</code> instance for loaded image file
     * @throws IOException
     */
    public BufferedImage loadImage(String imageName) throws IOException {
        return ImageIO.read(Objects.requireNonNull(classLoader.getResource(
                RESOURCES_IMG_DIR + "/" + imageName)));
    }
}
